package com.ans.cda.smallundoengine;

/**
 * acts as a connector between the MainController class and the Edit class
 * 
 * @author bensa Nizar
 */
public class EditorTextHistory implements IEdit {

	/**
	 * edit
	 */
	private final EditImpl edit = new EditImpl();

	/**
	 * EditorTextHistory
	 * 
	 * @param text
	 */
	public EditorTextHistory(final String text) {
		edit.setText(text);
	}

	/**
	 * EditorTextHistory
	 */
	public EditorTextHistory() {
		// empty constructor
	}

	/**
	 * getText
	 * 
	 * @return
	 */
	public String getText() {
		return edit.getText();
	}

	/**
	 * update
	 * 
	 * @param newText
	 */
	public void update(final String newText) {
		edit.setText(newText);
	}

	/**
	 * undo
	 */
	@Override
	public void undo() {
		edit.undo();
	}

	/**
	 * redo
	 */
	@Override
	public void redo() {
		edit.redo();
	}
}