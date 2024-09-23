package com.ans.cda.gui.mediator;

import java.nio.file.Path;
import java.util.List;

import com.ans.cda.gui.TabSpace;
import com.ans.cda.gui.components.FindReplaceToolBar;
import com.ans.cda.gui.components.MainController;
import com.ans.cda.gui.components.MainMenuBar;

import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

/**
 * interface IMediator
 * 
 * @author bensa Nizar
 */
public interface IMediator {
	/**
	 * setMenuBar
	 * 
	 * @param mainMenuBar
	 */
	void setMenuBar(MainMenuBar mainMenuBar);

	/**
	 * setTabSpaces
	 * 
	 * @param tabSpaces
	 */
	void setTabSpaces(List<TabSpace> tabSpaces);

	/**
	 * setMainController
	 * 
	 * @param mainController
	 */
	void setMainController(MainController mainController);

	/**
	 * setFindReplaceToolBar
	 * 
	 * @param findReplace
	 */
	void setFindReplaceToolBar(FindReplaceToolBar findReplace);

	/**
	 * getText
	 * 
	 * @return
	 */
	String getText();

	/**
	 * getFilePath
	 * 
	 * @return
	 */
	Path getCurrentTabIndex();

	/**
	 * isFileSaved
	 * 
	 * @return
	 */
	boolean isFileSaved();

	/**
	 * shouldExit
	 * 
	 * @return
	 */
	boolean shouldExit();

	/**
	 * isMatchCase
	 * 
	 * @return
	 */
	boolean isMatchCase();

	/**
	 * getEventBuilder
	 * 
	 * @return
	 */
	Mediator.EventBuilder getEventBuilder();

	/**
	 * getMediatorText
	 * 
	 * @return
	 */
	String getMediatorText();

	/**
	 * getFilePath
	 * 
	 * @return
	 */
	Path getFilePath();

	/**
	 * getStageTitle
	 * 
	 * @return
	 */
	Stage getStageTitle();

	/**
	 * getXslFilePath
	 * 
	 * @return
	 */
	String getXslFilePath();

	/**
	 * setXslFilePath
	 * 
	 * @param xslFilePath
	 */
	void setXslFilePath(String xslFilePath);

	/**
	 * getWebEngine
	 * 
	 * @return
	 */
	WebEngine getWebEngine();

	/**
	 * setWebEngine
	 * 
	 * @param engine
	 */
	void setWebEngine(WebEngine engine);

}
