package br.unisinos.pf2.nltest.ide.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteFileScriptEvent;
import br.unisinos.pf2.nltest.ide.testexecution.JUnitExecutor;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;

public class ExecutionPanelController implements EventListener {

	private static final Logger logger = LoggerFactory.getLogger(ExecutionPanelController.class);

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Button cancelButton;

	@FXML
	private Button printButton;

	@FXML
	private TreeView<ScriptResult> treeResult;

	@FXML
	private void initialize() {
		cancelButton.setDisable(false);
		printButton.setDisable(true);
	}

	@Override
	public void handleEvent(Event event) {

		if (event instanceof ExecuteFileScriptEvent) {

			ExecuteFileScriptEvent executeFileScriptEvent = (ExecuteFileScriptEvent) event;

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					JUnitExecutor executor = new JUnitExecutor();
					executor.execute(executeFileScriptEvent.getFile());
					executionFinished();
				}
			});
			thread.setDaemon(true);
			thread.start();
		}
	}

	private void executionFinished() {
		cancelButton.setDisable(true);
		printButton.setDisable(false);
	}
}
