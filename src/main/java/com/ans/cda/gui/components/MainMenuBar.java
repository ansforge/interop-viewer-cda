package com.ans.cda.gui.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xml.sax.SAXException;

import com.ans.cda.Constant;
import com.ans.cda.Handler;
import com.ans.cda.XslFileObj;
import com.ans.cda.gui.mediator.Events;
import com.ans.cda.gui.mediator.IMediator;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.lib.EditorUtils;
import com.ans.cda.smallundoengine.EditorTextHistory;
import com.ans.cda.utilities.INUtility;
import com.ans.cda.utilities.Utilities;
import com.ans.cda.utilities.Utilities2;
import com.ans.cda.utilities.Utilities3;
import com.ans.cda.utilities.Utilities4;
import com.ans.cda.utilities.Utilities5;
import com.ans.cda.utilities.Utilities6;
import com.ans.cda.utilities.Utilities7;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.DataMatrixEncodeMode;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.skin.TextAreaSkin;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main Menu Bar class
 * 
 * @author bensa Nizar
 */
public class MainMenuBar extends MenuBar {
	/**
	 * undo
	 */
	@FXML
	private MenuItem undo;
	/**
	 * save
	 */
	@FXML
	private MenuItem save;
	/**
	 * redo
	 */
	@FXML
	private MenuItem redo;
	/**
	 * close
	 */
	@FXML
	private MenuItem close;
	/**
	 * newTab
	 */
	@FXML
	private MenuItem newTab;
	/**
	 * open
	 */
	@FXML
	private MenuItem open;
	/**
	 * copy
	 */
	@FXML
	private MenuItem copy;
	/**
	 * cut
	 */
	@FXML
	private MenuItem cut;
	/**
	 * paste
	 */
	@FXML
	private MenuItem paste;
	/**
	 * findAndReplace
	 */
	@FXML
	private MenuItem findAndReplace;
	/**
	 * find
	 */
	@FXML
	private MenuItem find;
	/**
	 * about
	 */
	@FXML
	private MenuItem about;
	/**
	 * tree
	 */
	@FXML
	private MenuItem tree;
	/**
	 * view
	 */
	@FXML
	private MenuItem view;
	/**
	 * mediator
	 */
	private final IMediator mediator = Mediator.getInstance();
	/**
	 * secondStage
	 */
	private Stage secondStage;
	/**
	 * textAr
	 */
	private final TextArea textAr = new TextArea();
	/**
	 * xslLine
	 */
	private String xslLine;
	/**
	 * xslLineAutoP
	 */
	private String xslLineAutoP;
	/**
	 * browser
	 */
	private final WebView browser = new WebView();
	/**
	 * webEngine
	 */
	private final WebEngine webEngine = Utilities3.getEngine(browser);
	/**
	 * stage
	 */
	private static Stage stage = new Stage();
	/**
	 * count
	 */
	private Integer count = 1;
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(MainMenuBar.class);
	/**
	 * obj
	 */
	private static final String OBJ = null;

	/**
	 * constructor
	 */
	public MainMenuBar() {
		super();
		final Locale local = INUtility.getLocale();
		final ResourceBundle bundle = ResourceBundle.getBundle("properties/messages", local);
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/menubar.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
			// keybord controls
			save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
			copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
			paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
			cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
			find.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
			undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
			redo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
			findAndReplace.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
			close.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
			about.setAccelerator(new KeyCodeCombination(KeyCode.F1));
		} catch (final IOException ex) {
			if (LOG.isInfoEnabled()) {
				final String error = ex.getMessage();
				LOG.error(error);
			}
		}
	}

	/**
	 * viewMenuItemClick
	 * 
	 * @param event
	 */
	@FXML
	protected void viewMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.VIEW).build();
		final String xmlStr = Utilities2.getMediatorFilePath(mediator).toString();
		// Start ProgressBar creation
		final double wndwWidth = 150.0d;
		final double wndhHeigth = 150.0d;
		final ProgressIndicator progress = new ProgressIndicator();
		progress.setMinWidth(wndwWidth);
		progress.setMinHeight(wndhHeigth);
		progress.setProgress(0.25F);
		final VBox updatePane = new VBox();
		updatePane.setPadding(new Insets(10));
		updatePane.setSpacing(5.0d);
		updatePane.setAlignment(Pos.CENTER);
		Utilities.getChildrenNode(updatePane).addAll(progress);
		updatePane.setStyle("-fx-background-color: #edf2f4");
		final Stage taskUpdateStage = new Stage(StageStyle.UNDECORATED);
		taskUpdateStage.setScene(new Scene(updatePane, 170, 170));
		// call readFileContents method
		readFileContents(new File(xmlStr));
		if (xslLine != null && !xslLine.isEmpty()) {
			final int start = xslLine.indexOf("href=\"") + "href=\"".length();
			final int end = xslLine.indexOf("\"?>");
			final String outStr = xslLine.substring(start, end);
			final String xmlFileParent = new File(xmlStr).getParentFile().getParentFile().getAbsolutePath();
			final XslFileObj xslFileObj = new XslFileObj();
			xslFileObj.setOutStr(outStr);
			xslFileObj.setXmlFileParent(xmlFileParent);
			xslFileObj.setXmlStr(xmlStr);
			xslFileObj.setXslLineAutoP(xslLineAutoP);
			final File xslFile = Utilities7.createXslFile(xslFileObj);
			if (xslFile.exists()) {
				Utilities7.runTask(taskUpdateStage, progress);
				Platform.runLater(() -> {
					final Popup popup = new Popup();
					// create temp html file
					Path file1;
					File file;
					byte[] decoder;
					Element image;
					String name;
					List<File> lfiles;
					try {
						file1 = Files.createTempFile(null, Constant.HTMLEXT);
						final Path stream = Utilities6.transform(xslFile.getAbsolutePath(), xmlStr, file1);
						// Writes a string to the above temporary file
						final File url1 = new File(stream.toString());
						webEngine.load(url1.toURI().toString());
						// retrieving the observable list of the VBox
						final ObservableList<Node> list = Utilities3.getPopupContent(popup);
						// Adding all the nodes to the observable list
						list.addAll(browser);
						// Start DATAMATRIX
						final InputStream targetStream = Files.newInputStream(Paths.get(url1.toURI()));
						final Document doc = Jsoup.parse(targetStream, Constant.UTF8, "");
						// Get Element datamatrix for make some changes
						final Collection<Element> contents = doc.getElementsByClass("barcodeStyle");
						for (final Element content : contents) {
							if (content != null) {
								final String dataMatrixValue = content.val();
								if (dataMatrixValue != null && !dataMatrixValue.isEmpty()) {
									// Initialiser un objet de la classe BarcodeGenerator
									final BarcodeGenerator gen = Utilities2.getBarcode(dataMatrixValue);
									// D�finir les pixels
									Utilities2.getXDimension(gen).setPixels(4);
									// R�glez le mode d'encodage sur Auto
									Utilities2.getDataMatrix(gen).setDataMatrixEncodeMode(DataMatrixEncodeMode.AUTO);
									final Path matrix = Files.createTempFile(null, Constant.PNGEXT);
									gen.save(matrix.toString(), BarCodeImageFormat.PNG);
									final Element img = Utilities2.getElement(matrix);
									content.appendChild(img);
									content.val("");
									content.attr("style", "padding:0px 35px;");
								}
							}
						}
						// Start PDF
						final Collection<Element> iframes = doc.select("iframe,object");
						int jCounter = 0;
						for (final Element iframeChild : iframes) {
							if (iframeChild != null) {
								final String source = iframeChild.attr(Constant.SRC);
								final String source1 = iframeChild.attr(Constant.DATA);
								final String find = Constant.B64;
								final boolean isFound = source.contains(Constant.B64);
								final boolean isFound1 = source1.contains(Constant.B64);
								int index = 0;
								String str1 = null;
								if (isFound) {
									index = source.indexOf(find);
									str1 = source.substring(index + 7);
								}
								if (isFound1) {
									index = source1.indexOf(find);
									str1 = source1.substring(index + 7);
								}
								if (isFound || isFound1) {
									if (index > 0) {
										if (str1.contains(Constant.SPC1)) {
											str1 = str1.replaceAll(Constant.SPC1, "");
										}
										if (str1.contains(Constant.SPC2)) {
											str1 = str1.replaceAll(Constant.SPC2, "");
										}
										final String b64 = str1.replaceAll(" ", "");
										file = File.createTempFile("tmp", Constant.PDFEXT);
										if (file.exists()) {
											file.delete();
										}
										file = File.createTempFile("tmp", Constant.PDFEXT);
										try (OutputStream fos = Files.newOutputStream(Paths.get(file.toURI()))) {
											decoder = Base64.getDecoder().decode(b64);
											fos.write(decoder);
										}
										final String nom = iframeChild.attr(Constant.NAME);
										final String ident = iframeChild.attr(Constant.IDENTIFIANT);
										final int length = ident.length();
										final int lengthName = nom.length();
										final String fileName = Utilities7.compareInt(length, lengthName, jCounter, nom,
												ident);
										final String path = file.getParentFile() + "\\" + fileName + ".pdf";
										if (Utilities.getFile(path).exists()) {
											Utilities.getFile(path).delete();
										}
										final File newFile = Utilities.getFile(path);
										FileUtils.copyFile(file, newFile);
										// PDF to Images
										lfiles = Utilities6.generateImageFromPDF(file);
										Utilities6.mergeImage(lfiles);
										final String outputPath = file.getParent().concat("\\merged.png");
										if (!lfiles.isEmpty()) {
											name = Utilities.getAbsolutePath(outputPath);
											image = Utilities4.getElement(Constant.IMG).attr(Constant.SRC,
													"file:///" + name);
											iframeChild.replaceWith(image);
										}
										// END PDF to Images
									}
									jCounter = jCounter + 1;
								}
							}
						}
						// FIN DATAMATRIX
						final String docHtml = doc.html();
						final PrintWriter printWriter = new PrintWriter(url1, Constant.UTF8);
						printWriter.print("");
						printWriter.print(docHtml);
						printWriter.close();
						webEngine.load(new File(url1.getPath()).toURI().toURL().toString());
						browser.prefHeightProperty().bind(stage.heightProperty());
						browser.prefWidthProperty().bind(stage.widthProperty());
						final VBox layout = new VBox();
						final Scene scene = new Scene(layout);
						Utilities.getChildrenNode(layout).addAll(browser);
						stage.setScene(scene);
						stage.setMaximized(true);
						stage.setMaxWidth(Integer.MAX_VALUE);
						stage.setMaxHeight(Integer.MAX_VALUE);
						stage.setTitle(xmlStr);
						Utilities3.getIcons(stage)
								.add(new Image(Utilities3.getContextClassLoader().getResourceAsStream(Constant.PHOTO)));
						stage.setOnCloseRequest(eventt -> {
							eventt.consume();
							if (stage != null) {
								popup.hide();
								stage.close();
								setXslLine(OBJ);
								setXslLineAutoP(OBJ);
							}
						});
						stage.showAndWait();
						// hide webview scrollbars whenever they appear.
						Utilities3.getChildrenUnmodifiable(browser).addListener(new ListChangeListener<>() {
							@Override
							public void onChanged(Change<? extends Node> change) {
								final Set<Node> nodes = browser.lookupAll(".scroll-bar");
								for (final Node node : nodes) {
									if (node instanceof ScrollBar) {
										final ScrollBar sbuilder = (ScrollBar) node;
										if (Utilities3.getOrientation(sbuilder).equals(Orientation.HORIZONTAL)) {
											sbuilder.setVisible(false);
										}
									}
								}
							}
						});
					} catch (final IOException e) {
						if (LOG.isInfoEnabled()) {
							final String error = e.getMessage();
							LOG.error(error);
						}
					}
				});
			} else {
				final Alert alert = new Alert(AlertType.WARNING);
				final DialogPane dialogPane = alert.getDialogPane();
				Utilities2.getStyleDialog(dialogPane).add(getClass().getResource("/style.css").toExternalForm());
				Utilities2.getStyleClass(dialogPane).add("myDialog");
				dialogPane.setMinHeight(130);
				dialogPane.setMaxHeight(130);
				dialogPane.setPrefHeight(130);
				alert.setContentText(Utilities5.getString("popup.error8"));
				alert.setHeaderText(null);
				alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
				alert.showAndWait();
			}

		} else {
			final Alert alert = new Alert(AlertType.WARNING);
			final DialogPane dialogPane = alert.getDialogPane();
			Utilities2.getStyleDialog(dialogPane).add(getClass().getResource("/style.css").toExternalForm());
			Utilities2.getStyleClass(dialogPane).add("myDialog");
			dialogPane.setMinHeight(130);
			dialogPane.setMaxHeight(130);
			dialogPane.setPrefHeight(130);
			alert.setContentText(Utilities5.getString("popup.error8"));
			alert.setHeaderText(null);
			alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
			alert.showAndWait();
		}
	}

	/**
	 * onNewTabClick
	 * 
	 * @param event
	 * @see Mediator
	 **/
	@FXML
	protected void onNewTabClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.NEW_TAB).build();
	}

	/**
	 * 
	 * saveMenuItemClick
	 * 
	 * @param event
	 * @see Mediator
	 */
	@FXML
	protected void saveMenuItemClick(final ActionEvent event) {
		if (mediator.isFileSaved()) {
			Utilities2.getEventBuilder(mediator).withEvent(Events.AUTO_SAVE).build();
		} else {
			final Path filePath = EditorUtils.showSaveWindow(save.getParentPopup().getScene().getWindow());
			if (filePath == null) {
				return;
			}
			Utilities2.getEventBuilder(mediator).withEvent(Events.SAVE_MENU).fileSaved(true).textChanged(false)
					.withFilePath(filePath).build();

		}
	}

	/**
	 * readFileContents
	 * 
	 * @param selectedFile
	 * @throws IOException
	 */
	private String readFileContents(final File file) {
		String singleString = null;
		try (BufferedReader breader = Files.newBufferedReader(Paths.get(file.toURI()))) {
			final StringBuilder sbuilder = new StringBuilder();
			String line = breader.readLine();
			while (line != null) {
				sbuilder.append(count++).append(line).append('\n');
				line = breader.readLine();
				if (line != null && line.startsWith("<?xml-stylesheet")) {
					setXslLine(line);
				}
				if (line != null && line.startsWith("<xsl:stylesheet")) {
					setXslLineAutoP(line);
				}
			}
			singleString = sbuilder.toString();
			count = 1;
		} catch (final IOException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.error(error);
			}
		}
		return singleString;
	}

	/**
	 * treeMenuItemClick
	 * 
	 * @param event javafx event.. sends a CLOSE_MENU event to the mediator pops the
	 *              alert confirmation window
	 * @see EditorUtils
	 * @see Mediator
	 */
	@FXML
	protected void treeMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.TREE_FILE).build();
		Platform.setImplicitExit(false);
		secondStage = new Stage();
		secondStage.setOnShowing(ev -> {
			final String xmlStr = Utilities2.getMediatorFilePath(mediator).toString();
			final File file = new File(xmlStr);
			if (file != null) {
				try {
					final TreeItem<String> root = Handler.readXML(new File(xmlStr));
					final TreeView<String> treeView = new TreeView<>(root);
					final MultipleSelectionModel<TreeItem<String>> tvSelModel = Utilities4
							.getSelectionModelTV(treeView);
					Utilities5.selectedItemProperty(tvSelModel).addListener(new ChangeListener<>() {
						@Override
						public void changed(ObservableValue<? extends TreeItem<String>> changed,
								final TreeItem<String> oldVal, final TreeItem<String> newVal) {
							// Display the selection and its complete path from the root.
							int total = 0;
							textAr.setText(readFileContents(file));
							if (newVal.getValue().contains("[")) {
								final String str = newVal.getValue().substring(newVal.getValue().lastIndexOf('[') + 1);
								final String[] words = str.split("]");

								String strFinal = null;
								for (final String word : words) {
									strFinal = word;
								}

								// Define desired line
								final int line = Integer.parseInt(strFinal);
								final String[] lines = textAr.getText().split("\n");
								for (Integer i = 0; i < line; i++) {
									final int numChars = lines[i].length();
									total += numChars;
								}

								final Rectangle2D lineBounds = ((TextAreaSkin) Utilities5.getSkin(textAr))
										.getCharacterBounds(total);
								textAr.setScrollTop(lineBounds.getMinY());

							} else {
								if (newVal.getParent().getValue().contains("[")) {
									final String str = newVal.getParent().getValue()
											.substring(newVal.getParent().getValue().lastIndexOf('[') + 1);
									final String[] words = str.split("]");

									String strFinal = null;
									for (final String word : words) {
										strFinal = word;
									}
									// Define desired line
									final int line = Integer.parseInt(strFinal);
									final String[] lines = textAr.getText().split("\n");
									for (Integer i = 0; i < line; i++) {
										final int numChars = lines[i].length();
										total += numChars;

									}

									final Rectangle2D lineBounds = ((TextAreaSkin) Utilities5.getSkin(textAr))
											.getCharacterBounds(total);
									textAr.setScrollTop(lineBounds.getMinY());
								}
							}
						}
					});
					final StackPane stack = new StackPane();
					textAr.setEditable(true);
					textAr.setPrefSize(1000, 1000);
					textAr.setText(readFileContents(file));
					Utilities4.getStyleClassSP(stack).add(Constant.STACKPANE);
					stack.setPadding(new Insets(5));
					Utilities5.getChildSPane(stack).addAll(treeView);
					final HBox listHeaderBox2 = new HBox();
					listHeaderBox2.setAlignment(Pos.BASELINE_RIGHT);
					Utilities.getChildrenHNode(listHeaderBox2).add(textAr);
					final StackPane sp5 = new StackPane();
					Utilities4.getStyleClassSP(sp5).add(Constant.STACKPANE);
					Utilities5.getChildSPane(sp5).add(listHeaderBox2);
					final SplitPane sp3 = new SplitPane();
					Utilities5.getStyleClassSplP(sp3).add(Constant.STACKPANE);
					Utilities.getItemsSplit(sp3).addAll(sp5);
					final StackPane sp2 = new StackPane();
					Utilities4.getStyleClassSP(sp2).add(Constant.STACKPANE);
					Utilities5.getChildSPane(sp2).add(stack);
					final SplitPane splitP = new SplitPane();
					Utilities5.getStyleClassSplP(splitP).add(Constant.STACKPANE);
					Utilities.getItemsSplit(splitP).addAll(sp2, sp3);
					final Scene scene = new Scene(splitP);
					Utilities2.getStyleScene(scene).add(MainMenuBar.class.getResource(Constant.CSS).toExternalForm());
					Platform.runLater(() -> {
						secondStage.setScene(scene);
						secondStage.setTitle(file.getAbsolutePath());
						secondStage.setMaxWidth(Integer.MAX_VALUE);
						secondStage.setMaxHeight(Integer.MAX_VALUE);
						secondStage.setMaximized(true);
						Utilities3.getIcons(secondStage)
								.add(new Image(Utilities3.getContextClassLoader().getResourceAsStream(Constant.PHOTO)));
					});
					scene.widthProperty().addListener(new ChangeListener<>() {
						@Override
						public void changed(final ObservableValue<? extends Number> observableValue,
								final Number oldSceneWidth, final Number newSceneWidth) {
							if (newSceneWidth.doubleValue() >= 1509 && newSceneWidth.doubleValue() < 1632) { // ecran 16
																												// pouces
								textAr.setStyle(
										"-fx-font-size:14pt; -fx-font-weight:normal; -fx-font-family:Monaco, 'Courier New', MONOSPACE; -fx-background-color: #e9e4e6;");
							} else if (newSceneWidth.doubleValue() >= 1632 && newSceneWidth.doubleValue() >= 1728) { // ecran
																														// 17
																														// pouces
								textAr.setStyle(
										"-fx-font-size:16pt; -fx-font-weight:normal; -fx-font-family:Monaco, 'Courier New', MONOSPACE; -fx-background-color: #e9e4e6;");
							} else if (newSceneWidth.doubleValue() < 1509 && newSceneWidth.doubleValue() > 0) {
								textAr.setStyle(
										"-fx-font-size:10pt; -fx-font-weight:normal; -fx-font-family:Monaco, 'Courier New', MONOSPACE; -fx-background-color: #e9e4e6;");
							}
						}
					});
				} catch (final IOException | SAXException e2) {
					if (LOG.isInfoEnabled()) {
						final String error = e2.getMessage();
						LOG.error(error);
					}
				}
			}
		});
		secondStage.setOnCloseRequest(ev -> {
			ev.consume();
			secondStage.close();
		});
		secondStage.show();
	}

	/**
	 * closeMenuItemClick
	 * 
	 * @param event
	 */
	@FXML
	protected void closeMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.CLOSE_MENU).build();
		final Stage stage = mediator.getStageTitle();
		EditorUtils.onCloseExitConfirmation(stage);
	}

	/**
	 * undoMenuItemClick
	 * 
	 * @param event
	 * @see Mediator
	 * @see EditorTextHistory
	 */
	@FXML
	protected void undoMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.UNDO_TEXT).build();
	}

	/**
	 * redoMenuItemClick
	 * 
	 * @param event
	 * @see Mediator
	 * @see EditorTextHistory
	 */
	@FXML
	protected void redoMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.REDO_TEXT).build();
	}

	/**
	 * copyMenuItemClick
	 * 
	 * @param event
	 * @see Mediator
	 */
	@FXML
	protected void copyMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.COPY_MENU).build();
		final Map<DataFormat, Object> hashMap = new ConcurrentHashMap<>();
		hashMap.put(DataFormat.PLAIN_TEXT, mediator.getMediatorText());
		Clipboard.getSystemClipboard().setContent(hashMap);
	}

	/**
	 * cutMenuItemClick
	 * 
	 * @param event
	 * @see Mediator
	 */
	@FXML
	protected void cutMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.CUT_MENU).build();
		final Map<DataFormat, Object> hashMap = new ConcurrentHashMap<>();
		hashMap.put(DataFormat.PLAIN_TEXT, mediator.getMediatorText());
		Clipboard.getSystemClipboard().setContent(hashMap);
	}

	/**
	 * pasteMenuItemClick
	 * 
	 * @param event
	 * @see Mediator
	 */
	@FXML
	protected void pasteMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.PASTE_MENU).build();
	}

	/**
	 * findAndReplaceMenuItemClick
	 * 
	 * @param event
	 */
	@FXML
	protected void findAndReplaceMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.SHOW_FIND_REPLACE).build();
	}

	/**
	 * findMenuItemClick
	 * 
	 * @param event
	 */
	@FXML
	protected void findMenuItemClick(final ActionEvent event) {
		Utilities2.getEventBuilder(mediator).withEvent(Events.SHOW_FIND).build();
	}

	/**
	 * shows the version information of the app
	 *
	 * @see EditorUtils#showAboutWindow(MainMenuBar)
	 */
	@FXML
	protected void aboutMenuItemClick(ActionEvent event) {
		EditorUtils.showAboutWindow(this);
		Utilities2.getEventBuilder(mediator).withEvent(Events.ABOUT_MENU).build();
	}

	/**
	 * sets the current menubar in mediator to this instance
	 *
	 * @see Mediator
	 */
	@FXML
	public void initialize() {
		mediator.setMenuBar(this);
	}

	/**
	 * @return the secondStage
	 */
	public Stage getSecondStage() {
		return secondStage;
	}

	/**
	 * @return the stage
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * @param xslLine the xslLine to set
	 */
	public void setXslLine(final String xslLine) {
		this.xslLine = xslLine;
	}

	/**
	 * @param xslLineAutoP the xslLineAutoP to set
	 */
	public void setXslLineAutoP(final String xslLineAutoP) {
		this.xslLineAutoP = xslLineAutoP;
	}
}