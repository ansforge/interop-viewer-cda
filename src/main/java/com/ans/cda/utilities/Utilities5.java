package com.ans.cda.utilities;

import java.util.ResourceBundle;

import com.ans.cda.gui.components.TextSpace;
import com.ans.cda.gui.mediator.Mediator;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Skin;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;

/**
 * Utilities5
 */
public final class Utilities5 {

	/**
	 * Utilities constructor
	 */
	private Utilities5() {
		// empty constructor
	}

	/**
	 * getString value
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(final String key) {
		final ResourceBundle bundle = ResourceBundle.getBundle("properties/messages", INUtility.getLocale());
		return bundle.getString(key);
	}

	/**
	 * selectedItemProperty
	 * 
	 * @param tvSelModel
	 * @return
	 */
	public static ReadOnlyObjectProperty<TreeItem<String>> selectedItemProperty(
			final MultipleSelectionModel<TreeItem<String>> tvSelModel) {
		return tvSelModel.selectedItemProperty();
	}

	/**
	 * getSkin
	 * 
	 * @param textAr
	 * @return
	 */
	public static Skin<?> getSkin(final TextArea textAr) {
		return textAr.getSkin();
	}

	/**
	 * getChildSPane
	 * 
	 * @param vBox
	 * @return
	 */
	public static ObservableList<Node> getChildSPane(final StackPane pane) {
		return pane.getChildren();
	}

	/**
	 * getStyleClassSplP
	 * 
	 * @param splitPane
	 */
	public static ObservableList<String> getStyleClassSplP(final SplitPane splitPane) {
		return splitPane.getStyleClass();
	}

	/**
	 * replaceAll
	 */
	public static void replaceAll(final TextSpace textSpace, final String toolBarString, final Mediator mediator) {
		textSpace.replaceAll(toolBarString, mediator.getMediatorText());
	}

	/**
	 * cutMenu
	 */
	public static void cutMenu(final TextSpace textSpace, final Mediator mediator) {
		mediator.setText(textSpace.getSelectedText());
		cutFromTextArea(textSpace);
	}

	/**
	 * findSelect
	 */
	public static void findSelect(final Mediator mediator, final TextSpace textSpace) {
		final String toolBarString = mediator.getMediatorText();
		textSpace.resetIndicesTracker();
		textSpace.selectText(toolBarString);
	}

	/**
	 * replaceCurrent
	 */
	public static void replaceCurrent(final TextSpace textSpace, final String toolBarString, final Mediator mediator) {
		textSpace.replaceCurrent(toolBarString, mediator.getMediatorText());
	}

	/**
	 * copyMenu
	 */
	public static void copyMenu(final TextSpace textSpace, final Mediator mediator) {
		mediator.setText(textSpace.getSelectedText());
	}

	/**
	 * findNext
	 */
	public static void findNext(final TextSpace textSpace, final String toolBarString) {
		textSpace.increaseIndicesTracker();
		textSpace.selectText(toolBarString);
	}

	/**
	 * findPrevious
	 */
	public static void findPrevious(final TextSpace textSpace, final String toolBarString) {
		textSpace.decreaseIndicesTracker();
		textSpace.selectText(toolBarString);
	}

	/**
	 * removes the selected text in textArea
	 */
	public static void cutFromTextArea(final TextSpace textSpace) {
		textSpace.removeSelectedText();
	}

}
