package com.ans.cda.utilities;

import java.io.File;

import javax.xml.parsers.SAXParser;

import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.TextRenderer;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.render.BlockBox;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Utilities
 */
public final class Utilities {

	/**
	 * Utilities constructor
	 */
	private Utilities() {
		// empty constructor
	}

	/**
	 * getXMLReader
	 * 
	 * @param parser
	 * @return
	 * @throws SAXException
	 */
	public static XMLReader getXMLReader(final SAXParser parser) throws SAXException {
		return parser.getXMLReader();
	}

	/**
	 * getChildren
	 * 
	 * @param item
	 * @return
	 */
	public static ObservableList<TreeItem<String>> getChildren(final TreeItem<String> item) {
		return item.getChildren();
	}

	/**
	 * getChildrenNode
	 * 
	 * @param vBox
	 * @return
	 */
	public static ObservableList<Node> getChildrenNode(final VBox vBox) {
		return vBox.getChildren();
	}

	/**
	 * getChildrenHNode
	 * 
	 * @param hBox
	 * @return
	 */
	public static ObservableList<Node> getChildrenHNode(final HBox hBox) {
		return hBox.getChildren();
	}

	/**
	 * getItemsSplit
	 * 
	 * @param splitPane
	 * @return
	 */
	public static ObservableList<Node> getItemsSplit(final SplitPane splitPane) {
		return splitPane.getItems();
	}

	/**
	 * newObsMenu
	 * 
	 * @param fileChooser
	 */
	public static ObservableList<ExtensionFilter> newExtFilter(final FileChooser fileChooser) {
		return fileChooser.getExtensionFilters();
	}

	/**
	 * getSharedContext
	 * 
	 * @param renderer
	 * @return
	 */
	public static SharedContext getSharedContext(final ITextRenderer renderer) {
		return renderer.getSharedContext();
	}

	/**
	 * getTextRenderer
	 * 
	 * @param sharedContext
	 * @return
	 */
	public static TextRenderer getTextRenderer(final SharedContext sharedContext) {
		return sharedContext.getTextRenderer();
	}

	/**
	 * getElement
	 * 
	 * @param box
	 * @return
	 */
	public static Element getElement(final BlockBox box) {
		return box.getElement();
	}

	/**
	 * getAbsolutePath
	 * 
	 * @param string
	 * @return
	 */
	public static String getAbsolutePath(final String string) {
		return new File(string).getAbsolutePath();
	}

	/**
	 * getFile
	 * 
	 * @param string
	 * @return
	 */
	public static File getFile(final String string) {
		return new File(string);
	}

}
