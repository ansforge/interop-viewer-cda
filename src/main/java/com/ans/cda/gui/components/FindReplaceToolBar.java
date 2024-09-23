package com.ans.cda.gui.components;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.ans.cda.gui.mediator.Events;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.utilities.Utilities2;
import com.ans.cda.utilities.Utilities3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Find and Replace Tool Bar class
 * 
 * @author bensa Nizar
 */
public class FindReplaceToolBar extends VBox {

	/**
	 * findTextField
	 */
	@FXML
	private TextField findTextField;

	/**
	 * replaceTextField
	 */
	@FXML
	private TextField replaceTextField;

	/**
	 * findReplaceButton
	 */
	@FXML
	private Button findReplaceButton;

	/**
	 * nextFindButton
	 */
	@FXML
	private Button nextFindButton;

	/**
	 * previousFindButton
	 */
	@FXML
	private Button previousFind;

	/**
	 * findReplaceWordCount
	 */
	@FXML
	private Text findReplaceC;

	/**
	 * findReplaceHighlightedCount
	 */
	@FXML
	private Text findReplace;

	/**
	 * replaceAllCheckbox
	 */
	@FXML
	private CheckBox replaceAll;

	/**
	 * caseSensetiveCheckBox
	 */
	@FXML
	private CheckBox caseSensetive;

	/**
	 * findHbox
	 */
	@FXML
	private HBox findHbox;

	/**
	 * replaceHbox
	 */
	@FXML
	private HBox replaceHbox;

	/**
	 * hideFindReplaceToolBarButton
	 */
	@FXML
	private Button hideFindRep;

	/**
	 * findToolBar
	 */
	@FXML
	private ToolBar findToolBar;

	/**
	 * replaceToolBar
	 */
	@FXML
	private ToolBar replaceToolBar;

	/**
	 * mediator
	 */
	private final Mediator mediator = Mediator.getInstance();

	/**
	 * currentSelectedMatch
	 */
	private int currentSelected;

	/**
	 * matchedCount
	 */
	private int matchedCount;
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(FindReplaceToolBar.class);

	/**
	 * FindReplaceToolBar
	 */
	public FindReplaceToolBar() {
		super();
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/findAndReplace.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.error(error);
			}
		}
	}

	/**
	 * initialize
	 */
	@FXML
	public void initialize() {
		mediator.setFindReplaceToolBar(this);
		findTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> findReplaceTextFieldChangeListener());
		caseSensetive.selectedProperty()
				.addListener((observable, oldValue, newValue) -> findReplaceTextFieldChangeListener());
	}

	/**
	 * replaceButtonPressed
	 * 
	 * @param event
	 */
	@FXML
	public void replaceButtonPressed(final ActionEvent event) {
		if (replaceAll.isSelected()) {
			Utilities2.getEventBuilder(mediator).withText(replaceTextField.getText()).withEvent(Events.REPLACE_ALL).build();
		} else {
			Utilities2.getEventBuilder(mediator).withText(replaceTextField.getText()).withEvent(Events.REPLACE_CURRENT).build();
		}
	}

	/**
	 * hideFindReplaceToolBarButtonPressed
	 * 
	 * @param event
	 */
	@FXML
	public void hideFindReplaceToolBarButtonPressed(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.HIDE_REPLACE).build();
	}

	/**
	 * nextFindButtonPressed
	 */
	@FXML
	public void nextFindButtonPressed() {
		if (currentSelected + 1 <= matchedCount) {
			currentSelected++;
		}
		if (matchedCount > 0) {
			findReplace.setText(currentSelected + " of ");
		}
		Utilities2.getEventBuilder(mediator).withEvent(Events.FIND_NEXT).build();
	}

	/**
	 * previousFindButtonPressed
	 */
	@FXML
	public void previousFindButtonPressed() {
		if (currentSelected - 1 > 0) {
			currentSelected--;
		}
		if (matchedCount > 0) {
			findReplace.setText(currentSelected + " of ");
		}
		Utilities2.getEventBuilder(mediator).withEvent(Events.FIND_PREVIOUS).build();
	}

	/**
	 * findReplaceTextFieldChangeListener
	 */
	private void findReplaceTextFieldChangeListener() {
		currentSelected = 0;
		findReplace.setText("");
		final String text = mediator.getText();
		final String substring = findTextField.getText();
		matchedCount = Utilities3.getSubstringMatchedCount(substring, text, caseSensetive.isSelected());
		findReplaceC.setText(matchedCount + "\nmatches");

		if (matchedCount > 0) {
			currentSelected++;
			findReplace.setText(currentSelected + " of ");
		}
		Utilities2.getEventBuilder(mediator).withEvent(Events.FIND_SELECT).withText(substring).build();
	}

	/**
	 * setReplaceToolbarVisibility
	 * 
	 * @param visibility
	 */
	private void setReplaceToolbarVisibility(final boolean visibility) {
		replaceToolBar.setVisible(visibility);
		replaceToolBar.setManaged(visibility);
	}

	/**
	 * Shows replaceToolBar
	 */
	public void showFindReplace() {
		setReplaceToolbarVisibility(true);
		this.setVisible(true);
		this.setManaged(true);
	}

	/**
	 * Hides FindAndReplaceToolbar by hiding Vbox
	 */
	public void hideFindReplace() {
		this.setVisible(false);
		this.setManaged(false);
	}

	/**
	 * Shows findToolBar
	 */
	public void showFindToolbar() {
		setReplaceToolbarVisibility(false);
		this.setVisible(true);
		this.setManaged(true);
	}

	/**
	 * isMatchCase
	 * 
	 * @return
	 */
	public boolean isMatchCase() {
		return caseSensetive.isSelected();
	}
}