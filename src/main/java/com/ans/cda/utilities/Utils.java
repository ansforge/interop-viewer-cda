package com.ans.cda.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ans.cda.Constant;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * tooltip class utilities
 * 
 * @author bensa Nizar
 */
public final class Utils {

	/**
	 * locale
	 */
	private static ObjectProperty<Locale> locale = new SimpleObjectProperty<>(Locale.getDefault());

	/**
	 * Utils constructor
	 */
	private Utils() {
		// empty constructor
	}

	/**
	 * localeProperty
	 * 
	 * @return
	 */
	public static ObjectProperty<Locale> localeProperty() {
		return locale;
	}

	/**
	 * getLocale
	 * 
	 * @return
	 */
	public static Locale getLocale() {
		return locale.get();
	}

	/**
	 * setLocale
	 * 
	 * @param locale
	 */
	public static void setLocale(final Locale locale) {
		localeProperty().set(locale);
	}

	/**
	 * i18n
	 * 
	 * @param key
	 * @return
	 */
	public static String i18n(final String key) {
		return ResourceBundle.getBundle("bundleName", getLocale()).getString(key);
	}

	/**
	 * readFileContents
	 * 
	 * @param selectedFile
	 * @throws IOException
	 */
	public static String readFileContents(final InputStream file) throws IOException {
		String singleString;
		try (BufferedReader bReader = new BufferedReader(new InputStreamReader(file))) {
			final StringBuilder sbuilder = new StringBuilder();
			String line = bReader.readLine();
			while (line != null) {
				sbuilder.append(line).append(Constant.RETOURCHARIOT);
				line = bReader.readLine();
			}
			singleString = sbuilder.toString();
		}
		return singleString;
	}

	/**
	 * newObsListTArea
	 * 
	 * @param textArea
	 */
	public static ObservableList<String> newObsListTArea(final TextArea textArea) {
		return textArea.getStylesheets();
	}

	/**
	 * newObsList
	 * 
	 * @param vBox
	 */
	public static ObservableList<Node> newObsList(final VBox vBox) {
		return vBox.getChildren();
	}
	
	/**
	 * imageView
	 * 
	 * @return
	 */
	public static Image imageView10() {
		return new Image(Constant.README);
	}

	/**
	 * imageView
	 * 
	 * @return
	 */
	public static Image imageView11() {
		return new Image(Constant.ABOUT);
	}

	/**
	 * imageView
	 * 
	 * @return
	 */
	public static Image imageView12() {
		return new Image(Constant.OPENPHOTO);
	}

}