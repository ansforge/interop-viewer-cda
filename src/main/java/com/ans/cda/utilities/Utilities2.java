package com.ans.cda.utilities;

import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.jsoup.parser.Tag;

import com.ans.cda.Constant;
import com.ans.cda.gui.components.MainController;
import com.ans.cda.gui.mediator.IMediator;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.gui.mediator.Mediator.EventBuilder;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BarcodeParameters;
import com.aspose.barcode.generation.BaseGenerationParameters;
import com.aspose.barcode.generation.DataMatrixParameters;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.Unit;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Utilities2
 */
public final class Utilities2 {

	/**
	 * Utilities constructor
	 */
	private Utilities2() {
		// empty constructor
	}

	/**
	 * getBarcode
	 * 
	 * @param dataMatrixValue
	 * @return
	 */
	public static BarcodeGenerator getBarcode(final String dataMatrixValue) {
		return new BarcodeGenerator(EncodeTypes.DATA_MATRIX, dataMatrixValue);
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
	 * getBarcode
	 * 
	 * @param gen
	 * @return
	 */
	public static BarcodeParameters getBarcode(final BarcodeGenerator gen) {
		return getParameters(gen).getBarcode();
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
	 * getDataMatrix
	 * 
	 * @param gen
	 * @return
	 */
	public static DataMatrixParameters getDataMatrix(final BarcodeGenerator gen) {
		return getBarcode(gen).getDataMatrix();
	}

	/**
	 * getElement
	 * 
	 * @param matrix
	 * @return
	 */
	public static org.jsoup.nodes.Element getElement(final Path matrix) {
		return new org.jsoup.nodes.Element(Tag.valueOf(Constant.IMG), "").attr(Constant.SRC,
				"file:///" + matrix.toString());
	}

	/**
	 * getStyleScene
	 * 
	 * @param scene
	 */
	public static ObservableList<String> getStyleScene(final Scene scene) {
		return scene.getStylesheets();
	}

	/**
	 * getCount
	 * 
	 * @param document
	 * @return
	 */
	public static int getCount(final PDDocument document) {
		return getPages(document).getCount();
	}

	/**
	 * getPages
	 * 
	 * @param document
	 * @return
	 */
	public static PDPageTree getPages(final PDDocument document) {
		return document.getPages();
	}

	/**
	 * getStyleDialog
	 * 
	 * @param dialogPane
	 */
	public static ObservableList<String> getStyleDialog(final DialogPane dialogPane) {
		return dialogPane.getStylesheets();
	}

	/**
	 * getStyleClass
	 * 
	 * @param dialogPane
	 */
	public static ObservableList<String> getStyleClass(final DialogPane dialogPane) {
		return dialogPane.getStyleClass();
	}

	/**
	 * getButtonType
	 * 
	 * @param alert
	 */
	public static ObservableList<ButtonType> getButtonType(final Alert alert) {
		return alert.getButtonTypes();
	}

	/**
	 * getEvent
	 * 
	 * @return
	 */
	public static EventBuilder getEvent() {
		return Mediator.getInstance().getEventBuilder();
	}

	/**
	 * getEventBuilder
	 * 
	 * @param mediator
	 * 
	 * @return
	 */
	public static EventBuilder getEventBuilder(final IMediator mediator) {
		return mediator.getEventBuilder();
	}

	/**
	 * getMediatorFilePath
	 * 
	 * @param mediator
	 * @return
	 */
	public static Path getMediatorFilePath(final IMediator mediator) {
		return mediator.getFilePath();
	}

	/**
	 * getTabPane
	 * 
	 * @param tabPane
	 * @param tabNumber
	 */
	public static ObservableList<Tab> getTabPane(final TabPane tabPane, final int tabNumber) {
		return tabPane.getTabs();
	}

	/**
	 * getTabPane
	 * 
	 * @param tabPane
	 */
	public static ObservableList<Tab> getTabPanes(final TabPane tabPane) {
		return tabPane.getTabs();
	}

	/**
	 * getTabNumber
	 * 
	 * @param tabPane
	 * @param tabNumber
	 */
	public static Tab getTabNumber(final TabPane tabPane, final int tabNumber) {
		return getTabPane(tabPane, tabNumber).get(tabNumber);
	}

	/**
	 * getTabPane
	 * 
	 * @param mainController
	 */
	public static TabPane getTabPane(final MainController mainController) {
		return mainController.getTabPane();
	}

	/**
	 * getTabs
	 * 
	 * @param mainController
	 */
	public static ObservableList<Tab> getTabs(final MainController mainController) {
		return getTabPane(mainController).getTabs();
	}

	/**
	 * getTab
	 * 
	 * @param mainController
	 * @param index
	 */
	public static Tab getTab(final MainController mainController, final int index) {
		return getTabs(mainController).get(index);
	}

	/**
	 * getTabText
	 * 
	 * @param mainController
	 * @param index
	 */
	public static String getTabText(final MainController mainController, final int index) {
		return getTabs(mainController).get(index).getText();
	}

	/**
	 * getScene
	 * 
	 * @param mainController
	 */
	public static Scene getScene(final MainController mainController) {
		return getTabPane(mainController).getScene();
	}

}
