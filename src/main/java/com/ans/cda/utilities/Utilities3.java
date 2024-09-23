package com.ans.cda.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BarcodeParameters;
import com.aspose.barcode.generation.BaseGenerationParameters;
import com.aspose.barcode.generation.DataMatrixParameters;
import com.aspose.barcode.generation.Unit;

import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Utilities3
 */
public final class Utilities3 {
	/**
	 * Utilities constructor
	 */
	private Utilities3() {
		// empty constructor
	}

	/**
	 * getEngine
	 * 
	 * @param browserEngine
	 * @return
	 */
	public static WebEngine getEngine(final WebView browserEngine) {
		return browserEngine.getEngine();
	}

	/**
	 * getHistory
	 * 
	 * @param browserEngine
	 * @return
	 */
	public static WebHistory getHistory(final WebView browserEngine) {
		return getEngine(browserEngine).getHistory();
	}

	/**
	 * getLoadWorker
	 * 
	 * @param browserEngine
	 * @return
	 */
	public static Worker<Void> getLoadWorker(final WebView browserEngine) {
		return getEngine(browserEngine).getLoadWorker();
	}

	/**
	 * @return List of Integers of each index where the substring start in text
	 */
	public static List<Integer> getIndexStartsOfSubstring(final String text, final String substring,
			final boolean matchCase) {
		final List<Integer> startIndices = new ArrayList<>();
		String textL;
		String strL;
		if (matchCase) {
			textL = text;
			strL = substring;
		} else {
			textL = text.toLowerCase(Locale.FRENCH);
			strL = substring.toLowerCase(Locale.FRENCH);
		}

		int indexStart = 0;
		final int count = getSubstringMatchedCount(strL, textL, matchCase);

		for (int i = 0; i < count; i++) {
			indexStart = textL.indexOf(strL, indexStart);
			startIndices.add(indexStart);
			indexStart += strL.length();
		}
		return startIndices;
	}

	/**
	 * @param str:  matcher string
	 * @param text: matched string
	 * @return the number of matcher substrings in the matched string
	 */
	public static int getSubstringMatchedCount(final String str, final String text, final boolean matchCase) {
		String textL;
		String strL;
		if (matchCase) {
			textL = text;
			strL = str;
		} else {
			textL = text.toLowerCase(Locale.FRENCH);
			strL = str.toLowerCase(Locale.FRENCH);
		}
		int count = 0;
		if (strL.isEmpty()) {
			count = 0;
		}
		final Pattern pattern = Pattern.compile(Pattern.quote(strL));
		final Matcher matcher = pattern.matcher(textL);

		while (matcher.find()) {
			count++;
		}
		return count;
	}

	/**
	 * getPopupContent
	 * 
	 * @param popup
	 * @return
	 */
	public static ObservableList<Node> getPopupContent(final Popup popup) {
		return popup.getContent();
	}

	/**
	 * getXDimension
	 * 
	 * @param gen
	 * @return
	 */
	public static Unit getXDimension(final BarcodeGenerator gen) {
		return getBarcode(gen).getXDimension();
	}

	/**
	 * getBarcode
	 * 
	 * @param gen
	 * @return
	 */
	public static BarcodeParameters getBarcode(final BarcodeGenerator gen) {
		return getParameters(gen).getBarcode();
	}

	/**
	 * getDataMatrix
	 * 
	 * @param gen
	 * @return
	 */
	public static DataMatrixParameters getDataMatrix(final BarcodeGenerator gen) {
		return getBarcode(gen).getDataMatrix();
	}

	/**
	 * getParameters
	 * 
	 * @param gen
	 * @return
	 */
	public static BaseGenerationParameters getParameters(final BarcodeGenerator gen) {
		return gen.getParameters();
	}

	/**
	 * getIcons
	 * 
	 * @param stage
	 * @return
	 */
	public static ObservableList<Image> getIcons(final Stage stage) {
		return stage.getIcons();
	}

	/**
	 * getChildrenUnmodifiable
	 * 
	 * @param browser
	 * @return
	 */
	public static ObservableList<Node> getChildrenUnmodifiable(final WebView browser) {
		return browser.getChildrenUnmodifiable();
	}

	/**
	 * getOrientation
	 * 
	 * @param sbuilder
	 * @return
	 */
	public static Orientation getOrientation(final ScrollBar sbuilder) {
		return sbuilder.getOrientation();
	}

	/**
	 * getContextClassLoader
	 * 
	 * @return
	 */
	public static ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	/**
	 * get the default locale. This is the systems default if contained in the
	 * supported locales, english otherwise.
	 *
	 * @return
	 */
	public static Locale getDefaultLocale() {
		final Locale sysDefault = Locale.getDefault();
		return getSupportedLocales().contains(sysDefault) ? sysDefault : Locale.FRENCH;
	}

	/**
	 * get the supported Locales.
	 *
	 * @return List of Locale objects.
	 */
	public static List<Locale> getSupportedLocales() {
		return new ArrayList<>(
				Arrays.asList(Locale.FRENCH, Locale.ENGLISH, Locale.GERMAN, Locale.forLanguageTag("nl-NL"),
						Locale.forLanguageTag("es-ES"), Locale.forLanguageTag("pt-PT"), Locale.ITALIAN));
	}
}
