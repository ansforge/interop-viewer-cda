package com.ans.cda.utilities;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.NumberUtils;

import com.ans.cda.Constant;
import com.ans.cda.XslFileObj;
import com.ans.cda.gui.mediator.Events;
import com.ans.cda.gui.mediator.IMediator;
import com.ans.cda.gui.mediator.Mediator;
import com.ans.cda.lib.EditorUtils;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

/**
 * Utilities7
 */
public final class Utilities7 {
	/**
	 * mediator
	 */
	private final static IMediator MEDIATOR = Mediator.getInstance();

	/**
	 * Utilities7 constructor
	 */
	private Utilities7() {
		// empty constructor
	}

	/**
	 * loadContent
	 * 
	 * @param webEngine
	 */
	public static void loadContent(final WebEngine webEngine) {
		webEngine.loadContent("");
	}

	/**
	 * addAll
	 * 
	 * @param listHint     @param spacer10 @param buttonFrensh @param buttonEnglish
	 * @param buttonDeutch @param buttonNl @param buttonEs @param buttonPt
	 * @param buttonIt
	 */
	public static void addAll(final ObservableList<Node> listHint, final Button buttonFrensh,
			final Button buttonEnglish, final Button buttonDeutch, final Button buttonNl, final Button buttonEs,
			final Button buttonPt, final Button buttonIt) {
		listHint.addAll(buttonFrensh, buttonEnglish, buttonDeutch, buttonNl, buttonEs, buttonPt, buttonIt);
	}

	/**
	 * loading in api
	 */
	public static void runTask(final Stage taskUpdateStage, final ProgressIndicator progress) {
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
			}
		});
		progress.progressProperty().bind(longTask.progressProperty());
		taskUpdateStage.show();
		new Thread(longTask).start();
	}

	/**
	 * readFile
	 * 
	 * @param file
	 * @param xslFile
	 */
	public static void readFile(final File file, final String xslFile) {
		final List<String> list = EditorUtils.readFromFile(file);
		final String str = list.stream().collect(Collectors.joining("\n"));
		Utilities2.getEventBuilder(MEDIATOR).withEvent(Events.OPEN_MENU).withFilePath(file.toPath()).withText(str)
				.textChanged(false).fileSaved(true).build();
		MEDIATOR.setXslFilePath(xslFile);
		Utilities3.getIcons(MEDIATOR.getStageTitle())
				.add(new Image(Utilities3.getContextClassLoader().getResourceAsStream(Constant.PHOTO)));
	}

	/**
	 * createXslFile
	 * 
	 * @param xslFileObj
	 * @return
	 */
	public static File createXslFile(final XslFileObj xslFileObj) {
		File xslFile = null;
		if (xslFileObj.getOutStr().contains("..") && xslFileObj.getXslLineAutoP() == null) {
			xslFile = new File(xslFileObj.getXmlFileParent() + xslFileObj.getOutStr().substring(2));
		} else if (!xslFileObj.getOutStr().contains("..") && xslFileObj.getXslLineAutoP() == null) {
			xslFile = new File(xslFileObj.getXmlFileParent() + xslFileObj.getOutStr());
		} else if (xslFileObj.getXslLineAutoP() != null) {
			xslFile = new File(xslFileObj.getXmlStr());
		}
		return xslFile;
	}

	/**
	 * compareInt
	 * 
	 * @param length   @param lengthName
	 * @param jCounter @param nom
	 * @param ident
	 */
	public static String compareInt(final int length, final int lengthName, final int jCounter, final String nom,
			final String ident) {
		String fileName;
		if (NumberUtils.compare(length, 0) == 0) {
			if (NumberUtils.compare(lengthName, 0) == 0) {
				fileName = "Document" + jCounter;
			} else {
				fileName = nom;
			}
		} else {
			fileName = ident;
		}
		return fileName;
	}
}
