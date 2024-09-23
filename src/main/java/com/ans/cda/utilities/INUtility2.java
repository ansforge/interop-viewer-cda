package com.ans.cda.utilities;

import java.util.concurrent.Callable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;

/**
 * I18N utility class
 * 
 * @author bensa Nizar
 */
public final class INUtility2 {

	/**
	 * INUtility constructor
	 */
	private INUtility2() {
		// empty constructor
	}

	/**
	 * creates a bound Label whose value is computed on language change.
	 *
	 * @param func the function to compute the value
	 * @return Label
	 */
	public static Label labelForValue(final Callable<String> func) {
		final Label label = new Label();
		label.textProperty().bind(INUtility.createStringBinding(func));
		return label;
	}

	/**
	 * creates a bound Button for the given resourcebundle key
	 *
	 * @param key  ResourceBundle key
	 * @param args optional arguments for the message
	 * @return Button
	 */
	public static Button buttonForKey(final String key, final Object... args) {
		final Button button = new Button();
		button.textProperty().bind(INUtility.createStringBinding(key, args));
		return button;
	}

	/**
	 * creates a bound MenuItem for the given resourcebundle key
	 *
	 * @param key  ResourceBundle key
	 * @param args optional arguments for the message
	 * @return Button
	 */
	public static MenuItem menuBarForKey(final String key, final Object... args) {
		final MenuItem button = new MenuItem();
		button.textProperty().bind(INUtility.createStringBinding(key, args));
		return button;
	}

	/**
	 * creates a bound MenuItem for the given resourcebundle key
	 *
	 * @param key  ResourceBundle key
	 * @param args optional arguments for the message
	 * @return Button
	 */
	public static Menu menuForKey(final String key, final Object... args) {
		final Menu button = new Menu();
		button.textProperty().bind(INUtility.createStringBinding(key, args));
		return button;
	}

	/**
	 * creates a bound RadioButton for the given resourcebundle key
	 *
	 * @param key  ResourceBundle key
	 * @param args optional arguments for the message
	 * @return Button
	 */
	public static RadioButton radioForKey(final String key, final Object... args) {
		final RadioButton button = new RadioButton();
		button.textProperty().bind(INUtility.createStringBinding(key, args));
		return button;
	}
}