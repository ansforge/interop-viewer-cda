package com.ans.cda.gui;

import java.nio.file.Path;

import com.ans.cda.gui.components.FindReplaceToolBar;
import com.ans.cda.gui.components.TextSpace;
import com.ans.cda.gui.mediator.Events;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.smallundoengine.EditorTextHistory;
import com.ans.cda.utilities.Utilities5;

import javafx.scene.input.Clipboard;

/**
 * a class that wraps TextSpace, EditorTextHistory and FindReplaceToolBar
 * objects together
 * 
 * @author bensa Nizar
 */
public class TabSpace {
	/**
	 * mediator
	 */
	private final Mediator mediator = Mediator.getInstance();
	/**
	 * textSpace
	 */
	private final TextSpace textSpace;
	/**
	 * editorTextHistory
	 */
	private final EditorTextHistory editorTextHistory;
	/**
	 * findReplace
	 */
	private final FindReplaceToolBar findReplaceTB;
	/**
	 * toolBarString
	 */
	private String toolBarString;
	/**
	 * fileSaved
	 */
	private boolean fileSaved;
	/**
	 * textChanged
	 */
	private boolean textChanged;

	/**
	 * TabSpace
	 * 
	 * @param textSpace
	 * @param editorTextHistory
	 * @param findReplaceToolBar
	 */
	public TabSpace(final TextSpace textSpace, final EditorTextHistory editorTextHistory,
			final FindReplaceToolBar findReplace) {
		this.textSpace = textSpace;
		this.editorTextHistory = editorTextHistory;
		this.findReplaceTB = findReplace;
	}

	/**
	 * updates fileSaved
	 *
	 * @param bool the value isSaved will be set to
	 */
	public void setIsSaved(final boolean bool) {
		fileSaved = bool;
	}

	/**
	 * replaces textArea's text with itself appended with string content in the
	 * clipboards to textArea by
	 */
	private void pasteToTextArea() {
		final StringBuilder sBuilder = new StringBuilder();
		final String clipboardString = Clipboard.getSystemClipboard().getString();
		final String finalText = sBuilder.append(getText()).append(' ').append(clipboardString).toString();
		textSpace.setText(finalText);
	}

	/**
	 * hideReplace
	 */
	private void hideReplace() {
		findReplaceTB.hideFindReplace();
		textSpace.resetIndicesTracker();
		textSpace.selectText("");
	}

	/**
	 * sendEvent1
	 * 
	 * @param event
	 */
	public void sendEvent1(final Events event) {
		switch (event) {
		case UNDO_TEXT:
			undoText();
			break;
		case REDO_TEXT:
			redoText();
			break;
		case OPEN_MENU:
			openMenu();
			break;
		case SAVE_MENU:
			saveMenu();
			break;
		case TEXT_CHANGED:
			textChange();
			break;
		case COPY_MENU:
			Utilities5.copyMenu(textSpace, mediator);
			break;
		case CUT_MENU:
			Utilities5.cutMenu(textSpace, mediator);
			break;
		case PASTE_MENU:
			pasteToTextArea();
			break;
		default:
			break;
		}
	}

	/**
	 * sendEvent2
	 * 
	 * @param event
	 */
	public void sendEvent2(final Events event) {
		switch (event) {
		case VIEW:
			Utilities5.replaceAll(textSpace, toolBarString, mediator);
			break;
		case FIND_NEXT:
			Utilities5.findNext(textSpace, toolBarString);
			break;
		case FIND_PREVIOUS:
			Utilities5.findPrevious(textSpace, toolBarString);
			break;
		case REPLACE_CURRENT:
			Utilities5.replaceCurrent(textSpace, toolBarString, mediator);
			break;
		case REPLACE_ALL:
			Utilities5.replaceAll(textSpace, toolBarString, mediator);
			break;
		case TREE_FILE:
			Utilities5.replaceAll(textSpace, toolBarString, mediator);
			break;
		default:
			break;
		}
	}

	/**
	 * sendEvent3
	 * 
	 * @param event
	 */
	public void sendEvent3(final Events event) {
		switch (event) {
		case SHOW_FIND:
			findReplaceTB.showFindToolbar();
			break;
		case HIDE_REPLACE:
			hideReplace();
			break;
		case FIND_SELECT:
			Utilities5.findSelect(mediator, textSpace);
			break;
		case SHOW_FIND_REPLACE:
			findReplaceTB.showFindReplace();
			break;
		default:
			break;
		}
	}

	/**
	 * undoText
	 */
	private void undoText() {
		textSpace.undo(editorTextHistory);
		textChanged = true;
	}

	/**
	 * redoText
	 */
	private void redoText() {
		textSpace.redo(editorTextHistory);
		textChanged = true;
	}

	/**
	 * openMenu
	 */
	private void openMenu() {
		textSpace.setCurrentPath(mediator.getFilePath());
		textSpace.setText(mediator.getMediatorText());
		fileSaved = true;
	}

	/**
	 * saveMenu
	 */
	private void saveMenu() {
		textSpace.setCurrentPath(mediator.getFilePath());
		fileSaved = true;
		textChanged = false;
	}

	/**
	 * textChanged
	 */
	private void textChange() {
		editorTextHistory.update(textSpace.getText());
		textChanged = true;
	}

	/**
	 * getText
	 * 
	 * @return
	 */
	public String getText() {
		return textSpace.getText();
	}

	/**
	 * getCurrentPath
	 * 
	 * @return
	 */
	public Path getCurrentPath() {
		return textSpace.getCurrentPath();
	}

	/**
	 * isFileSaved
	 * 
	 * @return
	 */
	public boolean isFileSaved() {
		return fileSaved;
	}

	/**
	 * isTextChanged
	 * 
	 * @return
	 */
	public boolean isTextChanged() {
		return textChanged;
	}

}