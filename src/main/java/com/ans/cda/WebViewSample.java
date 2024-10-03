package com.ans.cda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ans.cda.gui.components.MainMenuBar;
import com.ans.cda.gui.mediator.IMediator;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.lib.EditorUtils;
import com.ans.cda.utilities.INUtility;
import com.ans.cda.utilities.INUtility2;
import com.ans.cda.utilities.Utilities;
import com.ans.cda.utilities.Utilities2;
import com.ans.cda.utilities.Utilities3;
import com.ans.cda.utilities.Utilities4;
import com.ans.cda.utilities.Utilities5;
import com.ans.cda.utilities.Utilities6;
import com.ans.cda.utilities.Utilities7;
import com.ans.cda.utilities.Utils;
import com.ans.cda.utilities.Utils1;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.sun.webkit.WebPage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * WebViewSample api with JavaFX
 * 
 * @author bensalem Nizar
 */
public class WebViewSample extends Application {
	/**
	 * browser
	 */
	private final WebView browserEngine = new WebView();
	/**
	 * webEngine
	 */
	private final WebEngine webEngine = browserEngine.getEngine();

	/**
	 * outputFileName
	 */
	private String outputFileName = "";
	/**
	 * xmlButton
	 */
	private Button xmlButton = INUtility2.buttonForKey("button.selectcda");

	/**
	 * xslButton
	 */
	private Button xslButton = INUtility2.buttonForKey("button.selectxsl");
	/**
	 * validateButton
	 */
	private Button validateButton = INUtility2.buttonForKey("button.displaycda");
	/**
	 * openButton
	 */
	private Button openButton = INUtility2.buttonForKey("button.opennav");
	/**
	 * pdfButton
	 */
	private Button pdfButton = INUtility2.buttonForKey("button.generatepdf");
	/**
	 * tccButton
	 */
	private Button tccButton = INUtility2.buttonForKey("button.validation");
	/**
	 * showXmlButton
	 */
	private Button showXmlButton = INUtility2.buttonForKey("button.showcda");
	/**
	 * clearButton
	 */
	private Button clearButton = INUtility2.buttonForKey("button.initinterface");
	/**
	 * openWithButton
	 */
	private Button openWithButton = INUtility2.buttonForKey("button.openwith");
	/**
	 * button
	 */
	private final Button button = INUtility2.buttonForKey("button.search.text");
	/**
	 * radio1
	 */
	private final RadioButton radio1 = INUtility2.radioForKey("button.autopresentable");
	/**
	 * radio2
	 */
	private final RadioButton radio2 = INUtility2.radioForKey("button.crbio");
	/**
	 * buttonX
	 */
	private final Button buttonX = new Button("x");
	/**
	 * textField
	 */
	private TextField textField = new TextField();
	/**
	 * textFieldxsl
	 */
	public TextField textFieldxsl = new TextField();
	/**
	 * primaryStage
	 */
	private Stage primaryStage;
	/**
	 * thirdStage
	 */
	private final Stage thirdStage = new Stage();
	/**
	 * menuBar for browser
	 */
	private final MenuBar menuBar = new MenuBar();
	/**
	 * mediator
	 */
	private final IMediator mediator = Mediator.getInstance();
	/**
	 * map
	 */
	private final Map<String, String> map = new ConcurrentHashMap<>();
	/**
	 * mapXsl
	 */
	private final Map<String, String> mapXsl = new ConcurrentHashMap<>();
	/**
	 * history
	 */
	private final WebHistory history = Utilities3.getHistory(browserEngine);
	/**
	 * exist
	 */
	private boolean exist;
	/**
	 * isAutopresentable
	 */
	private boolean isAutopresentable;
	/**
	 * isAutopresentable
	 */
	private boolean isAutopresentablePdf;
	/**
	 * fileChoose
	 */
	private File fileChoose;
	/**
	 * imageViewAns
	 */
	private ImageView imageViewAns;
	/**
	 * buttonIt
	 */
	private final Button buttonIt = INUtility2.buttonForKey("button.italian");
	/**
	 * buttonPt
	 */
	private final Button buttonPt = INUtility2.buttonForKey("button.portugais");
	/**
	 * buttonEs
	 */
	private final Button buttonEs = INUtility2.buttonForKey("button.espagnol");
	/**
	 * buttonNl
	 */
	private final Button buttonNl = INUtility2.buttonForKey("button.nl");
	/**
	 * buttonDeutch
	 */
	private final Button buttonDeutch = INUtility2.buttonForKey("button.deutch");
	/**
	 * buttonFrensh
	 */
	private final Button buttonFrensh = INUtility2.buttonForKey("button.frensh");
	/**
	 * buttonEnglish
	 */
	private final Button buttonEnglish = INUtility2.buttonForKey("button.english");
	/**
	 * boxCombo
	 */
	private final HBox boxCombo = new HBox();
	/**
	 * tmpFile
	 */
	private File tmpFile;
	/**
	 * isCrbio
	 */
	private boolean isCrbio;
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(WebViewSample.class);

	/**
	 * void main for Javafx launcher Main secondaire de l'application de javaFX
	 * 
	 * @param args
	 */

	public static void main(final String args[]) {
		launch(args);
	}

	/**
	 * disableButton
	 */
	private void disableButton() {
		buttonIt.setDisable(true);
		buttonPt.setDisable(true);
		buttonEs.setDisable(true);
		buttonNl.setDisable(true);
		buttonDeutch.setDisable(true);
		buttonFrensh.setDisable(true);
		buttonEnglish.setDisable(true);
		browserEngine.setDisable(true);
		xmlButton.setDisable(true);
		xslButton.setDisable(true);
		validateButton.setDisable(true);
		openButton.setDisable(true);
		tccButton.setDisable(true);
		pdfButton.setDisable(true);
		showXmlButton.setDisable(true);
		clearButton.setDisable(true);
		openWithButton.setDisable(true);
		textField.setDisable(true);
		textFieldxsl.setDisable(true);
		menuBar.setDisable(true);
		imageViewAns.setDisable(true);
		radio1.setDisable(true);
		radio2.setDisable(true);
	}

	/**
	 * enableButton
	 */
	private void enableButton() {
		buttonIt.setDisable(false);
		buttonPt.setDisable(false);
		buttonEs.setDisable(false);
		buttonNl.setDisable(false);
		buttonDeutch.setDisable(false);
		buttonFrensh.setDisable(false);
		buttonEnglish.setDisable(false);
		browserEngine.setDisable(false);
		xmlButton.setDisable(false);
		validateButton.setDisable(false);
		openButton.setDisable(false);
		pdfButton.setDisable(false);
		showXmlButton.setDisable(false);
		clearButton.setDisable(false);
		openWithButton.setDisable(false);
		tccButton.setDisable(false);
		textField.setDisable(false);
		menuBar.setDisable(false);
		imageViewAns.setDisable(false);
		radio1.setDisable(false);
		radio2.setDisable(false);
		if (isAutopresentable) {
			textFieldxsl.setDisable(true);
			xslButton.setDisable(true);
		} else {
			textFieldxsl.setDisable(false);
			xslButton.setDisable(false);
		}
	}

	/**
	 * loading in api
	 */
	public void runTask(final Stage taskUpdateStage, final ProgressIndicator progress) {
		disableButton();
		final Task<Void> longTask = new Task<>() {
			@Override
			protected Void call() throws Exception {
				final int max = 20;
				for (int i = 1; i <= max; i++) {
					if (isCancelled()) {
						break;
					}
					updateProgress(i, max);
					updateMessage(Constant.TASK_PART + i + Constant.COMPLETE);
				}
				return null;
			}
		};
		longTask.setOnSucceeded(new EventHandler<>() {
			@Override
			public void handle(final WorkerStateEvent event) {
				taskUpdateStage.hide();
				enableButton();
			}
		});
		progress.progressProperty().bind(longTask.progressProperty());
		taskUpdateStage.show();
		new Thread(longTask).start();
	}

	/**
	 * start stage
	 * 
	 * @param stage
	 */
	@Override
	public void start(final Stage stage) {
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
		updatePane.setStyle("-fx-background-color: transparent");
		final Stage taskUpdateStage = new Stage(StageStyle.UNDECORATED);
		taskUpdateStage.setScene(new Scene(updatePane, 170, 170));
		// End progressBar
		// creating a text field
		textField = new TextField();
		// creating a text field
		textFieldxsl = new TextField();
		textField.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIP));
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null || newValue.isEmpty()) {
				// Tooltip xml
				textField.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIP));
			}
		});
		textFieldxsl.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIPXSL));
		textFieldxsl.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null || newValue.isEmpty()) {
				// Tooltip xml
				textFieldxsl.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIPXSL));
			}
		});
		final ToggleGroup group = new ToggleGroup();
		group.selectedToggleProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> over, Toggle oldToggle, Toggle newToggle) {
				// Has selection.
				if (group.getSelectedToggle() != null) {
					final RadioButton button = (RadioButton) group.getSelectedToggle();
					if (button.getText().contains(Constant.CRBIO)) {
						final File file = new File(textField.getText());
						if (file != null && file.getParentFile() != null
								&& file.getParentFile().getParentFile() != null) {
							final File xslFile = file.getParentFile().getParentFile();
							final String filePath = xslFile.getAbsolutePath().concat(Constant.PATH_FDS)
									.concat(Constant.CDACRBIO);
							textFieldxsl.setText(filePath);
							final String tmpdir = System.getProperty("java.io.tmpdir");
							tmpFile = new File(tmpdir + Constant.TEST);
							if (tmpFile.exists()) {
								tmpFile.delete();
							}
							try {
								final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
								dbf.setNamespaceAware(true);
								final DocumentBuilder builder = dbf.newDocumentBuilder();
								final File target = new File(textField.getText());
								final org.w3c.dom.Document targetDom = builder.parse(
										new InputSource(Files.newBufferedReader(Paths.get(target.getAbsolutePath()))));
								final org.w3c.dom.Node targetSections = targetDom
										.getElementsByTagNameNS("*", "ClinicalDocument").item(0);
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:xsl",
										"http://www.w3.org/1999/XSL/Transform");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:exsl",
										"http://exslt.org/common");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:xsi",
										"http://www.w3.org/2001/XMLSchema-instance");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:data",
										"urn:asip-sante:ci-sis");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:c", "urn:hl7-org:v3");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:lab",
										"urn:oid:1.3.6.1.4.1.19376.1.3.2");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:ds",
										"http://www.w3.org/2000/09/xmldsig#");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:xad",
										"http://uri.etsi.org/01903/v1.2.2#");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:xd",
										"http://www.oxygenxml.com/ns/doc/xsl");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:fo",
										"http://www.w3.org/1999/XSL/Format");
								((org.w3c.dom.Element) targetSections).setAttribute("xmlns:fox",
										"http://xmlgraphics.apache.org/fop/extensions");
								((org.w3c.dom.Element) targetSections).setAttribute("xsi:schemaLocation",
										"urn:hl7-org:v3 ../infrastructure/cda/CDA_extended.xsd");
								final Transformer tform = TransformerFactory.newInstance().newTransformer();
								tform.setOutputProperty(OutputKeys.INDENT, "yes");
								tform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
								tform.transform(new DOMSource(targetSections), new StreamResult(tmpFile));
							} catch (final ParserConfigurationException | SAXException | IOException
									| TransformerFactoryConfigurationError | TransformerException e) {
								if (LOG.isInfoEnabled()) {
									final String error = e.getMessage();
									LOG.error(error);
								}
							}
						}
					} else {
						textFieldxsl.setText(textField.getText());
					}
				}
			}
		});
		radio1.setToggleGroup(group);
		radio1.setSelected(true);
		radio1.setVisible(false);
		radio2.setToggleGroup(group);
		radio2.setVisible(false);
		final HBox hBoxint = new HBox();
		final GridPane gridPane3 = new GridPane();
		// Setting size for the pane
		gridPane3.setMinSize(10, 10);
		// Setting the padding
		gridPane3.setPadding(new Insets(0, 10, 0, 10));
		// Setting the vertical and horizontal gaps between the columns
		gridPane3.setVgap(1);
		gridPane3.setHgap(10);
		// Setting the Grid alignment
		gridPane3.setAlignment(Pos.BASELINE_LEFT);
		// Arranging all the nodes in the grid
		gridPane3.add(radio1, 0, 1);
		gridPane3.add(radio2, 1, 1);
		gridPane3.setMinSize(hBoxint.getMinWidth(), hBoxint.getMinHeight());
		// filechooser
		final FileChooser fileChooser = new FileChooser();
		Utilities.newExtFilter(fileChooser).add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
		final FileChooser fileChooserxsl = new FileChooser();
		Utilities.newExtFilter(fileChooserxsl).add(new FileChooser.ExtensionFilter("XSL files (*.xsl)", "*.xsl"));
		// Creating the xml button
		xmlButton = INUtility2.buttonForKey("button.selectcda");
		final ImageView view = new ImageView(Constant.XMLPHOTO);
		xmlButton.setGraphic(view);
		xmlButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				Platform.runLater(() -> {
					final File file = fileChooser.showOpenDialog(stage);
					if (file != null) {
						fileChoose = file;
						if (fileChoose != null) {
							textField.clear();
							textFieldxsl.setDisable(false);
							if (radio1 != null) {
								radio1.setSelected(true);
							}
							final File defaultDir = fileChoose.getParentFile().getParentFile();
							final String pathFile = defaultDir.getAbsolutePath().concat(Constant.PATH_FDS);
							if (new File(pathFile).exists()) {
								fileChooserxsl.setInitialDirectory(new File(pathFile));
							} else {
								fileChooserxsl.setInitialDirectory(defaultDir);
							}
						}
						final List<File> files = Arrays.asList(file);
						Utilities6.printLog(textField, files);
						// creating a constructor of file class and parsing an XML file
						final File fileXml = new File(file.getAbsolutePath());
						// an instance of factory that gives a document builder
						final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						// an instance of builder to parse the specified xml file
						DocumentBuilder dBuilder;
						try {
							dBuilder = dbf.newDocumentBuilder();
							final org.w3c.dom.Document doc = dBuilder.parse(fileXml);
							doc.getDocumentElement().normalize();
							final NodeList nodeList = doc.getChildNodes();
							boolean trouve = false;
							for (int itr = 0; itr < nodeList.getLength(); itr++) {
								final org.w3c.dom.Node node = nodeList.item(itr);
								if (Constant.STYLESHEET.equals(node.getNodeName())) {
									final NodeList nList = doc.getElementsByTagName(Constant.STYLESHEET);
									for (int i = 0; i < nList.getLength(); i++) {
										final org.w3c.dom.Node nNode = nList.item(i);
										final org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
										if (eElement.getAttribute("xmlns:fo").length() > 0) {
											exist = true;
										} else {
											exist = false;
										}
										final NodeList nodes = nNode.getChildNodes();
										if (nodes != null) {
											for (int j = 0; j < nodes.getLength(); j++) {
												final org.w3c.dom.Node nod = nodes.item(j);
												final NodeList nodes1 = nod.getChildNodes();
												if (nodes1 != null) {
													for (int k = 0; k < nodes1.getLength(); k++) {
														final org.w3c.dom.Node nodd = nodes1.item(k);
														final NodeList nodes2 = nodd.getChildNodes();
														if (nodes2 != null) {
															for (int l = 0; l < nodes2.getLength(); l++) {
																final org.w3c.dom.Node noddd = nodes2.item(l);
																if (noddd.getNodeName().contains(Constant.CODE)) {
																	final NamedNodeMap map = noddd.getAttributes();
																	final String value = map.getNamedItem(Constant.CODE)
																			.getNodeValue();
																	if (value.equals(Constant.VALUECODE)) {
																		isCrbio = true;
																	} else {
																		isCrbio = false;
																	}
																}
															}
														}
													}
												}

											}
										}
									}
									isAutopresentable = true;
									isAutopresentablePdf = true;
									textFieldxsl.setText(textField.getText());
									if (isCrbio) {
										radio1.setVisible(true);
										radio2.setVisible(true);
									} else {
										radio1.setVisible(false);
										radio2.setVisible(false);
									}
									xslButton.setDisable(true);
									textFieldxsl.setDisable(true);
									trouve = true;
									break;
								} else {
									isAutopresentable = false;
									isAutopresentablePdf = false;
									radio1.setVisible(false);
									radio2.setVisible(false);
								}
							}
							if (!trouve) {
								textFieldxsl.clear();
								xslButton.setDisable(false);
								textFieldxsl.setDisable(false);
							}
							final Tooltip tooltip = new Tooltip();
							tooltip.setText(textField.getText());
							textField.setTooltip(tooltip);
						} catch (final ParserConfigurationException | SAXException | IOException e) {
							if (LOG.isInfoEnabled()) {
								final String error = e.getMessage();
								LOG.error(error);
							}
						}
					} else {
						textField.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIP));
					}
				});
			}
		});
		// Creating the xml button
		xslButton = INUtility2.buttonForKey("button.selectxsl");
		final ImageView viewxsl = new ImageView(Constant.XSLPHOTO);
		xslButton.setGraphic(viewxsl);
		xslButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				final File file = fileChooserxsl.showOpenDialog(stage);
				if (file != null) {
					textFieldxsl.clear();
					final List<File> files = Arrays.asList(file);
					Utilities6.printLog(textFieldxsl, files);
					// creating a constructor of file class and parsing an XML file
					final File fileXsl = new File(file.getAbsolutePath());
					// an instance of factory that gives a document builder
					final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					// an instance of builder to parse the specified xml file
					DocumentBuilder dbuilder;
					try {
						dbuilder = dbf.newDocumentBuilder();
						final org.w3c.dom.Document doc = dbuilder.parse(fileXsl);
						doc.getDocumentElement().normalize();
						final NodeList nodeList = doc.getChildNodes();
						for (int itr = 0; itr < nodeList.getLength(); itr++) {
							final org.w3c.dom.Node node = nodeList.item(itr);
							if (Constant.STYLESHEET.equals(node.getNodeName())) {
								final NodeList nList = doc.getElementsByTagName(Constant.STYLESHEET);
								for (int i = 0; i < nList.getLength(); i++) {
									final org.w3c.dom.Node nNode = nList.item(i);
									final org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
									if (eElement.getAttribute("xmlns:fo").length() > 0) {
										exist = true;
									} else {
										exist = false;
									}
								}
								isAutopresentablePdf = true;
							} else {
								isAutopresentablePdf = false;
							}
						}
					} catch (final IOException | ParserConfigurationException | SAXException e) {
						if (LOG.isInfoEnabled()) {
							final String error = e.getMessage();
							LOG.error(error);
						}
					}
					// change tooltip textfield text
					final Tooltip tooltip = new Tooltip();
					tooltip.setText(textFieldxsl.getText());
					textFieldxsl.setTooltip(tooltip);
				} else {
					textFieldxsl.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIPXSL));
				}
			}
		});
		// Creating the stop button
		validateButton = INUtility2.buttonForKey("button.displaycda");
		final ImageView viewdoc = new ImageView(Constant.DOCPHOTO);
		validateButton.setGraphic(viewdoc);
		validateButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				if (!textFieldxsl.getText().isEmpty() && !textField.getText().isEmpty()
						&& (textFieldxsl.getText().trim().endsWith(Constant.XSLEXT) || textFieldxsl.isDisabled()
								|| textFieldxsl.getText().contains(Constant.CRBIO))
						&& (textField.getText().trim().endsWith(Constant.XMLEXT)
								|| textField.getText().trim().endsWith(Constant.XMLEXT1))) {
					// call progress bar
					runTask(taskUpdateStage, progress);
					Platform.runLater(() -> {
						// create temp html file
						Path file1;
						List<File> lfiles;
						File file = null;
						byte[] decoder;
						Element image;
						String name;
						final Path stream;
						try {
							file1 = Files.createTempFile(null, Constant.HTMLEXT);
							if (radio2.isSelected()) {
								stream = Utilities6.transform(textFieldxsl.getText(), tmpFile.getAbsolutePath(), file1);
							} else {
								stream = Utilities6.transform(textFieldxsl.getText(), textField.getText(), file1);
							}
							// Writes a string to the above temporary file
							final File url1 = new File(stream.toString());
							outputFileName = url1.getAbsolutePath();
							Utilities6.load(webEngine, url1.toURI().toString());
							browserEngine.prefHeightProperty().bind(stage.heightProperty());
							browserEngine.prefWidthProperty().bind(stage.widthProperty());
							if (radio2.isSelected()) {
								map.put(stream.toString(), tmpFile.getAbsolutePath());
							} else {
								map.put(stream.toString(), textField.getText());
							}

							mapXsl.put(stream.toString(), textFieldxsl.getText());
							// Start DATAMATRIX
							final InputStream targetStream = Files.newInputStream(Paths.get(url1.toURI()));
							final Document doc = Jsoup.parse(targetStream, Constant.UTF8, "");
							// Get Element datamatrix for make some changes
							final Collection<Element> contents = doc.getElementsByClass("barcodeStyle");
							for (final Element content : contents) {
								if (content != null) {
									final String dataMatrixValue = content.val();
									if (dataMatrixValue != null && !dataMatrixValue.isEmpty()) {
										final Map<EncodeHintType, Object> hints = new HashMap<>();
										hints.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_SQUARE);
										// Generate the DataMatrix barcode
										final BitMatrix matrix = new MultiFormatWriter().encode(dataMatrixValue,
												BarcodeFormat.DATA_MATRIX, 200, 200, hints);

										// Define the output file path
										final Path path = Files.createTempFile(null, Constant.PNGEXT);
										// Save the barcode image as PNG
										MatrixToImageWriter.writeToPath(matrix, "PNG", path);
										final Element img = Utilities2.getElement(path);
										content.appendChild(img);
										content.val("");
										content.attr("style", "padding:0px 35px;");

									}
								}
							}
							// FIN DATAMATRIX
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
											String fileName;
											final int length = ident.length();
											final int lengthName = nom.length();
											if (length > 0) {
												fileName = ident;
											} else if (length == 0 && lengthName > 0) {
												fileName = nom;
											} else {
												fileName = "Document" + jCounter;
											}
											if (Utilities.getFile(file.getParentFile() + "\\" + fileName + ".pdf")
													.exists()) {
												Utilities.getFile(file.getParentFile() + "\\" + fileName + ".pdf")
														.delete();
											}
											final String path = file.getParentFile() + "\\" + fileName + ".pdf";

											final File newFile = Utilities.getFile(path);
											FileUtils.copyFile(file, newFile);
											// PDF to Images
											lfiles = Utilities6.generateImageFromPDF(file);
											final File imgFile = Utilities6.mergeImage(lfiles);
											final String outputPath = imgFile.getAbsolutePath();
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
							// FIN PDF
							final String docHtml = doc.html();
							final PrintWriter printWriter = new PrintWriter(url1, Constant.UTF8);
							printWriter.print("");
							printWriter.print(docHtml);
							printWriter.close();
							final String urlStr = new File(url1.getPath()).toURI().toURL().toString();
							Utilities6.load(webEngine, urlStr);
							menuBar.setDisable(false);
							mediator.setWebEngine(webEngine);
						} catch (final IOException | WriterException e) {
							if (LOG.isInfoEnabled()) {
								final String error = e.getMessage();
								LOG.error(error);
							}
						} finally {
							if (file != null) {
								file.deleteOnExit();
							}
						}
					});
					// champs xml ou xsl vide --> error
				} else {
					final Alert alert = new Alert(AlertType.WARNING);
					final DialogPane dialogPane = alert.getDialogPane();
					Utilities2.getStyleDialog(dialogPane).add(getClass().getResource(Constant.CSS).toExternalForm());
					Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
					dialogPane.setMinHeight(130);
					dialogPane.setMaxHeight(130);
					dialogPane.setPrefHeight(130);
					alert.setContentText(Utilities5.getString("popup.error1"));
					alert.setHeaderText(null);
					alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
					alert.showAndWait();
					menuBar.setDisable(true);
				}
			}
		});
		// Creating the xml button
		openButton = INUtility2.buttonForKey("button.opennav");
		final ImageView viewopen = new ImageView(Constant.WWWPHOTO);
		openButton.setGraphic(viewopen);
		openButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				if (outputFileName.isEmpty()) {
					final Alert alert = new Alert(AlertType.WARNING);
					final DialogPane dialogPane = alert.getDialogPane();
					Utilities2.getStyleDialog(dialogPane).add(getClass().getResource(Constant.CSS).toExternalForm());
					Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
					dialogPane.setMinHeight(140);
					dialogPane.setMaxHeight(140);
					dialogPane.setPrefHeight(140);
					alert.setContentText(Utilities5.getString("popup.error2"));
					alert.setHeaderText(null);
					alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
					alert.showAndWait();
				} else {
					final File url1 = new File(outputFileName);
					if (url1 != null) {
						Utilities6.openFile(url1);
					}
				}
			}
		});

		final ImageView viewtcc = new ImageView(Constant.TCCPHOTO);
		tccButton.setGraphic(viewtcc);
		tccButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				Platform.runLater(() -> {
					if (textField.getText().isEmpty()) {
						final Alert alert = new Alert(AlertType.WARNING);
						final DialogPane dialogPane = alert.getDialogPane();
						Utilities2.getStyleDialog(dialogPane)
								.add(getClass().getResource(Constant.CSS).toExternalForm());
						Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
						dialogPane.setMinHeight(140);
						dialogPane.setMaxHeight(140);
						dialogPane.setPrefHeight(140);
						alert.setContentText(Utilities5.getString("popup.error3"));
						alert.setHeaderText(null);
						alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
						alert.showAndWait();
					} else {
						final File file = new File(textField.getText()).getParentFile().getParentFile();
						final String path = file.getAbsolutePath().concat(Constant.PATH_MOTEUR);
						if (new File(path).exists()) {
							try {
								final String command[] = { "java", "-jar", path, textField.getText(),
										INUtility.getLocale().toLanguageTag() };
								final ProcessBuilder pbuilder = new ProcessBuilder(command);
								pbuilder.redirectErrorStream(true);
								final Process process = pbuilder.start();
								final InputStream istream = process.getInputStream();
								// thread to handle or gobble text sent from input stream
								new Thread(() -> {
									// try with resources
									try (BufferedReader reader = new BufferedReader(new InputStreamReader(istream));) {
										String line;
										while ((line = reader.readLine()) != null) {
											LOG.info(line);
										}
									} catch (final IOException e) {
										if (LOG.isInfoEnabled()) {
											final String error = e.getMessage();
											LOG.error(error);
										}
									}
								}).start();
								// thread to get exit value from process without blocking
								Thread waitForThread = new Thread(() -> {
									try {
										process.waitFor();
									} catch (final InterruptedException e) {
										if (LOG.isInfoEnabled()) {
											final String error = e.getMessage();
											LOG.error(error);
										}
									}
								});
								waitForThread.start();
							} catch (final IOException e) {
								if (LOG.isInfoEnabled()) {
									final String error = e.getMessage();
									LOG.error(error);
								}
							}
						} else {
							final Alert alert = new Alert(AlertType.ERROR);
							final DialogPane dialogPane = alert.getDialogPane();
							Utilities2.getStyleDialog(dialogPane)
									.add(getClass().getResource(Constant.CSS).toExternalForm());
							Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
							dialogPane.setMinHeight(130);
							dialogPane.setMaxHeight(130);
							dialogPane.setPrefHeight(130);
							alert.setContentText(Utilities5.getString("popup.error12"));
							alert.setHeaderText(null);
							alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
							alert.showAndWait();
						}
					}
				});
			}
		});

		pdfButton = INUtility2.buttonForKey("button.generatepdf");
		final ImageView viewpdf = new ImageView(Constant.PDFPHOTO);
		pdfButton.setGraphic(viewpdf);
		pdfButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				try {
					if (!textFieldxsl.getText().isEmpty() && !textField.getText().isEmpty()
							&& (textFieldxsl.getText().trim().endsWith(Constant.XSLEXT) || textFieldxsl.isDisabled()
									|| textFieldxsl.getText().contains(Constant.CRBIO))
							&& (textField.getText().trim().endsWith(Constant.XMLEXT)
									|| textField.getText().trim().endsWith(Constant.XMLEXT1))) {
						final Path pdf = Files.createTempFile(null, Constant.PDFEXT);
						final Path pdfFile = pdf;
						// call progress bar
						runTask(taskUpdateStage, progress);
						Platform.runLater(() -> {
							final String pathStr = textFieldxsl.getText();
							String path;
							if (isAutopresentablePdf) {
								path = new File(pathStr).getParentFile().getParentFile().getAbsolutePath()
										.concat(Constant.PATH_FDS);
							} else {
								path = new File(pathStr).getParentFile().getAbsolutePath();
							}
							String str = path + Constant.PATH_FOP;
							// check fop folder if exist
							final Path pathFOPFolder = Paths.get(str);
							Path pathFOP;
							String xslPath;
							if (radio2 != null && radio2.isSelected()) {
								// Start DATAMATRIX
								InputStream targetStream;
								Path targetFilePath = null;
								Path targetFileLTeen = null;
								Path targetNarrativeFile = null;
								try {
									final Path file1 = Files.createTempFile(null, Constant.HTMLEXT);
									final String tempDir = System.getProperty("java.io.tmpdir");
									final Path pathDir = new File(tempDir).toPath();
									final File sourceFile = new File(textFieldxsl.getText());
									targetFilePath = pathDir.resolve(sourceFile.getName());
									Files.copy(sourceFile.toPath(), targetFilePath,
											StandardCopyOption.REPLACE_EXISTING);
									final File lTeenN = new File(sourceFile.getParent() + "//cda_l10n.xml");
									targetFileLTeen = pathDir.resolve(lTeenN.getName());
									Files.copy(lTeenN.toPath(), targetFileLTeen, StandardCopyOption.REPLACE_EXISTING);
									final File narrative = new File(
											sourceFile.getParent() + "//cda_narrativeblock.xml");
									targetNarrativeFile = pathDir.resolve(narrative.getName());
									Files.copy(narrative.toPath(), targetNarrativeFile,
											StandardCopyOption.REPLACE_EXISTING);
									final Path stream = Utilities6.transform(textFieldxsl.getText(),
											tmpFile.getAbsolutePath(), file1);
									final File url1 = new File(stream.toString());
									targetStream = Files.newInputStream(Paths.get(url1.toURI()));
									final Document doc = Jsoup.parse(targetStream, Constant.UTF8, "");
									final Collection<Element> contents = doc.getElementsByClass("barcodeStyle");
									for (final Element content : contents) {
										if (content != null) {
											final String dataMatrixValue = content.val();
											if (dataMatrixValue != null && !dataMatrixValue.isEmpty()) {
												final Map<EncodeHintType, Object> hints = new HashMap<>();
												hints.put(EncodeHintType.DATA_MATRIX_SHAPE,
														SymbolShapeHint.FORCE_SQUARE);
												final BitMatrix matrix = new MultiFormatWriter().encode(dataMatrixValue,
														BarcodeFormat.DATA_MATRIX, 200, 200, hints);
												final Path paths = Files.createTempFile(null, Constant.PNGEXT);
												MatrixToImageWriter.writeToPath(matrix, "PNG", paths);
												final Document document = Jsoup.parse(targetFilePath.toFile(), "UTF-8",
														"", Parser.xmlParser());
												final Element externalGraphic = document.getElementById("datamatrixId");
												if (externalGraphic != null) {
													externalGraphic.attr("src",
															"url('file:///" + paths.toString() + "')");
												}
												final File outputFile = targetFilePath.toFile();
												try (FileWriter writer = new FileWriter(outputFile)) {
													writer.write(document.outerHtml());
												}
											}
										}
									}
								} catch (final IOException | WriterException e) {
									if (LOG.isInfoEnabled()) {
										final String error = e.getMessage();
										LOG.error(error);
									}
								}
								// FIN DATAMATRIX
								xslPath = targetFilePath.toString();
								// externe fds xsl folder
								if (!Files.exists(pathFOPFolder)) {
									pathFOP = pathFOPFolder.getParent().getParent().getParent().getParent();
									str = pathFOP.toFile() + Constant.PATH_FOP;
									// autoprésentable xsl folder
									if (!Files.exists(Paths.get(str))) {
										str = pathFOP.toFile().getAbsolutePath() + Constant.PATH_FDS
												+ Constant.PATH_FOP;
									}
								}
								if (Files.exists(Paths.get(str))) {
									String cmd = "cd " + '"' + str + '"' + " && fop -xml " + '"' + textField.getText()
											+ '"' + " -xsl " + '"' + xslPath + '"' + " -pdf " + '"' + pdfFile.toString()
											+ '"';
									cmd = cmd.replace("\\", "/");
									final String[] command = new String[3];
									command[0] = Constant.CMD;
									command[1] = Constant.C_SLASH;
									command[2] = cmd;
									final ProcessBuilder pbuilder = new ProcessBuilder();
									pbuilder.command(command);
									Process process;
									try {
										process = pbuilder.start();
										final BufferedReader errStreamReader = new BufferedReader(
												new InputStreamReader(process.getErrorStream()));

										String line = errStreamReader.readLine();
										while (line != null) {
											line = errStreamReader.readLine();
										}
										final String namePDF = new File(textField.getText()).getName();
										final File basedir = new File(System.getProperty("java.io.tmpdir"));
										if (new File(basedir, Utilities6.removeExtension(namePDF) + Constant.PDFEXT)
												.exists()) {
											new File(basedir, Utilities6.removeExtension(namePDF) + Constant.PDFEXT)
													.delete();
										}
										File pdfFinal = new File(basedir,
												Utilities6.removeExtension(namePDF) + Constant.PDFEXT);
										if (pdfFile != null) {
											// rename if file exists
											int index = 1;
											while (pdfFinal.exists()) {
												final String path1 = pdfFinal.getAbsolutePath();
												final int idot = path1.lastIndexOf('.');
												final String path2 = path1.substring(0, idot) + "(" + ++index + ")"
														+ path1.substring(idot);
												pdfFinal = Utilities.getFile(path2);
											}
											ConvertPdfToPdfAB1.main(pdfFile.toString(), pdfFinal);
											// open pdf file
											Utilities6.openFile(new File(pdfFinal.toString()));
										}
									} catch (final IOException e) {
										if (LOG.isInfoEnabled()) {
											final String error = e.getMessage();
											LOG.error(error);
										}
									}
								}
							} else if (exist) {
								// Start DATAMATRIX
								InputStream targetStream;
								Path targetFilePath = null;
								Path targetFileLTeen = null;
								Path targetNarrativeFile = null;
								try {
									final Path file1 = Files.createTempFile(null, Constant.HTMLEXT);
									final String tempDir = System.getProperty("java.io.tmpdir");
									final Path pathDir = new File(tempDir).toPath();
									final File sourceFile = new File(textFieldxsl.getText());
									targetFilePath = pathDir.resolve(sourceFile.getName());
									Files.copy(sourceFile.toPath(), targetFilePath,
											StandardCopyOption.REPLACE_EXISTING);

									File lTeenN = null;
									File narrative = null;
									if (isAutopresentablePdf) {
										lTeenN = new File(sourceFile.getParentFile().getParent()
												+ "//FeuilleDeStyle//cda_l10n.xml");
										narrative = new File(sourceFile.getParentFile().getParent()
												+ "//FeuilleDeStyle//cda_narrativeblock.xml");
									} else {
										lTeenN = new File(sourceFile.getParent() + "//cda_l10n.xml");
										narrative = new File(sourceFile.getParent() + "//cda_narrativeblock.xml");
									}

									targetFileLTeen = pathDir.resolve(lTeenN.getName());
									Files.copy(lTeenN.toPath(), targetFileLTeen, StandardCopyOption.REPLACE_EXISTING);

									targetNarrativeFile = pathDir.resolve(narrative.getName());
									Files.copy(narrative.toPath(), targetNarrativeFile,
											StandardCopyOption.REPLACE_EXISTING);
									final Path stream = Utilities6.transform(targetFilePath.toString(),
											textField.getText(), file1);
									final File url1 = new File(stream.toString());
									targetStream = Files.newInputStream(Paths.get(url1.toURI()));
									final Document doc = Jsoup.parse(targetStream, Constant.UTF8, "");
									final Collection<Element> contents = doc.getElementsByClass("barcodeStyle");
									for (final Element content : contents) {
										if (content != null) {
											final String dataMatrixValue = content.val();
											if (dataMatrixValue != null && !dataMatrixValue.isEmpty()) {
												final Map<EncodeHintType, Object> hints = new HashMap<>();
												hints.put(EncodeHintType.DATA_MATRIX_SHAPE,
														SymbolShapeHint.FORCE_SQUARE);
												final BitMatrix matrix = new MultiFormatWriter().encode(dataMatrixValue,
														BarcodeFormat.DATA_MATRIX, 200, 200, hints);
												final Path paths = Files.createTempFile(null, Constant.PNGEXT);
												MatrixToImageWriter.writeToPath(matrix, "PNG", paths);
												final Document document = Jsoup.parse(targetFilePath.toFile(), "UTF-8",
														"", Parser.xmlParser());
												final Element externalGraphic = document.getElementById("datamatrixId");
												if (externalGraphic != null) {
													externalGraphic.attr("src",
															"url('file:///" + paths.toString() + "')");
												}
												final File outputFile = targetFilePath.toFile();
												try (FileWriter writer = new FileWriter(outputFile)) {
													writer.write(document.outerHtml());
												}
											}
										}
									}
								} catch (final IOException | WriterException e) {
									if (LOG.isInfoEnabled()) {
										final String error = e.getMessage();
										LOG.error(error);
									}
								}
								// FIN DATAMATRIX
								xslPath = targetFilePath.toString();
								// externe fds xsl folder
								if (!Files.exists(pathFOPFolder)) {
									pathFOP = pathFOPFolder.getParent().getParent().getParent().getParent();
									str = pathFOP.toFile() + Constant.PATH_FOP;
									// autoprésentable xsl folder
									if (!Files.exists(Paths.get(str))) {
										str = pathFOP.toFile().getAbsolutePath() + Constant.PATH_FDS
												+ Constant.PATH_FOP;
									}
								}
								if (Files.exists(Paths.get(str))) {
									String cmd = "cd " + '"' + str + '"' + " && fop -xml " + '"' + textField.getText()
											+ '"' + " -xsl " + '"' + xslPath + '"' + " -pdf " + '"' + pdfFile.toString()
											+ '"';
									cmd = cmd.replace("\\", "/");
									final String[] command = new String[3];
									command[0] = Constant.CMD;
									command[1] = Constant.C_SLASH;
									command[2] = cmd;
									final ProcessBuilder pbuilder = new ProcessBuilder();
									pbuilder.command(command);
									Process process;
									try {
										process = pbuilder.start();
										final BufferedReader errStreamReader = new BufferedReader(
												new InputStreamReader(process.getErrorStream()));

										String line = errStreamReader.readLine();
										while (line != null) {
											line = errStreamReader.readLine();
										}
										final String namePDF = new File(textField.getText()).getName();
										final File basedir = new File(System.getProperty("java.io.tmpdir"));
										if (new File(basedir, Utilities6.removeExtension(namePDF) + Constant.PDFEXT)
												.exists()) {
											new File(basedir, Utilities6.removeExtension(namePDF) + Constant.PDFEXT)
													.delete();
										}
										File pdfFinal = new File(basedir,
												Utilities6.removeExtension(namePDF) + Constant.PDFEXT);
										if (pdfFile != null) {
											// rename if file exists
											int index = 1;
											while (pdfFinal.exists()) {
												final String path1 = pdfFinal.getAbsolutePath();
												final int idot = path1.lastIndexOf('.');
												final String path2 = path1.substring(0, idot) + "(" + ++index + ")"
														+ path1.substring(idot);
												pdfFinal = Utilities.getFile(path2);
											}
											ConvertPdfToPdfAB1.main(pdfFile.toString(), pdfFinal);
											// open pdf file
											Utilities6.openFile(new File(pdfFinal.toString()));
										}
									} catch (final IOException e) {
										if (LOG.isInfoEnabled()) {
											final String error = e.getMessage();
											LOG.error(error);
										}
									}
								}
							} else {
								final Alert dialog = new Alert(AlertType.INFORMATION);
								final DialogPane dialogPane = dialog.getDialogPane();
								dialog.setHeaderText(null);
								dialog.setContentText(Utilities5.getString("popup.information"));
								dialogPane.setMinHeight(200);
								dialogPane.setMaxHeight(200);
								dialogPane.setPrefHeight(200);
								Utilities2.getStyleDialog(dialogPane)
										.add(getClass().getResource(Constant.CSS).toExternalForm());
								Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
								final ButtonType okButtonType = new ButtonType(
										Utilities5.getString("button.generatepdf"));
								Utilities2.getButtonType(dialog).setAll(okButtonType);
								final Optional<ButtonType> result = dialog.showAndWait();
								if (result.get().equals(okButtonType)) {
									final String namePDF = new File(textField.getText()).getName();
									final File basedir = new File(System.getProperty("java.io.tmpdir"));
									if (new File(basedir, Utilities6.removeExtension(namePDF) + Constant.PDFEXT)
											.exists()) {
										new File(basedir, Utilities6.removeExtension(namePDF) + Constant.PDFEXT)
												.delete();
									}
									File pdfFinal = new File(basedir,
											Utilities6.removeExtension(namePDF) + Constant.PDFEXT);
									File out = null;
									out = HTMLToPDF.main(textField.getText().trim(), textFieldxsl.getText().trim());
									if (out != null) {
										// rename if file exists
										int index = 1;
										while (pdfFinal.exists()) {
											final String path1 = pdfFinal.getAbsolutePath();
											final int idot = path1.lastIndexOf('.');
											final String path2 = path1.substring(0, idot) + "(" + ++index + ")"
													+ path1.substring(idot);
											pdfFinal = Utilities.getFile(path2);
										}
										ConvertPdfToPdfAB1.main(out.toString(), pdfFinal);
										// open pdf file
										Utilities6.openFile(new File(pdfFinal.toString()));
									}
								}
							}
						});
					} else {
						final Alert alert = new Alert(AlertType.WARNING);
						final DialogPane dialogPane = alert.getDialogPane();
						Utilities2.getStyleDialog(dialogPane)
								.add(getClass().getResource(Constant.CSS).toExternalForm());
						Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
						dialogPane.setMinHeight(130);
						dialogPane.setMaxHeight(130);
						dialogPane.setPrefHeight(130);
						alert.setContentText(Utilities5.getString("popup.error1"));
						alert.setHeaderText(null);
						alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
						alert.showAndWait();
					}
				} catch (final IOException e) {
					if (LOG.isInfoEnabled()) {
						final String error = e.getMessage();
						LOG.error(error);
					}
				}
			}
		});
		showXmlButton = INUtility2.buttonForKey("button.showcda");
		final ImageView viewxml = new ImageView(Constant.OPENPHOTO);
		showXmlButton.setGraphic(viewxml);
		showXmlButton.setOnAction(e -> {
			Platform.setImplicitExit(false);
			Font.loadFont(this.getClass().getResourceAsStream("/Roboto-Regular.ttf"), 16);
			FXMLLoader loader;
			try {
				if (!textField.getText().isEmpty() && (textField.getText().trim().endsWith(Constant.XMLEXT)
						|| textField.getText().trim().endsWith(Constant.XMLEXT1))) {
					final URL url = getClass().getResource("/pages/main.fxml");
					loader = new FXMLLoader(url);
					final Parent root = loader.load();
					Platform.runLater(() -> {
						final Scene scene = new Scene(root);
						// stylesheet css
						Utilities2.getStyleScene(scene)
								.add(WebViewSample.class.getResource(Constant.CSS1).toExternalForm());
						primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.setMinHeight(640);
						primaryStage.setMinWidth(640);
						primaryStage.setTitle(textField.getText());
						final Image image = Utils.imageView12();
						primaryStage.getIcons().add(image);
						primaryStage.setMaximized(true);
						primaryStage.setOnShowing(event -> {
							final String xmlStr = textField.getText();
							final File file = new File(xmlStr);
							if (file != null) {
								Utilities7.readFile(file, textFieldxsl.getText());
							}
						});
						primaryStage.setOnCloseRequest(event -> {
							EditorUtils.onCloseExitConfirmation(primaryStage);
							event.consume();
							final MainMenuBar bar = new MainMenuBar();
							if (bar.getSecondStage() != null && bar.getSecondStage().isShowing()) {
								bar.getSecondStage().close();
							}
							if (MainMenuBar.getStage() != null && MainMenuBar.getStage().isShowing()) {
								MainMenuBar.getStage().close();
							}
						});
						primaryStage.show();
					});
				} else {
					final Alert alert = new Alert(AlertType.WARNING);
					final DialogPane dialogPane = alert.getDialogPane();
					Utilities2.getStyleDialog(dialogPane).add(getClass().getResource(Constant.CSS).toExternalForm());
					Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
					dialogPane.setMinHeight(130);
					dialogPane.setMaxHeight(130);
					dialogPane.setPrefHeight(130);
					alert.setContentText(Utilities5.getString("popup.error3"));
					alert.setHeaderText(null);
					alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
					alert.showAndWait();
				}
			} catch (final IOException e1) {
				if (LOG.isInfoEnabled()) {
					final String error = e1.getMessage();
					LOG.error(error);
				}
			}
		});
		clearButton = INUtility2.buttonForKey("button.initinterface");
		final ImageView viewclear = new ImageView(Constant.CLEARPHOTO);
		clearButton.setGraphic(viewclear);
		clearButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				Utilities7.loadContent(webEngine);
				textField.setText("");
				textFieldxsl.setText("");
				outputFileName = "";
				xslButton.setDisable(false);
				textFieldxsl.setDisable(false);
				menuBar.setDisable(true);
				// Tooltip xml
				textField.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIP));
				// Tooltip xsl
				textFieldxsl.setTooltip(INUtility.createBoundTooltip(Constant.TOOLTIPXSL));
				boxCombo.setVisible(false);
				radio1.setSelected(true);
				radio1.setVisible(false);
				radio2.setVisible(false);
				// close xml editor
				if (primaryStage != null && primaryStage.isShowing()) {
					primaryStage.close();
				}
				if (thirdStage != null && thirdStage.isShowing()) {
					thirdStage.close();
				}
			}
		});
		openWithButton = INUtility2.buttonForKey("button.openwith");
		final ImageView viewOpen = new ImageView(Constant.OXYGENPHOTO);
		openWithButton.setGraphic(viewOpen);
		openWithButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(final ActionEvent event) {
				if (!textField.getText().isEmpty() && (textField.getText().trim().endsWith(Constant.XMLEXT)
						|| textField.getText().trim().endsWith(Constant.XMLEXT1))) {
					String cmd = textField.getText();
					cmd = cmd.replace("\\", "/");
					final String[] command = new String[3];
					command[0] = Constant.CMD;
					command[1] = Constant.C_SLASH;
					command[2] = cmd;
					final ProcessBuilder pbuilder = new ProcessBuilder();
					pbuilder.command(command);
					Process process = null;
					try {
						process = pbuilder.start();
					} catch (final IOException e) {
						if (LOG.isInfoEnabled()) {
							final String error = e.getMessage();
							LOG.error(error);
						}
					}
					try (BufferedReader errStreamReader = new BufferedReader(
							new InputStreamReader(process.getErrorStream()))) {
						String line = errStreamReader.readLine();
						while (line != null) {
							line = errStreamReader.readLine();
						}
					} catch (final IOException e) {
						if (LOG.isInfoEnabled()) {
							final String error = e.getMessage();
							LOG.error(error);
						}
					}
				} else {
					final Alert alert = new Alert(AlertType.WARNING);
					final DialogPane dialogPane = alert.getDialogPane();
					Utilities2.getStyleDialog(dialogPane).add(getClass().getResource(Constant.CSS).toExternalForm());
					Utilities2.getStyleClass(dialogPane).add(Constant.DIALOG);
					dialogPane.setMinHeight(130);
					dialogPane.setMaxHeight(130);
					dialogPane.setPrefHeight(130);
					alert.setContentText(Utilities5.getString("popup.error3"));
					alert.setHeaderText(null);
					alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(true);
					alert.showAndWait();
				}
			}
		});

		final HBox hBox = new HBox(10);
		// retrieving the observable list of the HBox
		final ObservableList<Node> listH = Utilities.getChildrenHNode(hBox);
		// English language
		final BackgroundImage backgroundImage = new BackgroundImage(
				new Image(WebViewSample.class.getResource("/images/en.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		final Background background = new Background(backgroundImage);
		buttonEnglish.setBackground(background);
		buttonEnglish.setPrefWidth(30);
		buttonEnglish.setPrefHeight(20);
		buttonEnglish.setMinHeight(20);
		buttonEnglish.setMaxHeight(20);
		buttonEnglish.setStyle(Constant.STYLE);
		buttonEnglish.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		// Tooltip English flag
		buttonEnglish.setTooltip(INUtility.createBoundTooltip("button.tooltip.english"));
		buttonEnglish.setOnAction((evt) -> Utilities6.switchLanguage(Locale.ENGLISH));
		// French language
		final BackgroundImage backgroundImageFr = new BackgroundImage(
				new Image(WebViewSample.class.getResource("/images/fr.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		final Background backgroundFr = new Background(backgroundImageFr);
		buttonFrensh.setBackground(backgroundFr);
		buttonFrensh.setPrefWidth(30);
		buttonFrensh.setPrefHeight(20);
		buttonFrensh.setMinHeight(20);
		buttonFrensh.setMaxHeight(20);
		buttonFrensh.setStyle(Constant.STYLE);
		buttonFrensh.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		// Tooltip Frensh flag
		buttonFrensh.setTooltip(INUtility.createBoundTooltip("button.tooltip.frensh"));
		buttonFrensh.setOnAction((evt) -> Utilities6.switchLanguage(Locale.FRENCH));
		// Deutch language
		final BackgroundImage backgroundImageDe = new BackgroundImage(
				new Image(WebViewSample.class.getResource("/images/de.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		final Background backgroundDe = new Background(backgroundImageDe);
		buttonDeutch.setBackground(backgroundDe);
		buttonDeutch.setPrefWidth(30);
		buttonDeutch.setPrefHeight(20);
		buttonDeutch.setMinHeight(20);
		buttonDeutch.setMaxHeight(20);
		buttonDeutch.setStyle(Constant.STYLE);
		buttonDeutch.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		// Tooltip Deutch flag
		buttonDeutch.setTooltip(INUtility.createBoundTooltip("button.tooltip.deutch"));
		buttonDeutch.setOnAction((evt) -> Utilities6.switchLanguage(Locale.GERMAN));
		// Netherland language
		final BackgroundImage backgroundImageNl = new BackgroundImage(
				new Image(WebViewSample.class.getResource("/images/nl.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		final Background backgroundNl = new Background(backgroundImageNl);
		buttonNl.setBackground(backgroundNl);
		buttonNl.setPrefWidth(30);
		buttonNl.setPrefHeight(20);
		buttonNl.setMinHeight(20);
		buttonNl.setMaxHeight(20);
		buttonNl.setStyle(Constant.STYLE);
		buttonNl.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		// Tooltip Deutch flag
		buttonNl.setTooltip(INUtility.createBoundTooltip("button.tooltip.nl"));
		buttonNl.setOnAction((evt) -> Utilities6.switchLanguage(Locale.forLanguageTag("nl-NL")));
		// Espagnol language
		final BackgroundImage backgroundImageEs = new BackgroundImage(
				new Image(WebViewSample.class.getResource("/images/es.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		final Background backgroundEs = new Background(backgroundImageEs);
		buttonEs.setBackground(backgroundEs);
		buttonEs.setPrefWidth(30);
		buttonEs.setPrefHeight(20);
		buttonEs.setMinHeight(20);
		buttonEs.setMaxHeight(20);
		buttonEs.setStyle(Constant.STYLE);
		buttonEs.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		// Tooltip Deutch flag
		buttonEs.setTooltip(INUtility.createBoundTooltip("button.tooltip.espagnol"));
		buttonEs.setOnAction((evt) -> Utilities6.switchLanguage(Locale.forLanguageTag("es-ES")));
		// Portugal language
		final BackgroundImage backgroundImagePt = new BackgroundImage(
				new Image(WebViewSample.class.getResource("/images/pt.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		final Background backgroundPt = new Background(backgroundImagePt);
		buttonPt.setBackground(backgroundPt);
		buttonPt.setPrefWidth(30);
		buttonPt.setPrefHeight(20);
		buttonPt.setMinHeight(20);
		buttonPt.setMaxHeight(20);
		buttonPt.setStyle(Constant.STYLE);
		buttonPt.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		// Tooltip Deutch flag
		buttonPt.setTooltip(INUtility.createBoundTooltip("button.tooltip.portugais"));
		buttonPt.setOnAction((evt) -> Utilities6.switchLanguage(Locale.forLanguageTag("pt-PT")));
		// Italian language
		final BackgroundImage backgroundImageIt = new BackgroundImage(
				new Image(WebViewSample.class.getResource("/images/it.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		final Background backgroundIt = new Background(backgroundImageIt);
		buttonIt.setBackground(backgroundIt);
		buttonIt.setPrefWidth(30);
		buttonIt.setPrefHeight(20);
		buttonIt.setMinHeight(20);
		buttonIt.setMaxHeight(20);
		buttonIt.setStyle(Constant.STYLE);
		buttonPt.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		// Tooltip Deutch flag
		buttonIt.setTooltip(INUtility.createBoundTooltip("button.tooltip.italian"));
		buttonIt.setOnAction((evt) -> Utilities6.switchLanguage(Locale.ITALIAN));
		final Image ansImage = new Image(WebViewSample.class.getResource("/images/ans01.jpg").toExternalForm());
		// creating ImageView for adding image
		imageViewAns = new ImageView();
		imageViewAns.setImage(ansImage);
		imageViewAns.setFitWidth(100);
		imageViewAns.setFitHeight(100);
		imageViewAns.setPreserveRatio(true);
		imageViewAns.setSmooth(true);
		imageViewAns.setCursor(Cursor.HAND);
		final Tooltip tooltip = new Tooltip(Constant.URL);
		Tooltip.install(imageViewAns, tooltip);
		imageViewAns.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<>() {
			@Override
			public void handle(final MouseEvent event) {
				Utilities6.browser(Constant.URL);
				event.consume();
			}
		});

		// creating HBox to add imageview
		final HBox hBoxImg = new HBox();
		Utilities.getChildrenHNode(hBoxImg).addAll(imageViewAns);
		hBoxImg.setStyle("-fx-background-color: white;");
		hBoxImg.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
		// space region between button and flags
		final Region spacer10 = new Region();
		spacer10.setMaxWidth(900);
		HBox.setHgrow(spacer10, Priority.ALWAYS);

		hBoxint.setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
		// retrieving the observable list of the HBox
		final ObservableList<Node> listHint = hBoxint.getChildren();
		hBoxint.setStyle("-fx-background-color: gray");
		// Adding all the nodes to the observable list
		Utilities7.addAll(listHint, buttonFrensh, buttonEnglish, buttonDeutch, buttonNl, buttonEs, buttonPt, buttonIt);
		// Adding all the nodes to the observable list
		listH.addAll(xmlButton, textField, xslButton, textFieldxsl, gridPane3);
		final HBox hBoxFlag = new HBox(10);
		// retrieving the observable list of the HBox
		final ObservableList<Node> listHFlag = Utilities.getChildrenHNode(hBoxFlag);
		listHFlag.addAll(listHint);
		final HBox hBoxOne = new HBox(10);
		// retrieving the observable list of the Hbox
		final ObservableList<Node> listHOne = Utilities.getChildrenHNode(hBoxOne);
		// Adding all the nodes to the observable list
		listHOne.addAll(validateButton, openButton, pdfButton, showXmlButton, openWithButton, tccButton, clearButton);
		final VBox vBoxAll = new VBox(5);
		final SplitPane splitPane = new SplitPane();
		vBoxAll.setMinSize(splitPane.getPrefWidth(), splitPane.getPrefHeight());
		final ObservableList<Node> listVBoxAll = Utilities.getChildrenNode(vBoxAll);
		listVBoxAll.addAll(hBoxFlag, hBox, hBoxOne);

		splitPane.setStyle("-fx-box-border: 0px;");
		splitPane.setOrientation(Orientation.HORIZONTAL);
		splitPane.setDividerPositions(0.01f, 0.99f);
		Utilities.getItemsSplit(splitPane).addAll(hBoxImg, vBoxAll);
		// Instantiating the VBox class
		final VBox vBox = new VBox(10);
		vBox.setMinSize(stage.getMinWidth(), stage.getMinHeight());
		vBox.setStyle("-fx-background-color: transparent;");
		final File url1 = new File(outputFileName);
		try {
			final String strUrl = new File(url1.getPath()).toURI().toURL().toString();
			Utilities6.load(webEngine, strUrl);
			browserEngine.prefHeightProperty().bind(stage.heightProperty());
			browserEngine.prefWidthProperty().bind(stage.widthProperty());

			// A line in Ox Axis
			final Separator oxLine1 = new Separator();
			oxLine1.setOrientation(Orientation.HORIZONTAL);
			oxLine1.setMinSize(Separator.USE_PREF_SIZE, Separator.USE_PREF_SIZE);
			oxLine1.setStyle("-fx-background-color: black;  -fx-pref-height: 1;");

			final Menu menu = INUtility2.menuForKey("menu.file");
			final MenuItem menuItem1 = INUtility2.menuBarForKey("menu.print");
			final ImageView imgView1 = Utils1.imageView1();
			menuItem1.setGraphic(imgView1);

			final MenuItem menuItem2 = INUtility2.menuBarForKey("button.copy.text");
			final ImageView imgView = Utils1.imageView();
			menuItem2.setGraphic(imgView);

			Utilities4.getItemsMenu(menu).addAll(menuItem2, menuItem1);

			final Menu menu1 = INUtility2.menuForKey("menu.display");

			final Menu subMenu = INUtility2.menuForKey("menu.zoom");
			final ImageView imgView2 = Utils1.imageView2();
			subMenu.setGraphic(imgView2);

			final MenuItem menuItem11 = INUtility2.menuBarForKey("menu.zoomavant");
			Utilities4.getItemsMenu(subMenu).add(menuItem11);
			final ImageView imgView3 = Utils1.imageView3();
			menuItem11.setGraphic(imgView3);

			final MenuItem menuItem12 = INUtility2.menuBarForKey("menu.zoomarriere");
			Utilities4.getItemsMenu(subMenu).add(menuItem12);
			final ImageView imgView4 = Utils1.imageView4();
			menuItem12.setGraphic(imgView4);

			final MenuItem menuItem13 = INUtility2.menuBarForKey("menu.taille");
			Utilities4.getItemsMenu(subMenu).add(menuItem13);
			Utilities4.getItemsMenu(menu).add(subMenu);
			final ImageView imgView6 = Utils1.imageView6();
			menuItem13.setGraphic(imgView6);

			final MenuItem menuItem4 = INUtility2.menuBarForKey("menu.pleinecran");
			final ImageView imgView5 = Utils1.imageView5();
			menuItem4.setGraphic(imgView5);

			Utilities4.getItemsMenu(menu1).add(subMenu);
			Utilities4.getItemsMenu(menu1).add(menuItem4);

			final Menu menu2 = INUtility2.menuForKey("menu.help");
			final MenuItem menuItem6 = INUtility2.menuBarForKey("menu.apropos");
			final ImageView imgView8 = Utils1.imageView8();
			menuItem6.setGraphic(imgView8);

			final MenuItem menuItem8 = INUtility2.menuBarForKey("menu.lisezmoi");
			final ImageView imgView9 = Utils1.imageView9();
			menuItem8.setGraphic(imgView9);

			Utilities4.getItemsMenu(menu2).addAll(menuItem6, menuItem8);

			final Menu menu3 = INUtility2.menuForKey("menu.history");
			final MenuItem menuItem7 = INUtility2.menuBarForKey("menu.display.history");
			final ImageView imgView7 = Utils1.imageView7();
			menuItem7.setGraphic(imgView7);

			Utilities4.getItemsMenu(menu3).add(menuItem7);
			Utilities4.getMenu(menuBar).addAll(menu, menu1, menu3, menu2);
			menuBar.setDisable(true);
			menuBar.getStylesheets().add(getClass().getResource(Constant.CSS).toExternalForm());

			menuItem2.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
			menuItem11.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
			menuItem12.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
			menuItem13.setAccelerator(new KeyCodeCombination(KeyCode.F10));
			menuItem4.setAccelerator(new KeyCodeCombination(KeyCode.F11));
			menuItem7.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
			boxCombo.setVisible(false);
			final HBox controls = new HBox(10);
			controls.setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);

			final Label label = INUtility2.labelForValue(() -> INUtility.get("menu.history.recent"));
			final ComboBox<WebHistory.Entry> comboBox = new ComboBox<>();
			comboBox.getStylesheets().add(getClass().getResource(Constant.CSS).toExternalForm());
			label.setPadding(new Insets(10, 0, 10, 20));
			label.setFont(Font.font(14));
			Utilities.getChildrenHNode(boxCombo).addAll(label, comboBox);
			Utilities4.getSelectionModel(comboBox).clearSelection();
			Utilities4.getItemsCombo(comboBox).clear();
			menuItem7.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					controls.setVisible(false);
					// Set a cell factory to to show only the page title in the history list
					comboBox.setCellFactory(new Callback<>() {
						@Override
						public ListCell<WebHistory.Entry> call(ListView<WebHistory.Entry> list) {
							final ListCell<Entry> cell = new ListCell<>() {
								@Override
								public void updateItem(final Entry item, final boolean empty) {
									super.updateItem(item, empty);

									if (empty) {
										this.setText(null);
										this.setGraphic(null);
									} else {
										final String pageTitle = item.getTitle();
										if (pageTitle != null) {
											if (pageTitle.isEmpty()) {
												this.setText(item.getUrl());
											} else {
												final String[] words = pageTitle.split("<");
												this.setText(words[0]);
											}
										}
									}
								}
							};
							return cell;
						}
					});
					comboBox.setPadding(new Insets(10, 0, 0, 20));
					comboBox.setMinWidth(500);
					comboBox.setMaxWidth(500);
					comboBox.setPrefWidth(500);
					boxCombo.setMaxWidth(Control.USE_PREF_SIZE);
					boxCombo.setVisible(true);
					comboBox.setItems(history.getEntries());
					comboBox.setOnAction(new EventHandler<>() {
						@Override
						public void handle(final ActionEvent event) {
							final int offset = Utilities4.getSelectionModel(comboBox).getSelectedIndex()
									- history.getCurrentIndex();
							history.go(offset);
							boxCombo.setVisible(false);
						}
					});
					history.currentIndexProperty().addListener(new ChangeListener<>() {
						@Override
						public void changed(final ObservableValue<? extends Number> observable, final Number oldValue,
								final Number newValue) {
							// update currently selected combobox item
							Utilities4.getSelectionModel(comboBox).select(newValue.intValue());
							final String pagePath = Utilities4.getSelectedItemCB(comboBox).getUrl();
							if (pagePath != null && pagePath.contains("file:/")) {
								final String[] word = pagePath.split("file:/");
								final String mapWord = word[1];
								final File file1 = new File(mapWord);
								for (final Map.Entry<String, String> mapentry : map.entrySet()) {
									final File file2 = Utilities.getFile(mapentry.getKey());
									if (file1.compareTo(file2) == 0) {
										textField.setText(mapentry.getValue());
										if (isAutopresentable) {
											textFieldxsl.setDisable(true);
											xslButton.setDisable(true);
										} else {
											textFieldxsl.setDisable(false);
											xslButton.setDisable(false);
										}
										break;
									}
								}
								for (final Map.Entry<String, String> mapentry : mapXsl.entrySet()) {
									final File file2 = Utilities.getFile(mapentry.getKey());
									if (file1.compareTo(file2) == 0) {
										textFieldxsl.setText(mapentry.getValue());
										break;
									}
								}
							}
							boxCombo.setVisible(false);
						}
					});
					// set converter for value shown in the combobox:
					comboBox.setConverter(new StringConverter<>() {
						@Override
						public String toString(final WebHistory.Entry object) {
							return object == null ? null : object.getUrl();
						}

						@Override
						public WebHistory.Entry fromString(final String string) {
							throw new UnsupportedOperationException();
						}
					});
					Utilities4.getSelectionModel(comboBox).clearSelection();
				}
			});
			menuItem6.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					Platform.runLater(() -> {
						final Stage stageT = new Stage();
						// Setting the text to be added.
						final String apropos = Utilities5.getString("menu.title.propo");
						final Label apropsT = new Label(apropos);
						final String browser = Utilities5.getString("menu.browser");
						final Label browserT = new Label(browser);

						apropsT.setFont(Font.font(20));
						apropsT.setStyle("-fx-font-weight: bold");
						apropsT.setMaxWidth(Double.MAX_VALUE);
						AnchorPane.setLeftAnchor(apropsT, 0.0);
						AnchorPane.setRightAnchor(apropsT, 0.0);
						apropsT.setAlignment(Pos.BASELINE_LEFT);
						apropsT.setPadding(new Insets(0, 0, 0, 20));

						browserT.setPadding(new Insets(0, 0, 0, 20));
						browserT.setFont(Font.font(14));
						browserT.setTextAlignment(TextAlignment.LEFT);
						browserT.setWrapText(true);
						browserT.setMaxWidth(250);

						final FlowPane root = new FlowPane();
						root.setPadding(new Insets(10));
						root.setHgap(10);
						root.setVgap(10);
						final VBox vbox = new VBox();
						vbox.getChildren().addAll(apropsT, browserT);
						Utilities4.getChildPane(root).add(vbox);
						// Creating a scene object
						final Scene sceneT = new Scene(root, 300, 200);
						// Setting title to the Stage
						stageT.titleProperty().bind(INUtility.createStringBinding("menu.apropo"));
						final Image image = Utils.imageView11();
						stageT.getIcons().add(image);
						// Adding scene to the stage
						stageT.setScene(sceneT);
						// Specifies the modality for new window.
						stageT.initModality(Modality.WINDOW_MODAL);
						// Specifies the owner Window (parent) for new window
						stageT.initOwner(stage);
						stageT.setResizable(false);
						// Displaying the contents of the stage
						stageT.show();
					});
				}
			});
			menuItem8.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					Platform.runLater(() -> {
						final ClassLoader classloader = Utilities3.getContextClassLoader();
						InputStream iStream = null;
						if (INUtility.getLocale().equals(Locale.FRENCH)) {
							iStream = classloader.getResourceAsStream(Constant.LISEZMOIFILE);
						} else if (INUtility.getLocale().equals(Locale.ENGLISH)) {
							iStream = classloader.getResourceAsStream(Constant.LISEZMOIEN);
						} else if (INUtility.getLocale().equals(Locale.GERMAN)) {
							iStream = classloader.getResourceAsStream(Constant.LISEZMOIDE);
						} else if (INUtility.getLocale().equals(Locale.ITALIAN)) {
							iStream = classloader.getResourceAsStream(Constant.LISEZMOIIT);
						} else if (INUtility.getLocale().equals(Locale.forLanguageTag("nl-NL"))) {
							iStream = classloader.getResourceAsStream(Constant.LISEZMOINL);
						} else if (INUtility.getLocale().equals(Locale.forLanguageTag("es-ES"))) {
							iStream = classloader.getResourceAsStream(Constant.LISEZMOIES);
						} else if (INUtility.getLocale().equals(Locale.forLanguageTag("pt-PT"))) {
							iStream = classloader.getResourceAsStream(Constant.LISEZMOIPT);
						}
						final VBox root = new VBox();
						root.setPadding(new Insets(10));
						root.setSpacing(5);
						final TextArea textArea = new TextArea();
						textArea.setEditable(false);
						;
						textArea.setPrefHeight(Integer.MAX_VALUE);
						textArea.setPrefWidth(Integer.MAX_VALUE);
						final Font font = Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR,
								TextArea.USE_PREF_SIZE);
						textArea.setFont(font);
						textArea.setStyle(Constant.STYLETX);
						Utils.newObsListTArea(textArea).add(getClass().getResource(Constant.CSS).toExternalForm());
						try {
							textArea.setText(Utils.readFileContents(iStream));
							Utils.newObsList(root).add(textArea);
							final Scene scene = new Scene(root);
							thirdStage.titleProperty().bind(INUtility.createStringBinding("menu.lisezmoi"));
							final Image image = Utils.imageView10();
							thirdStage.getIcons().add(image);
							thirdStage.setScene(scene);
							thirdStage.setMaximized(true);
							thirdStage.show();
						} catch (final IOException e) {
							if (LOG.isInfoEnabled()) {
								final String error = e.getMessage();
								LOG.error(error);
							}
						}
					});
				}
			});
			menuItem4.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					stage.setFullScreen(true);
				}
			});
			menuItem2.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					final Clipboard clipboard = Clipboard.getSystemClipboard();
					final Map<DataFormat, Object> content = new ConcurrentHashMap<>();
					final String selection = (String) Utilities3.getEngine(browserEngine)
							.executeScript("window.getSelection().toString()");
					content.put(DataFormat.PLAIN_TEXT, selection);
					clipboard.setContent(content);
				}
			});
			menuItem12.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					final Double zoom = browserEngine.getZoom();
					browserEngine.setZoom(zoom - 0.25);
				}
			});
			menuItem11.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					final Double zoom = browserEngine.getZoom();
					browserEngine.setZoom(zoom + 0.25);
				}
			});
			menuItem13.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					browserEngine.setZoom(1);
				}
			});
			final HBox allHbox = new HBox(10);
			controls.disableProperty().bind(Utilities3.getLoadWorker(browserEngine).runningProperty());
			controls.setVisible(false);
			allHbox.setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
			menuItem1.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
			final TextField searchBar = new TextField();

			final EventHandler<ActionEvent> handler = event -> {
				controls.setVisible(false);
				searchBar.setText("");

			};
			buttonX.setOnAction(handler);

			final Region spacer = new Region();
			spacer.setMaxWidth(20);
			HBox.setHgrow(spacer, Priority.ALWAYS);

			Utilities.getChildrenHNode(controls).addAll(spacer, searchBar, button, buttonX);

			final Field pageField = browserEngine.getClass().getDeclaredField("page");
			pageField.setAccessible(true);
			final WebPage page = (WebPage) pageField.get(browserEngine);
			button.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					try {
						page.find(searchBar.getText(), true, true, false);
					} catch (final IllegalArgumentException e) {
						if (LOG.isInfoEnabled()) {
							final String error = e.getMessage();
							LOG.error(error);
						}
					}
				}
			});
			menuItem1.setOnAction(new EventHandler<>() {
				@Override
				public void handle(final ActionEvent event) {
					boxCombo.setVisible(false);
					controls.setVisible(true);
				}
			});

			final VBox hBox2 = new VBox();
			hBox2.setMinSize(vBox.getPrefWidth(), vBox.getPrefHeight());

			final ObservableList<Node> listHB = Utilities.getChildrenNode(hBox2);
			Utilities.getChildrenHNode(allHbox).addAll(controls, boxCombo);
			listHB.addAll(oxLine1, menuBar, allHbox, browserEngine);
			// retrieving the observable list of the VBox
			final ObservableList<Node> list = Utilities.getChildrenNode(vBox);
			// Adding all the nodes to the observable list
			list.addAll(splitPane, hBox2);
			splitPane.setMinSize(vBox.getPrefWidth(), vBox.getPrefHeight());
			// Creating a scene object
			final Scene scene = new Scene(vBox, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
			Utilities3.getIcons(stage)
					.add(new Image(Utilities3.getContextClassLoader().getResourceAsStream(Constant.PHOTO)));
			// Setting title to the Stage
			stage.titleProperty().bind(INUtility.createStringBinding("window.title"));
			// Adding scene to the stage
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.setOnCloseRequest(new EventHandler<>() {
				@Override
				public void handle(final WindowEvent event) {
					stage.close();
					Platform.exit();
				}
			});
			xmlButton.setStyle(Constant.STYLE1);
			xslButton.setStyle(Constant.STYLE1);
			validateButton.setStyle(Constant.STYLE1);
			openButton.setStyle(Constant.STYLE1);
			tccButton.setStyle(Constant.STYLE1);
			openWithButton.setStyle(Constant.STYLE1);
			pdfButton.setStyle(Constant.STYLE1);
			showXmlButton.setStyle(Constant.STYLE1);
			clearButton.setStyle(Constant.STYLE1);
			textFieldxsl.setStyle(Constant.STYLE12);
			textField.setStyle(Constant.STYLE12);
			button.setStyle(Constant.STYLE1);
			buttonX.setStyle(Constant.STYLE1);
			radio1.setStyle(Constant.STYLE11);
			radio2.setStyle(Constant.STYLE11);
			menuItem1.setStyle(Constant.STYLE2);
			menuItem2.setStyle(Constant.STYLE2);
			menu.setStyle(Constant.STYLE2);
			menu1.setStyle(Constant.STYLE2);
			subMenu.setStyle(Constant.STYLE2);
			menuItem11.setStyle(Constant.STYLE2);
			menuItem12.setStyle(Constant.STYLE2);
			menuItem13.setStyle(Constant.STYLE2);
			menuItem4.setStyle(Constant.STYLE2);
			menu2.setStyle(Constant.STYLE2);
			menuItem6.setStyle(Constant.STYLE2);
			menuBar.setStyle(Constant.STYLE2);
			hBoxFlag.setAlignment(Pos.BASELINE_RIGHT);
			hBox.setAlignment(Pos.BASELINE_LEFT);
			hBoxOne.setAlignment(Pos.BASELINE_LEFT);
			scene.widthProperty().addListener(new ChangeListener<>() {
				@Override
				public void changed(final ObservableValue<? extends Number> observableValue, final Number oldSceneWidth,
						final Number newSceneWidth) {
					xmlButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
					xslButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
					validateButton.setMinSize(hBoxOne.getPrefWidth(), hBoxOne.getPrefHeight());
					openButton.setMinSize(hBoxOne.getPrefWidth(), hBoxOne.getPrefHeight());
					tccButton.setMinSize(hBoxOne.getPrefWidth(), hBoxOne.getPrefHeight());
					openWithButton.setMinSize(hBoxOne.getPrefWidth(), hBoxOne.getPrefHeight());
					pdfButton.setMinSize(hBoxOne.getPrefWidth(), hBoxOne.getPrefHeight());
					showXmlButton.setMinSize(hBoxOne.getPrefWidth(), hBoxOne.getPrefHeight());
					clearButton.setMinSize(hBoxOne.getPrefWidth(), hBoxOne.getPrefHeight());
					textFieldxsl.setMinSize(210, xmlButton.getPrefHeight());
					textField.setMinSize(210, xmlButton.getPrefHeight());
					button.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
					buttonX.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
					radio1.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
					radio2.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
					menuBar.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
					final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
					if (screenBounds.getWidth() < 1536) {
						xmlButton.setStyle(Constant.STYLE10);
						xslButton.setStyle(Constant.STYLE10);
						validateButton.setStyle(Constant.STYLE10);
						openButton.setStyle(Constant.STYLE10);
						tccButton.setStyle(Constant.STYLE10);
						openWithButton.setStyle(Constant.STYLE10);
						pdfButton.setStyle(Constant.STYLE10);
						showXmlButton.setStyle(Constant.STYLE10);
						clearButton.setStyle(Constant.STYLE10);
					}
					// hide webview scrollbars whenever they appear.
					Utilities3.getChildrenUnmodifiable(browserEngine).addListener(new ListChangeListener<>() {
						@Override
						public void onChanged(Change<? extends Node> change) {
							final Set<Node> nodes = browserEngine.lookupAll(".scroll-bar");
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
					// Resize Button
					hBox.setMinSize(vBoxAll.getPrefWidth(), vBoxAll.getPrefHeight());
					hBoxOne.setMinSize(vBoxAll.getPrefWidth(), vBoxAll.getPrefHeight());
					hBoxFlag.setMinSize(vBoxAll.getPrefWidth(), vBoxAll.getPrefHeight());
				}
			});

			stage.widthProperty().addListener(new ChangeListener<>() {
				@Override
				public void changed(final ObservableValue<? extends Number> observableValue, final Number number,
						final Number number2) {
					setCurrentWidthToStage(number2, stage);
				}
			});

			stage.heightProperty().addListener(new ChangeListener<>() {
				@Override
				public void changed(final ObservableValue<? extends Number> observableValue, final Number number,
						final Number number2) {
					setCurrentHeightToStage(number2, stage);
				}
			});

			// Displaying the contents of the stage
			stage.show();
			browserEngine.requestFocus();
		} catch (final MalformedURLException | NoSuchFieldException | SecurityException | IllegalArgumentException
				| IllegalAccessException e) {
			if (LOG.isInfoEnabled()) {
				final String error = e.getMessage();
				LOG.error(error);
			}
		}
	}

	/**
	 * setCurrentWidthToStage
	 * 
	 * @param number2
	 * @param stage
	 */
	private void setCurrentWidthToStage(final Number number2, final Stage stage) {
		stage.setMinWidth((double) number2);
	}

	/**
	 * setCurrentHeightToStage
	 * 
	 * @param number2
	 * @param stage
	 */
	private void setCurrentHeightToStage(final Number number2, final Stage stage) {
		stage.setMinWidth((double) number2);
	}

}
