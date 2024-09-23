package com.ans.cda.utilities;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Tooltip;

/**
 * I18N utility class
 * 
 * @author bensa Nizar
 */
public final class INUtility {
	/**
	 * the current selected Locale
	 */
	private static ObjectProperty<Locale> locale;

	/**
	 * INUtility constructor
	 */
	private INUtility() {
		// empty constructor
	}

	/**
	 * set default local
	 */
	static {
		locale = new SimpleObjectProperty<>(Utilities3.getDefaultLocale());
		locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
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
		Locale.setDefault(locale);
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
	 * get
	 *
	 * @param key  message key
	 * @param args optional arguments for the message
	 * @return localized formatted string
	 */
	public static String get(final String key, final Object... args) {
		final ResourceBundle bundle = ResourceBundle.getBundle("properties/messages", getLocale());
		return MessageFormat.format(bundle.getString(key), args);
	}

	/**
	 * creates a String binding to a localized String for the given message bundle
	 * key
	 *
	 * @param key key
	 * @return String binding
	 */
	public static StringBinding createStringBinding(final String key, final Object... args) {
		return Bindings.createStringBinding(() -> get(key, args), locale);
	}

	/**
	 * creates a String Binding to a localized String that is computed by calling
	 * the given func
	 *
	 * @param func function called on every change
	 * @return StringBinding
	 */
	public static StringBinding createStringBinding(final Callable<String> func) {
		return Bindings.createStringBinding(func, locale);
	}

	/**
	 * createBoundTooltip
	 * 
	 * @param key
	 * @return
	 */
	public static Tooltip createBoundTooltip(final String key) {
		final Tooltip tooltip = new Tooltip();
		tooltip.textProperty().bind(createStringBinding(key, locale));
		return tooltip;
	}
}