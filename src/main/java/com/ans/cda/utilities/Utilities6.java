package com.ans.cda.utilities;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.ans.cda.Constant;

import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;

/**
 * Utilities5
 */
public final class Utilities6 {
	/**
	 * desktop
	 */
	private final static Desktop DESKTOP = Desktop.getDesktop();
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(Utilities6.class);

	/**
	 * Utilities6 constructor
	 */
	private Utilities6() {
		// empty constructor
	}

	/**
	 * print log
	 * 
	 * @param textArea
	 * @param files
	 */
	public static void printLog(final TextField textArea, final List<File> files) {
		if (files == null || files.isEmpty()) {
			return;
		}
		for (final File file : files) {
			textArea.appendText(file.getAbsolutePath() + "\n");
		}
	}

	/**
	 * generate Image From PDF
	 * 
	 * @param pdffile
	 * @return lfiles
	 */
	public static List<File> generateImageFromPDF(final File pdffile) {
		final List<File> lfiles = new ArrayList<>();
		File file = null;
		final int min = 0;
		final int max = 999_999_999;
		try (PDDocument document = PDDocument.load(new File(pdffile.getAbsolutePath()))) {
			final PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int i = 0; i < Utilities2.getCount(document); i++) {
				// Save every page as PNG image
				final BufferedImage image = pdfRenderer.renderImage(i);
				final int random = (int) (Math.random() * (max - min + 1) + min);
				file = Utilities.getFile(pdffile.getParent() + "//" + String.format("%d.png", random));
				ImageIO.write(image, Constant.PNG, file);
				lfiles.add(file);
			}
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		} finally {
			file.deleteOnExit();
		}
		return lfiles;
	}

	/**
	 * transform xml to html with xslt
	 * 
	 * @param xslFile
	 * @param xmlFile
	 * @return strResult
	 */
	public static Path transform(final String xslFile, final String xmlFile, final Path file1) {

		StreamSource xlsStreamSource = new StreamSource(Paths.get(xslFile).toAbsolutePath().toFile());

		StreamSource xmlStreamSource = new StreamSource(Paths.get(xmlFile).toAbsolutePath().toFile());

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance("org.apache.xalan.processor.TransformerFactoryImpl", null);

		Path pathToHtmlFile = file1;
		StreamResult result = new StreamResult(pathToHtmlFile.toFile());

		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer(xlsStreamSource);
			transformer.transform(xmlStreamSource, result);
		} catch (final TransformerConfigurationException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		} catch (final TransformerException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		}
		return pathToHtmlFile;

	}

	/**
	 * transform xml to html with xslt
	 * 
	 * @param xslFile
	 * @param xmlFile
	 * @return strResult
	 */
	public static String transformDom(final String xslFile, final String xmlFile) {

		final TransformerFactory transformer = TransformerFactory.newInstance();
		// attribut properties to make javafx working in heigher jre than 1.8
		try {
			transformer.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
		} catch (final TransformerConfigurationException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		}

		Transformer xform;

		final StringWriter writer = new StringWriter();
		String strResult = null;
		try {
			xform = transformer.newTransformer(new StreamSource(new File(xslFile)));
			xform.setOutputProperty(OutputKeys.ENCODING, "UTF-16");
			xform.setOutputProperty(OutputKeys.INDENT, "yes");
			xform.setOutputProperty(OutputKeys.METHOD, "html");
			xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			xform.transform(new StreamSource(new File(xmlFile)), new StreamResult(writer));
			strResult = writer.toString();
		} catch (final TransformerException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		}
		return strResult;

	}

	/**
	 * remove extension from file name
	 * 
	 * @param s
	 * @return string
	 */
	public static String removeExtension(final String string) {
		return string != null && string.lastIndexOf('.') > 0 ? string.substring(0, string.lastIndexOf('.')) : string;
	}

	/**
	 * open html file in browser
	 * 
	 * @param file
	 */
	public static void openFile(final File file) {
		try {
			DESKTOP.open(file);
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		}
	}

	/**
	 * getDefaultToolkit
	 * 
	 * @return
	 */
	public static Toolkit getDefaultToolkit() {
		return Toolkit.getDefaultToolkit();
	}

	/**
	 * getScreenSize
	 * 
	 * @return
	 */
	public static Dimension getScreenSize() {
		return getDefaultToolkit().getScreenSize();
	}

	/**
	 * browser
	 * 
	 * @param url
	 */
	public static void browser(final String url) {
		try {
			if (Desktop.isDesktopSupported()) {
				final Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(URI.create(url));
				}
			}
		} catch (final IOException | InternalError e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		}
	}

	/**
	 * merge images file
	 * 
	 * @param lfiles
	 */
	public static File mergeImage(final List<File> lfiles) {
		File imgFile = null;
		final int rows = lfiles.size(); // we assume the no. of rows and cols are known and each chunk has equal width
		// and height
		final int cols = 1;
		final int chunks = rows * cols;
		int chunkWidth;
		int chunkHeight;
		int type;
		// fetching image files
		final File[] imgFiles = new File[chunks];
		for (int i = 0; i < chunks; i++) {
			imgFiles[i] = new File(lfiles.get(i).getAbsolutePath());
		}
		// creating a bufferd image array from image files
		final BufferedImage[] buffImages = new BufferedImage[chunks];
		for (int i = 0; i < chunks; i++) {
			try {
				buffImages[i] = ImageIO.read(imgFiles[i]);
			} catch (final IOException e) {
				if (LOG.isInfoEnabled()) {
					final String error = e.getMessage();
					LOG.info(error);
				}
			}
		}
		type = buffImages[0].getType();
		chunkWidth = buffImages[0].getWidth();
		chunkHeight = buffImages[0].getHeight();
		// Initializing the final image
		final BufferedImage finalImg = new BufferedImage(chunkWidth * cols, chunkHeight * rows, type);
		int num = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
				num++;
			}
		}
		try {
			imgFile = new File(lfiles.get(0).getParent() + "\\" + RandomStringUtils.randomAlphanumeric(8) + "merged.png");
			ImageIO.write(finalImg, "png", imgFile);
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.info(error);
			}
		}
		return imgFile;
	}

	/**
	 * sets the given Locale in the I18N class and keeps count of the number of
	 * switches.
	 *
	 * @param locale the new local to set
	 */
	public static void switchLanguage(final Locale locale) {
		INUtility.setLocale(locale);
	}

	/**
	 * @param text:    text
	 * @param current: the replaced string
	 * @param str:     the new string
	 * @param index:   the ith occurence of current in text
	 * @return text with current string replaced with str
	 *
	 */
	public static String replaceSpecificString(final String text, final String current, final String str,
			final int index, final boolean matchCase) {
		String textL;
		String strL;
		if (matchCase) {
			textL = text;
			strL = current;
		} else {
			textL = text.toLowerCase(Locale.FRENCH);
			strL = current.toLowerCase(Locale.FRENCH);
		}
		final List<Integer> startIndices = Utilities3.getIndexStartsOfSubstring(textL, strL, matchCase);
		if (index >= startIndices.size() && LOG.isInfoEnabled()) {
			final String error = "text doesn't contain " + index + "th " + str;
			LOG.info(error);
		}
		final int startIndex = startIndices.get(index);
		return new StringBuilder(textL).replace(startIndex, startIndex + strL.length(), str).toString();

	}

	/**
	 * load
	 * 
	 * @param webEngine
	 * @param url
	 */
	public static void load(final WebEngine webEngine, final String url) {
		webEngine.load(url);
	}

}
