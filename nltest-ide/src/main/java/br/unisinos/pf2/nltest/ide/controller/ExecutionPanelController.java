package br.unisinos.pf2.nltest.ide.controller;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeView;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteFileScriptEvent;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionFinishedEvent;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionStartedEvent;
import br.unisinos.pf2.nltest.ide.testexecution.JUnitExecutor;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;

public class ExecutionPanelController implements EventListener {

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
					EventDispatcher.getInstance().dispatch(new TestExecutionStartedEvent());
					executor.execute(executeFileScriptEvent.getFile());
					EventDispatcher.getInstance().dispatch(new TestExecutionFinishedEvent());
					executionFinished();
				}
			});
			thread.setDaemon(true);
			thread.start();

			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					cancelButton.setText("texto " + new Random().hashCode());
				}
			};
			timer.start();

		}
	}

	private void executionFinished() {
		cancelButton.setDisable(true);
		printButton.setDisable(false);
	}
}
