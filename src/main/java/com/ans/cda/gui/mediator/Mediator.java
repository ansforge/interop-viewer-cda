package com.ans.cda.gui.mediator;

import java.nio.file.Path;
import java.util.List;

import com.ans.cda.gui.TabSpace;
import com.ans.cda.gui.components.FindReplaceToolBar;
import com.ans.cda.gui.components.MainController;
import com.ans.cda.gui.components.MainMenuBar;
import com.ans.cda.lib.EditorUtils;
import com.ans.cda.utilities.Utilities2;

import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

/**
 * Class Mediator
 * 
 * @author bensa Nizar
 */
public class Mediator implements IMediator {
	/**
	 * filePath
	 */
	private Path filePath;
	/**
	 * mainController
	 */
	private MainController mainController;
	/**
	 * fileSaved
	 */
	private boolean fileSaved;
	/**
	 * textChanged
	 */
	private boolean textChanged;
	/**
	 * text
	 */
	private String text;
	/**
	 * mainMenuBar
	 */
	public MainMenuBar mainMenuBar;
	/**
	 * findReplace
	 */
	private FindReplaceToolBar findReplace;
	/**
	 * tabSpaces
	 */
	private List<TabSpace> tabSpaces;
	/**
	 * xslFilePath
	 */
	private String xslFilePath;
	/**
	 * webEngine
	 */
	public WebEngine webEngine;

	/**
	 * MediatorInstance class
	 */
	private static final class MediatorInstance {
		/**
		 * instance
		 */
		private static Mediator instance = new Mediator();
	}

	/**
	 * getInstance
	 * 
	 * @return instance
	 */
	public static Mediator getInstance() {
		return MediatorInstance.instance;
	}

	/**
	 * setMenuBar
	 */
	@Override
	public void setMenuBar(final MainMenuBar mainMenuBar) {
		this.mainMenuBar = mainMenuBar;
	}

	/**
	 * setTabSpaces
	 */
	@Override
	public void setTabSpaces(final List<TabSpace> tabSpaces) {
		this.tabSpaces = tabSpaces;
	}

	/**
	 * setMainController
	 */
	@Override
	public void setMainController(final MainController mainController) {
		this.mainController = mainController;
	}

	/**
	 * setFindReplaceToolBar
	 */
	@Override
	public void setFindReplaceToolBar(final FindReplaceToolBar findReplace) {
		this.findReplace = findReplace;
	}

	/**
	 * @param text: the updated text a setter for text
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * @return true if the current tab has a file that is saved, false otherwise.
	 */
	@Override
	public boolean isFileSaved() {
		final int tabIndex = mainController.getCurrentTabIndex();
		return tabSpaces.get(tabIndex).isFileSaved();
	}

	/**
	 * checks whether to show the confirmation window when the user exits the app or
	 * not by checking if all tabspaces have no changed text
	 * 
	 * @return AND of tabspaces.isTextChanged() ie true if any value is true in
	 *         tabspaces, false otherwise
	 */
	@Override
	public boolean shouldExit() {
		return tabSpaces.stream().anyMatch(tabSpace -> tabSpace.isTextChanged());
	}

	/**
	 * @return the text in the selected tab
	 */
	@Override
	public String getText() {
		final int tabIndex = mainController.getCurrentTabIndex();
		return tabSpaces.get(tabIndex).getText();
	}

	/**
	 * @return the mediator's text, used by textSpace when OPEN_MENU ie gets the
	 *         text from the file, to the mediator, then from the mediator, to
	 *         textspace
	 */
	@Override
	public String getMediatorText() {
		return text;
	}

	/**
	 * @return the mediator's filepath, used by mediator to update the title when
	 *         OPEN_MENU
	 */
	@Override
	public Path getFilePath() {
		return filePath;
	}

	/**
	 * isMatchCase
	 */
	@Override
	public boolean isMatchCase() {
		return findReplace.isMatchCase();
	}

	/**
	 * @return the Path of the file that is opened in the selected tab
	 */
	@Override
	public Path getCurrentTabIndex() {
		final int tabIndex = mainController.getCurrentTabIndex();
		return tabSpaces.get(tabIndex).getCurrentPath();
	}

	/**
	 * notify event
	 * 
	 * @param event
	 */
	private void notify(final Events event) {
		int tabIndex = mainController.getCurrentTabIndex();
		switch (event) {
		case TEXT_CHANGED:
			tabSpaces.get(tabIndex).sendEvent1(Events.TEXT_CHANGED);
			updateTabTitle(tabIndex);
			break;
		case NEW_TAB:
			mainController.createNewTab(false);
			getFilePath();
			break;
		case UNDO_TEXT:
			tabSpaces.get(tabIndex).sendEvent1(Events.UNDO_TEXT);
			break;
		case OPEN_MENU:
			if (Utilities2.getTabs(mainController).isEmpty()) {
				mainController.createNewTab(true);
				tabIndex = mainController.getCurrentTabIndex();
			}
			tabSpaces.get(tabIndex).sendEvent1(Events.OPEN_MENU);
			mainController.updateIsSaved(fileSaved);
			updateTitles();
			break;
		case REDO_TEXT:
			tabSpaces.get(tabIndex).sendEvent1(Events.REDO_TEXT);
			break;
		case SAVE_MENU:
			tabSpaces.get(tabIndex).sendEvent1(Events.SAVE_MENU);
			updateTitles();
			break;
		case ABOUT_MENU:
			break;
		case AUTO_SAVE:
			text = tabSpaces.get(tabIndex).getText();
			EditorUtils.writeToFile(text, filePath);
			updateTitles();
			break;
		case CLOSE_MENU:
			break;
		case EXIT_EVENT:
			text = tabSpaces.get(tabIndex).getText();
			EditorUtils.writeToFile(text, filePath);
			break;
		case TAB_CHANGED:
			EditorUtils.setCurrentEditorTitle(mainController.getTabPane(), tabSpaces.get(tabIndex).getCurrentPath(),
					mainController.getCurrentTabIndex());
			break;
		case SAVE_REQUEST:
			EditorUtils.showSaveWindow(Utilities2.getScene(mainController).getWindow());
			break;
		case COPY_MENU:
			tabSpaces.get(tabIndex).sendEvent1(Events.COPY_MENU);
			break;
		case CUT_MENU:
			tabSpaces.get(tabIndex).sendEvent1(Events.CUT_MENU);
			break;
		case PASTE_MENU:
			tabSpaces.get(tabIndex).sendEvent1(Events.PASTE_MENU);
			break;
		case SHOW_FIND_REPLACE:
			tabSpaces.get(tabIndex).sendEvent3(Events.SHOW_FIND_REPLACE);
			break;
		case SHOW_FIND:
			tabSpaces.get(tabIndex).sendEvent3(Events.SHOW_FIND);
			break;
		case HIDE_REPLACE:
			tabSpaces.get(tabIndex).sendEvent3(Events.HIDE_REPLACE);
			break;
		case FIND_SELECT:
			tabSpaces.get(tabIndex).sendEvent3(Events.FIND_SELECT);
			break;
		case FIND_NEXT:
			tabSpaces.get(tabIndex).sendEvent2(Events.FIND_NEXT);
			break;
		case FIND_PREVIOUS:
			tabSpaces.get(tabIndex).sendEvent2(Events.FIND_PREVIOUS);
			break;
		case REPLACE_CURRENT:
			tabSpaces.get(tabIndex).sendEvent2(Events.REPLACE_CURRENT);
			break;
		case REPLACE_ALL:
			tabSpaces.get(tabIndex).sendEvent2(Events.REPLACE_ALL);
			break;
		case TREE_FILE:
			tabSpaces.get(tabIndex).sendEvent2(Events.TREE_FILE);
			break;
		case VIEW:
			tabSpaces.get(tabIndex).sendEvent2(Events.VIEW);
			break;
		default:
			break;
		}
	}

	/**
	 * updates the title of the tab, and stage whenever the selected tab changes
	 * does nothing if no file was opened
	 */
	private void updateTitles() {
		if (getCurrentTabIndex() == null) {
			return;
		}
		EditorUtils.setTabTitle(mainController.getTabPane(), getCurrentTabIndex(), mainController.getCurrentTabIndex());
		EditorUtils.setStageTitle(mainController.getTabPane(), getCurrentTabIndex());
	}

	/**
	 * update Tab Title
	 * 
	 * @param index
	 */
	private void updateTabTitle(final int index) {
		final String title = Utilities2.getTabText(mainController, index);
		final Character charactar = title.charAt(title.length() - 1);
		if (charactar.equals('*')) {
			return;
		}
		Utilities2.getTab(mainController, index).setText(title + " *");
	}

	/**
	 * getStageTitle
	 * 
	 * @return
	 */
	@Override
	public Stage getStageTitle() {
		return EditorUtils.getStageTitle();
	}

	/**
	 * returns a builder of events, used to build events
	 */
	@Override
	public EventBuilder getEventBuilder() {
		return new EventBuilder(textChanged, fileSaved, filePath, text);
	}

	/**
	 * EventBuilder static sous class
	 */
	public static final class EventBuilder {

		/**
		 * textChange
		 */
		private boolean textChange;
		/**
		 * fileSave
		 */
		private boolean fileSave;
		/**
		 * filePath
		 */
		private Path pathFile;
		/**
		 * text
		 */
		private String text;
		/**
		 * event
		 */
		private Events event;

		/**
		 * EventBuilder constructor
		 * 
		 * @param textChanged
		 * @param fileSaved
		 * @param filePath
		 * @param text
		 */
		private EventBuilder(final boolean textChanged, final boolean fileSaved, final Path filePath,
				final String text) {
			this.textChange = textChanged;
			this.fileSave = fileSaved;
			this.pathFile = filePath;
			this.text = text;
		}

		/**
		 * textChanged event
		 * 
		 * @param textChanged
		 * @return
		 */
		public EventBuilder textChanged(final boolean textChanged) {
			this.textChange = textChanged;
			return this;
		}

		/**
		 * fileSaved event
		 * 
		 * @param fileSaved
		 * @return
		 */
		public EventBuilder fileSaved(final boolean fileSaved) {
			this.fileSave = fileSaved;
			return this;
		}

		/**
		 * withFilePath event
		 * 
		 * @param filePath
		 * @return
		 */
		public EventBuilder withFilePath(final Path filePath) {
			this.pathFile = filePath;
			return this;
		}

		/**
		 * withText event
		 * 
		 * @param text
		 * @return
		 */
		public EventBuilder withText(final String text) {
			this.text = text;
			return this;
		}

		/**
		 * withEvent
		 * 
		 * @param event
		 * @return
		 */
		public EventBuilder withEvent(final Events event) {
			this.event = event;
			return this;
		}

		/**
		 * build
		 */
		public void build() {
			final Mediator mediator = getInstance();
			mediator.text = this.text;
			mediator.filePath = this.pathFile;
			mediator.fileSaved = this.fileSave;
			mediator.textChanged = this.textChange;
			mediator.notify(event);
		}
	}

	/**
	 * @return the textChanged
	 */
	public boolean isTextChanged() {
		return textChanged;
	}

	/**
	 * @param textChanged the textChanged to set
	 */
	public void setTextChanged(final boolean textChanged) {
		this.textChanged = textChanged;
	}

	/**
	 * @return the xslFilePath
	 */
	@Override
	public String getXslFilePath() {
		return xslFilePath;
	}

	/**
	 * @param xslFilePath the xslFilePath to set
	 */
	@Override
	public void setXslFilePath(final String xslFilePath) {
		this.xslFilePath = xslFilePath;
	}

	/**
	 * getWebEngine
	 */
	@Override
	public WebEngine getWebEngine() {
		return this.webEngine;
	}

	/**
	 * setWebEngine
	 */
	@Override
	public void setWebEngine(final WebEngine engine) {
		this.webEngine = engine;

	}
}