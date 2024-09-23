package com.ans.cda.lib;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.ans.cda.Constant;
import com.ans.cda.gui.components.MainMenuBar;
import com.ans.cda.gui.mediator.Events;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.utilities.Utilities;
import com.ans.cda.utilities.Utilities2;
import com.ans.cda.utilities.Utilities5;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Editor Utils class : class utilitaire
 * 
 * @author bensa Nizar
 */
public final class EditorUtils {
	/**
	 * stageprimary
	 */
	private static Stage stageprimary = new Stage();
	/**
	 * OBJ
	 */
	private static final Path OBJ = null;
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(EditorUtils.class);

	/**
	 * EditorUtils constructor
	 */
	private EditorUtils() {
		// empty constructor
	}

	/**
	 * writeToFile
	 * 
	 * @param text
	 * @param path
	 * @return
	 */
	public static boolean writeToFile(final String text, final Path path) {
		boolean bool;
		if (path == null || text == null) {
			bool = false;
		} else {
			try {
				Files.write(path.toAbsolutePath(), text.getBytes("utf-8"), StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (final IOException e) {
				if (LOG.isInfoEnabled()) {
					final String error = e.getMessage();
					LOG.error(error);
				}
			}
			bool = true;
		}

		return bool;
	}

	/**
	 * shows the version information of the app
	 */
	public static void showAboutWindow(final MainMenuBar mainMenuBar) {
		final FXMLLoader fxmlLoader = new FXMLLoader(mainMenuBar.getClass().getResource("/pages/about.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			final Scene scene = mainMenuBar.getScene();
			Utilities2.getStyleScene(scene).add(mainMenuBar.getClass().getResource("/style.css").toExternalForm());
			final Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle(Utilities5.getString("button.propos.text"));
			stage.setScene(new Scene(root, 290, 130));
			stage.showAndWait();
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				LOG.info(e.getMessage());
			}
		}

	}

	/**
	 * onCloseExitConfirmation
	 * 
	 * @param stage
	 */
	public static void onCloseExitConfirmation(final Stage stage) {
		if (Mediator.getInstance().shouldExit()) {
			final Alert alert = createConfirmationAlert(Utilities5.getString("button.closeevent.text"),
					Utilities5.getString("button.yes.text"), Utilities5.getString("button.no.text"));
			final DialogPane dialogPane = alert.getDialogPane();
			Utilities2.getStyleDialog(dialogPane).add(EditorUtils.class.getResource("/style.css").toExternalForm());
			Utilities2.getStyleClass(dialogPane).add("myDialog");
			dialogPane.setMinHeight(150);
			dialogPane.setMaxHeight(150);
			dialogPane.setPrefHeight(150);
			final Optional<ButtonType> btnClicked = alert.showAndWait();
			if (btnClicked.get().getText().equals(Utilities5.getString("button.yes.text"))) {
				if (Mediator.getInstance().isFileSaved()) {
					Utilities2.getEvent().withEvent(Events.AUTO_SAVE).build();
					stage.close();
				} else {
					Utilities2.getEvent().withEvent(Events.SAVE_REQUEST).build();
					stage.close();
				}
			} else if (btnClicked.get().getText().equals(Utilities5.getString("button.no.text"))) {
				stage.close();
			}
		} else {
			stage.close();
		}
	}

	/**
	 * creates an alert confirmation window with 2/3 buttons, 2 custom buttons, 1
	 * 
	 * @param alertText:   the content of the alert
	 * @param buttonText1: the text value of the first button
	 * @param buttonText2: the text value of the second button
	 *
	 */
	public static Alert createConfirmationAlert(final String alertText, final String buttonText1,
			final String buttonText2) {
		final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(Utilities5.getString("button.quit.text"));
		alert.setHeaderText(null);
		alert.setContentText(alertText);
		final ButtonType btn1 = new ButtonType(buttonText1);
		final ButtonType btn2 = new ButtonType(buttonText2);
		final ButtonType cnlBtn = new ButtonType(Utilities5.getString("button.cancel.text"),
				ButtonBar.ButtonData.CANCEL_CLOSE);
		if (buttonText2.isEmpty()) {
			Utilities2.getButtonType(alert).setAll(btn1, cnlBtn);
		} else {
			Utilities2.getButtonType(alert).setAll(btn1, btn2, cnlBtn);
		}
		return alert;
	}

	/**
	 * updates the stage title whenever the selected tab changes
	 *
	 * @param tabPane:   the TabPane of the main controller
	 * @param filePath:  the path of the opened file in the selected tab
	 * @param tabNumber: the index of the selected tab
	 */
	public static void setCurrentEditorTitle(final TabPane tabPane, final Path filePath, final int tabNumber) {
		final Stage stage = (Stage) tabPane.getParent().getScene().getWindow();

		if (filePath == null) {
			stage.setTitle(Constant.UNTITLED_TAB + tabNumber);
			return;
		}

		stage.setTitle(filePath.toString());
		Utilities2.getTabNumber(tabPane, tabNumber).setText(filePath.getFileName().toString());
	}

	/**
	 * updates the stage title used whenever the user opens a new file, or save a
	 * new file
	 *
	 * @param filePath: the path of the saved file
	 * @param tabPane:  the current tabPane, used to get the stage
	 */
	public static void setStageTitle(final TabPane tabPane, final Path filePath) {
		final Stage stage = (Stage) tabPane.getParent().getScene().getWindow();
		stage.setTitle(filePath.toString());
		stageprimary = stage;
	}

	/**
	 * getStageTitle
	 * 
	 * @return
	 */
	public static Stage getStageTitle() {
		return getStageprimary();
	}

	/**
	 * updates the stage title used whenever the user opens a new file, or save a
	 * new file
	 * 
	 * @param filePath
	 * @param tabPane
	 * @param tabNumber
	 */
	public static void setTabTitle(final TabPane tabPane, final Path filePath, final int tabNumber) {
		Utilities2.getTabNumber(tabPane, tabNumber).setText(filePath.getFileName().toString());
	}

	/**
	 * readFromFile
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> readFromFile(final File file) {
		List<String> lines = new ArrayList<>();
		try {
			lines = Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				LOG.info(e.getMessage());
			}
		}
		return lines;
	}

	/**
	 * opens a fileChooser save windows so the user can save a new file as .txt does
	 * nothing if the file in null creates a new text file with the current text in
	 * the specified path sends SAVE_MENU event to the mediator
	 *
	 * @see Mediator
	 * @see EditorUtils#writeToFile(String, Path)
	 */
	public static Path showSaveWindow(final Window window) {
		Path path;
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(Utilities5.getString("button.save.text"));
		Utilities.newExtFilter(fileChooser).add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
		File file = fileChooser.showSaveDialog(window);
		if (file == null) { // check if the user clicked the cancel button
			path = OBJ;
		} else {
			file = new File(file.getPath() + Constant.XMLEXT);
			writeToFile(Mediator.getInstance().getText(), file.toPath());
			path = file.toPath();
		}
		return path;
	}

	/**
	 * @return the stageprimary
	 */
	public static Stage getStageprimary() {
		return stageprimary;
	}

	/**
	 * @param stageprimary the stageprimary to set
	 */
	public static void setStageprimary(final Stage stageprimary) {
		EditorUtils.stageprimary = stageprimary;
	}
}