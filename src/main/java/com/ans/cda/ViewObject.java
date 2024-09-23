package com.ans.cda;

import javafx.scene.image.ImageView;

/**
 * ViewObject
 */
public class ViewObject {
	
	/**
	 * row
	 */
	private int row;
	/**
	 * image
	 */
	private ImageView image;
	/**
	 * fileName
	 */
	private String fileName;

	/**
	 * ViewObject
	 */
	public ViewObject() {
	}

	/**
	 * ViewObject
	 * 
	 * @param image
	 * @param fileName
	 */
	public ViewObject( final int row, final ImageView image, final String fileName) {
		this.image = image;
		this.fileName = fileName;
		this.setRow(row);
	}

	/**
	 * @return the image
	 */
	public ImageView getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(final ImageView image) {
		this.image = image;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

}
