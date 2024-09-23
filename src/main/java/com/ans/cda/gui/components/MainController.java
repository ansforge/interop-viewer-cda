package com.ans.cda.gui.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ans.cda.Constant;
import com.ans.cda.gui.TabSpace;
import com.ans.cda.gui.mediator.Events;
import com.ans.cda.gui.mediator.IMediator;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.lib.EditorUtils;
import com.ans.cda.smallundoengine.EditorTextHistory;
import com.ans.cda.utilities.Utilities;
import com.ans.cda.utilities.Utilities2;
import com.ans.cda.utilities.Utilities4;
import com.ans.cda.utilities.Utilities5;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

/**
 * MainController for xml editor
 * 
 * @author bensa Nizar
 */
public class MainController {

	/**
	 * tabPane
	 */
	@FXML
	private TabPane tabPane;

	/**
	 * textSpace
	 */
	@FXML
	private TextSpace textSpace;

	/**
	 * mainMenuBar
	 */
	@FXML
	private MainMenuBar mainMenuBar;

	/**
	 * findReplaceToolBar
	 */
	@FXML
	private FindReplaceToolBar findReplace;

	/**
	 * mediator
	 */
	private final IMediator mediator = Mediator.getInstance();

	/**
	 * tabSpaces
	 */
	private final List<TabSpace> tabSpaces = new ArrayList<>();

	/**
	 * textSpacesCount
	 */
	private int textSpacesCount;

	/**
	 * initialize
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	@FXML
	public void initialize() throws SAXException, ParserConfigurationException, IOException {
		createNewTab(false);
		mediator.setMainController(this);
		mediator.setTabSpaces(tabSpaces);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
	}

	/**
	 * tabPaneListener
	 */
	private void tabPaneListener() {
		Utilities4.selectedIndexProperty(tabPane).addListener((ov, oldValue, newValue) -> {
			if (Utilities2.getTabPanes(tabPane).isEmpty()) {
				return;
			}
			Utilities2.getEventBuilder(mediator).withEvent(Events.TAB_CHANGED).build();
		});
	}

	/**
	 * createNewTab
	 * 
	 * @param isSaved
	 * @throws IOException  @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void createNewTab(final boolean isSaved) {
		final Tab tab = new Tab(Constant.UNTITLED_TAB + textSpacesCount);
		final TextSpace textSpace = new TextSpace();
		final FindReplaceToolBar findReplace = new FindReplaceToolBar();
		findReplace.setManaged(false);
		final EditorTextHistory editorTextHistory = new EditorTextHistory();
		textSpace.setNumber(textSpacesCount);
		// VBox used for tab content that hold textspace and toolbar because we can't
		// add many nodes to Tab
		final VBox vBox = new VBox();
		Utilities.getChildrenNode(vBox).addAll(textSpace, findReplace);
		tab.setContent(vBox);
		final TabSpace tabSpace = addTabSpace(textSpace, editorTextHistory, findReplace, isSaved);
		tab.setOnCloseRequest(event -> {
			final Alert alert = EditorUtils.createConfirmationAlert(Utilities5.getString("button.closetxt.text"),
					Utilities5.getString("button.yes.text"), "");
			boolean close = true;
			if (tabSpace.isTextChanged()) {
				final Optional<ButtonType> btnClicked = alert.showAndWait();
				if (!btnClicked.get().getText().equals(Utilities5.getString("button.yes.text"))) {
					close = false;
					event.consume();
				}
			}
			if (close) {
				tabSpaces.remove(getCurrentTabIndex());
			}
		});
		Utilities2.getTabPanes(tabPane).add(tab);
		textSpacesCount++;
		tabPaneListener();
	}

	/**
	 * addTabSpace adds a new tabspace the the list of tabspaces
	 *
	 * @param textSpace         the current TextSpace of TabSpace
	 * @param editorTextHistory the current EditorTextHistory of TabSpace
	 * @param isSaved           specifies if the file is saved in the system
	 * @return the created tabSpace
	 */
	private TabSpace addTabSpace(final TextSpace textSpace, final EditorTextHistory editorTextHistory,
			final FindReplaceToolBar findReplace, final boolean isSaved) {
		final TabSpace current = new TabSpace(textSpace, editorTextHistory, findReplace);
		current.setIsSaved(isSaved);
		tabSpaces.add(current);
		return current;
	}

	/**
	 * getCurrentTab
	 * 
	 * @return the current selected tab
	 */
	public Tab getCurrentTab() {
		return Utilities4.getSelectedItem(tabPane);
	}

	/**
	 * getTabPane
	 * 
	 * @return tabPane
	 */
	public TabPane getTabPane() {
		return tabPane;
	}

	/**
	 * updateIsSaved
	 * 
	 * @param isSaved
	 */
	public void updateIsSaved(final boolean isSaved) {
		tabSpaces.get(getCurrentTabIndex()).setIsSaved(isSaved);
	}

	/**
	 * getCurrentTabIndex
	 * 
	 * @return the index of the selected tab
	 */
	public int getCurrentTabIndex() {
		return Utilities4.getSelectedIndex(tabPane);
	}

	/**
	 * getMainMenuBar
	 * 
	 * @return the mainMenuBar
	 */
	public MainMenuBar getMainMenuBar() {
		return mainMenuBar;
	}

	/**
	 * setMainMenuBar
	 * 
	 * @param mainMenuBar the mainMenuBar to set
	 */
	public void setMainMenuBar(final MainMenuBar mainMenuBar) {
		this.mainMenuBar = mainMenuBar;
	}
}