package com.ans.cda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.ans.cda.utilities.Utilities;
import com.ans.cda.utilities.Utilities6;

/**
 * HTMLToPDF
 * 
 * @author bensalem Nizar
 */
public final class HTMLToPDF {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(HTMLToPDF.class);

	/**
	 * Utilities constructor
	 */
	private HTMLToPDF() {
		// empty constructor
	}

	/**
	 * void main
	 * 
	 * @param file
	 * @param filexsl
	 * @return
	 */
	public static File main(final String file, final String filexsl) {
		File outputPdf = null;
		try {
			final Path pdf = Files.createTempFile(null, Constant.HTMLEXT);
			final String streamRes = Utilities6.transformDom(filexsl, file);
			Files.write(pdf, streamRes.getBytes(StandardCharsets.UTF_8));
			// Source HTML file
			final String strFile = pdf.toString();
			final File inputHTML = new File(strFile);
			final File inputFile = inputHTML;
			final Path tempPath = Files.createTempFile(null, Constant.HTMLEXT);
			final File tempFile = new File(tempPath.toString());
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile.toURI()))) {
				try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(tempFile.toURI()))) {
					final String lineToRemove = Constant.DOCTYPE;
					String currentLine;
					while ((currentLine = reader.readLine()) != null) {
						// trim newline when comparing with lineToRemove
						final String trimmedLine = currentLine.trim();
						if (trimmedLine.startsWith(lineToRemove)) {
							continue;
						}
						writer.write(currentLine + System.getProperty("line.separator"));
					}
				}
			}
			final Path path = Paths.get(tempFile.getAbsolutePath());
			final Charset charset = StandardCharsets.UTF_8;
			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll("&nbsp;", " ");
			Files.write(path, content.getBytes(charset));
			// Generated PDF file name
			final Path pdfGenerated = Files.createTempFile(null, Constant.PDFEXT);
			final String pdfFile = pdfGenerated.toString();
			outputPdf = new File(pdfFile);
			// Convert HTML to XHTML
			final String xhtml = htmlToXhtml(new File(tempFile.getAbsolutePath()));
			xhtmlToPdf(xhtml, outputPdf);
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.error(error);
			}
		}
		return outputPdf;
	}

	/**
	 * htmlToXhtml convert html to xhtml
	 * 
	 * @param inputHTML
	 * @return
	 * @throws IOException
	 */
	private static String htmlToXhtml(final File inputHTML) throws IOException {
		final Document document = Jsoup.parse(inputHTML, Constant.UTF8);
		document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
		return document.html();
	}

	/**
	 * xhtmlToPdf convert xhtml to pdf
	 * 
	 * @param xhtml
	 * @param outputPdf
	 * @throws IOException
	 */
	private static void xhtmlToPdf(final String xhtml, final File outputPdf) throws IOException {
		final ITextRenderer renderer = new ITextRenderer();
		final SharedContext sharedContext = Utilities.getSharedContext(renderer);
		sharedContext.setPrint(true);
		sharedContext.setInteractive(false);
		sharedContext.setReplacedElementFactory(new ImageReplacedElementFactory());
		Utilities.getTextRenderer(sharedContext).setSmoothingThreshold(0);
		renderer.setDocumentFromString(xhtml, null);
		renderer.layout();
		try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPdf.toURI()))) {
			renderer.createPDF(outputStream);
			outputStream.close();
		}
	}
}