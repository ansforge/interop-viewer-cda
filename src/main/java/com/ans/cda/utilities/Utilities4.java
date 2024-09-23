package com.ans.cda.utilities;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;

/**
 * Utilities4
 */
public final class Utilities4 {

	/**
	 * Utilities constructor
	 */
	private Utilities4() {
		// empty constructor
	}

	/**
	 * getItemsMenu
	 * 
	 * @return
	 */
	public static ObservableList<MenuItem> getItemsMenu(final Menu menu) {
		return menu.getItems();
	}

	/**
	 * getItemsCombo
	 * 
	 * @return
	 */
	public static ObservableList<Entry> getItemsCombo(final ComboBox<WebHistory.Entry> comboBox) {
		return comboBox.getItems();
	}

	/**
	 * getMenu
	 * 
	 * @return
	 */
	public static ObservableList<Menu> getMenu(final MenuBar menuBar) {
		return menuBar.getMenus();
	}

	/**
	 * getSelectionModel
	 * 
	 * @param comboBox
	 * @return
	 */
	public static SingleSelectionModel<Entry> getSelectionModel(final ComboBox<WebHistory.Entry> comboBox) {
		return comboBox.getSelectionModel();
	}

	/**
	 * getSelectionModelTP
	 * 
	 * @param tabPane
	 * @return
	 */
	public static SingleSelectionModel<Tab> getSelectionModelTP(final TabPane tabPane) {
		return tabPane.getSelectionModel();
	}

	/**
	 * selectedIndexProperty
	 * 
	 * @param tabPane
	 * @return
	 */
	public static ReadOnlyIntegerProperty selectedIndexProperty(final TabPane tabPane) {
		return getSelectionModelTP(tabPane).selectedIndexProperty();
	}

	/**
	 * getSelectedIndex
	 * 
	 * @param tabPane
	 * @return
	 */
	public static int getSelectedIndex(final TabPane tabPane) {
		return getSelectionModelTP(tabPane).getSelectedIndex();
	}

	/**
	 * getSelectedItem
	 * 
	 * @param tabPane
	 * @return
	 */
	public static Tab getSelectedItem(final TabPane tabPane) {
		return getSelectionModelTP(tabPane).getSelectedItem();
	}

	/**
	 * getSelectedItemCB
	 * 
	 * @param comboBox
	 * @return
	 */
	public static Entry getSelectedItemCB(final ComboBox<WebHistory.Entry> comboBox) {
		return getSelectionModel(comboBox).getSelectedItem();
	}

	/**
	 * getChildPane
	 * 
	 * @param vBox
	 * @return
	 */
	public static ObservableList<Node> getChildPane(final FlowPane pane) {
		return pane.getChildren();
	}

	/**
	 * getElement
	 * 
	 * @return
	 */
	public static Element getElement(final String str) {
		return new Element(Tag.valueOf(str), "");
	}

	/**
	 * getSelectionModelTV
	 * 
	 * @param treeView
	 * @return
	 */
	public static MultipleSelectionModel<TreeItem<String>> getSelectionModelTV(final TreeView<String> treeView) {
		return treeView.getSelectionModel();
	}

	/**
	 * getStyleClassSP
	 * 
	 * @param stackPane
	 */
	public static ObservableList<String> getStyleClassSP(final StackPane stackPane) {
		return stackPane.getStyleClass();
	}

}
