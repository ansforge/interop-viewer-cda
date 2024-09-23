package com.ans.cda.gui.components;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.Selection;
import org.fxmisc.richtext.SelectionImpl;

import com.ans.cda.Handler;
import com.ans.cda.gui.mediator.Events;
import com.ans.cda.gui.mediator.IMediator;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.smallundoengine.EditorTextHistory;
import com.ans.cda.utilities.Utilities2;
import com.ans.cda.utilities.Utilities3;
import com.ans.cda.utilities.Utilities5;
import com.ans.cda.utilities.Utilities6;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * TextSpace main class
 * 
 * @author bensa Nizar
 */
public class TextSpace extends HBox {
	/**
	 * textSpaceNumber
	 */
	public int textSpaceNumber;
	/**
	 * mediator
	 */
	private final IMediator mediator = Mediator.getInstance();
	/**
	 * currentPath
	 */
	private Path currentPath;
	/**
	 * extraSelection
	 */
	private Selection<Collection<String>, String, Collection<String>> extraSelection;
	/**
	 * startIndices
	 */
	private List<Integer> startIndices;
	/**
	 * startIndicesTracker
	 */
	private int indicesTracker;
	/**
	 * textArea
	 */
	@FXML
	private CodeArea textArea;
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(Handler.class);

	/**
	 * loads textspace.fxml and specifies the controller and the root
	 */
	public TextSpace() {
		super();
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/textspace.fxml"));
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
	 * starts {@link TextSpace#textAreaChangeListener} sets the current TextSpace in
	 * the mediator to the current instance adds line number to textArea
	 */
	@FXML
	public void initialize() {
		textAreaChangeListener();
		textArea.setParagraphGraphicFactory(LineNumberFactory.get(textArea));
		// add highlighter
		extraSelection = new SelectionImpl<>(Utilities5.getString("button.otherselect.text"), textArea, path -> {
			// make rendered selection path look like a yellow highlighter
			path.setStrokeWidth(0);
			path.setFill(Color.YELLOW);
		});
		if (!textArea.addSelection(extraSelection)) {
			throw new IllegalStateException(Utilities5.getString("button.addzone.text"));
		}
	}

	/**
	 * sends TEXT_CHANGED event to the mediator updates the redo/undo stack
	 *
	 * @see Mediator
	 * @see EditorTextHistory
	 */
	private void textAreaChangeListener() {
		textArea.textProperty().addListener((observable, oldValue, newValue) -> Utilities2.getEventBuilder(mediator)
				.withEvent(Events.TEXT_CHANGED).build());
	}

	/**
	 * sets the number of the textspace (used when dealing with multiple tabs)
	 */
	public void setNumber(final int number) {
		textSpaceNumber = number;
	}

	/**
	 * removes the last word from the text sends TEXT_CHANGED event to the mediator
	 *
	 * @see Mediator
	 * @see EditorTextHistory
	 */
	public void undo(final EditorTextHistory editorTextHistory) {
		editorTextHistory.undo();
		textArea.replaceText(editorTextHistory.getText());
		Utilities2.getEventBuilder(mediator).withEvent(Events.TEXT_CHANGED).build();
	}

	/**
	 * adds the last word from redo stack to the text sends TEXT_CHANGED event to
	 * the mediator
	 *
	 * @see Mediator
	 * @see EditorTextHistory
	 */
	public void redo(final EditorTextHistory editorTextHistory) {
		editorTextHistory.redo();
		textArea.replaceText(editorTextHistory.getText());
		Utilities2.getEventBuilder(mediator).withEvent(Events.TEXT_CHANGED).build();
	}

	/**
	 * @return the text of textArea
	 */
	public String getText() {
		return textArea.getText();
	}

	/**
	 * @param text the text to be set sets the textArea text
	 */
	public void setText(final String text) {
		textArea.replaceText(text);
	}

	/**
	 * @return the current path of the file
	 */
	public Path getCurrentPath() {
		return currentPath;
	}

	/**
	 * @param path the path of the current file sets currentPath to path
	 */
	public void setCurrentPath(final Path path) {
		currentPath = path;
	}

	/**
	 * @return the selected text in codeArea
	 */
	public String getSelectedText() {
		return textArea.getSelectedText();
	}

	/**
	 * replaces the selected text with empty string
	 */
	public void removeSelectedText() {
		textArea.replaceSelection("");
	}

	private void clearHighlighting() {
		extraSelection.selectRange(0, 0);
	}

	/**
	 * highlights str
	 */
	public void selectText(final String str) {
		final String text = getText();
		startIndices = Utilities3.getIndexStartsOfSubstring(text, str, mediator.isMatchCase());

		if (str == null || str.isEmpty() || startIndices.isEmpty()) {
			extraSelection.selectRange(0, 0);
			return;
		}

		extraSelection.selectRange(startIndices.get(indicesTracker), startIndices.get(indicesTracker) + str.length());
	}

	/**
	 * increaseIndicesTracker
	 */
	public void increaseIndicesTracker() {
		if (startIndices == null) {
			return;
		}

		if (indicesTracker < startIndices.size() - 1) {
			indicesTracker++;
		}
	}

	/**
	 * decreaseIndicesTracker
	 */
	public void decreaseIndicesTracker() {
		if (indicesTracker > 0) {
			indicesTracker--;
		}
	}

	/**
	 * resetIndicesTracker
	 */
	public void resetIndicesTracker() {
		indicesTracker = 0;
	}

	/**
	 * replaceCurrent
	 * 
	 * @param oldString
	 * @param newStr
	 */
	public void replaceCurrent(final String oldString, final String newStr) {
		// called with textfield empty
		if (oldString == null || newStr == null) {
			return;
		}
		setText(Utilities6.replaceSpecificString(getText(), oldString, newStr, indicesTracker, mediator.isMatchCase()));
		clearHighlighting();
	}

	/**
	 * replaceAll
	 * 
	 * @param oldString
	 * @param newStr
	 */
	public void replaceAll(final String oldString, final String newStr) {
		// called with textfield empty
		if (oldString == null || newStr == null) {
			return;
		}

		final String newText = getText().replaceAll(oldString, newStr);
		setText(newText);
		clearHighlighting();
	}
}