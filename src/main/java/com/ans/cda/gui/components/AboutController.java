package com.ans.cda.gui.components;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;

import com.ans.cda.Constant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * About main menu Controller
 * 
 * @author bensa Nizar
 */
public class AboutController {
	/**
	 * ansLink
	 */
	@FXML
	private Hyperlink ansLink;
	/**
	 * okButton
	 */
	@FXML
	private Button okButton;
	/**
	 * aboutText
	 */
	@FXML
	private Text aboutText;
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(AboutController.class);

	/**
	 * okButtonClicked
	 * 
	 * @param event
	 */
	@FXML
	protected void okButtonClicked(final ActionEvent event) {
		final Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * ansLinkOnAction
	 * 
	 * @param event
	 */
	@FXML
	protected void ansLinkOnAction(final ActionEvent event) {
		final String githubURL = Constant.LINK;
		try {
			if (Desktop.isDesktopSupported()) {
				final Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(URI.create(githubURL));
				}
			}
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.error(error);
			}
		}
	}
}