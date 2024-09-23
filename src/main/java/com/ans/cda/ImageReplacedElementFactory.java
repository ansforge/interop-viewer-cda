package com.ans.cda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import com.ans.cda.utilities.Utilities;
import com.lowagie.text.Image;

/**
 * ImageReplacedElementFactory
 * 
 * @author bensalem Nizar
 */
public class ImageReplacedElementFactory implements ReplacedElementFactory {

	/**
	 * list
	 */
	private final List<String> list;
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(ImageReplacedElementFactory.class);

	/**
	 * ImageReplacedElementFactory constructor
	 */
	public ImageReplacedElementFactory() {
		super();
		list = new ArrayList<>();
	}

	/**
	 * generate Image From PDF
	 * 
	 * @param filename
	 */
	private void generateImageFromPDF(final String filename) {
		try (PDDocument document = PDDocument.load(new File(filename))) {
			final PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int page = 0; page < document.getNumberOfPages(); ++page) {
				final BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
				final Path png = Files.createTempFile(null, Constant.PNGEXT);
				final String strFile = png.toString();
				ImageIOUtil.writeImage(bim, String.format(strFile, page + 1), 300);
				list.add(strFile);
			}
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.error(error);
			}
		}
	}

	/**
	 * createReplacedElement
	 */
	@Override
	public ReplacedElement createReplacedElement(final LayoutContext context, final BlockBox box,
			final UserAgentCallback uac, final int cssWidth, final int cssHeight) {
		final Element element = Utilities.getElement(box);
		final String nodeName = element.getNodeName();
		if (Constant.OBJECT.equals(nodeName)) {
			try {
				iTxtObjElement(element, uac, cssWidth, cssHeight);
			} catch (final IOException e) {
				if (LOG.isInfoEnabled()) {
					final String error = e.getMessage();
					LOG.error(error);
				}
			}
		}
		if (Constant.IMG.equals(nodeName)) {
			iTxtImgElement(element, uac, cssWidth, cssHeight);
		}
		return null;
	}

	/**
	 * iTxtObjElement
	 * 
	 * @param element  @param uac
	 * @param cssWidth @param cssHeight
	 * @return
	 * @throws IOException
	 */
	private ITextImageElement iTxtObjElement(final Element element, final UserAgentCallback uac, final int cssWidth,
			final int cssHeight) throws IOException {
		String attribute = element.getAttribute(Constant.DATA);
		ITextImageElement itextElem = null;
		if (attribute.startsWith(Constant.STARTDATA)) {
			final byte[] decodedBytes = getDecodedBytes(attribute);
			final Path pdf = Files.createTempFile(null, Constant.PDFEXT);
			final String strFile = pdf.toString();
			Files.write(Paths.get(strFile), decodedBytes);
			attribute = new File(strFile).getAbsolutePath();
			generateImageFromPDF(attribute);
			for (final String string : list) {
				attribute = Utilities.getAbsolutePath(string);
				FSImage fsImage;
				fsImage = imageForPDF(attribute, uac);
				if (fsImage != null) {
					if (cssWidth == -1 || cssHeight == -1) {
						final int width = 10_000;
						final int height = 13_000;
						fsImage.scale(width, height);
					} else {
						fsImage.scale(cssWidth, cssHeight);
					}
					itextElem = itextElem(fsImage);
				}
			}

		}
		return itextElem;

	}

	/**
	 * itextElem
	 * 
	 * @param fsImage
	 * @return
	 */
	private ITextImageElement itextElem(final FSImage fsImage) {
		return new ITextImageElement(fsImage);
	}

	/**
	 * getDecodedBytes
	 * 
	 * @param attribute
	 * @return
	 */
	private byte[] getDecodedBytes(final String attribute) {
		final String[] words = attribute.split(Constant.STARTB64);
		String base64String = words[1];
		final Boolean bool1 = Constant.SPC1.contains(base64String);
		if (bool1) {
			base64String = base64String.replaceAll(Constant.SPC1, "");
		} else {
			base64String = base64String.replaceAll(Constant.SPC2, "");
		}
		return Base64.getDecoder().decode(base64String.replaceAll(" ", ""));
	}

	/**
	 * iTxtImgElement
	 * 
	 * @param element  @param uac
	 * @param cssWidth @param cssHeight
	 * @return
	 */
	private ITextImageElement iTxtImgElement(final Element element, final UserAgentCallback uac, final int cssWidth,
			final int cssHeight) {
		String attribute = element.getAttribute(Constant.SRC);
		if (attribute.startsWith(Constant.STARTDATA)) {
			final byte[] decodedBytes = getDecodedBytes(attribute);
			try {
				final Path pdf = Files.createTempFile(null, Constant.PDFEXT);
				final String strFile = pdf.toString();
				Files.write(Paths.get(strFile), decodedBytes);
				attribute = new File(strFile).getAbsolutePath();
			} catch (final IOException exp) {
				if (LOG.isInfoEnabled()) {
					final String error = exp.getMessage();
					LOG.error(error);
				}
			}
		}
		FSImage fsImage;
		fsImage = imageForPDF(attribute, uac);
		ITextImageElement itextElem = null;
		if (fsImage != null) {
			if (cssWidth == -1 || cssHeight == -1) {
				final int width = 6000;
				final int height = 6000;
				fsImage.scale(width, height);
			} else {
				fsImage.scale(cssWidth, cssHeight);
			}
			itextElem = new ITextImageElement(fsImage);
		}
		return itextElem;
	}

	/**
	 * imageForPDF
	 * 
	 * @param attribute @param uac
	 * @return
	 * @throws IOException @throws BadElementException
	 */
	protected FSImage imageForPDF(final String attribute, final UserAgentCallback uac) {
		FSImage fsImage = null;
		try (InputStream input = Files.newInputStream(Paths.get(attribute))) {
			final byte[] bytes = IOUtils.toByteArray(input);
			final Image image = Image.getInstance(bytes);
			fsImage = new ITextFSImage(image);
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.error(error);
			}
		}
		return fsImage;
	}

	@Override
	public void reset() {
		// Auto-generated method stub
	}

	@Override
	public void remove(final Element element) {
		// Auto-generated method stub
	}

	@Override
	public void setFormSubmissionListener(final FormSubmissionListener listener) {
		// Auto-generated method stub
	}
}