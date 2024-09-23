package com.ans.cda.smallundoengine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NumberUtils;

/**
 * Edit class
 * 
 * @author bensa Nizar
 */
public class EditImpl implements IEdit {

	/**
	 * undoStack
	 */
	private final List<String> undoStack = new ArrayList<>();
	/**
	 * redoStack
	 */
	private final List<String> redoStack = new ArrayList<>();
	/**
	 * text
	 */
	private String text;

	/**
	 * constructor
	 */
	public EditImpl() {
		text = "";
	}

	/**
	 * updates the current text to the previous one stored in the stack if the stack
	 * is not empty
	 *
	 * @return true if the stack is not empty, false otherwise
	 */
	@Override
	public void undo() {
		if (undoStack.isEmpty()) {
			text = "";
		} else if (NumberUtils.compare(undoStack.size(), 1) == 0) {
			text = "";
			redoStack.add(undoStack.get(undoStack.size() - 1));
		} else {
			text = undoStack.remove(undoStack.size() - 2);
			redoStack.add(undoStack.remove(undoStack.size() - 1));
		}

	}

	/**
	 * updates the current text to the original one stored in the stack if the stack
	 * is not empty
	 *
	 * @return true if the stack is not empty, false otherwise
	 */
	@Override
	public void redo() {
		if (!redoStack.isEmpty()) {
			text = redoStack.remove(redoStack.size() - 1);
		}
	}

	/**
	 * gettextArray
	 * 
	 * @param text
	 * @return
	 */
	private String[] gettextArray(final String text) {
		String[] textStringArray;
		if (text.contains(" ")) {
			textStringArray = text.split(" ");
		} else {
			textStringArray = new String[] { text };
		}
		return textStringArray;
	}

	/**
	 * parcourirlastWInStk
	 * 
	 * @param lastWordInStack
	 * @param lastWordInText
	 * @param shouldReplace
	 * @return
	 */
	private boolean parcourirlastWInStk(final String lastWordInStack, final String lastWordInText) {
		boolean shouldReplace = true;
		if (undoStack.get(undoStack.size() - 1).length() != text.length() - 1) {
			shouldReplace = false;
		}
		if (shouldReplace && lastWordInStack.length() == lastWordInText.length() - 1) {
			for (int j = lastWordInStack.length() - 1; j >= 0; j--) {
				if (lastWordInStack.charAt(j) != lastWordInText.charAt(j)) {
					shouldReplace = false;
					break;
				}
			}
		}
		return shouldReplace;
	}

	/**
	 * updates the current text
	 * 
	 * @param text the value of the new text
	 **/
	public void setText(final String text) {
		if (undoStack.isEmpty() || text.endsWith(" ")) {
			undoStack.add(text);
		}
		if (undoStack.contains(text) || text.isEmpty() || undoStack.isEmpty() || text.endsWith(" ")) {
			return;
		}
		final String[] textStringArray = gettextArray(text);
		final String[] stringStackArray = undoStack.get(undoStack.size() - 1).split(" ");
		final String lastWordInStack = stringStackArray[stringStackArray.length - 1];
		final String lastWordInText = textStringArray[textStringArray.length - 1];
		// case we have new word with space before it, add it directly
		if (stringStackArray.length < textStringArray.length) {
			undoStack.add(text);
			return;
		}
		// if the phrases don't have different of length 1, don't even compare
		final boolean shouldReplace = parcourirlastWInStk(lastWordInStack, lastWordInText);
		shouldReplace(shouldReplace);
	}

	/**
	 * shouldReplace
	 * 
	 * @param shouldReplace
	 */
	private void shouldReplace(final boolean shouldReplace) {
		if (shouldReplace) {
			undoStack.remove(undoStack.size() - 1);
			undoStack.add(text);
		} else {
			undoStack.add(text);
		}
	}

	/**
	 * getText
	 * 
	 * @return text the text to be returned
	 */
	public String getText() {
		return text;
	}
}