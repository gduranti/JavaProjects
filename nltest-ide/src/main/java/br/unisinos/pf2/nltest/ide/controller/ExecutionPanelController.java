package br.unisinos.pf2.nltest.ide.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import br.unisinos.pf2.nltest.ide.controller.thread.UpdateUiThread;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteFileScriptEvent;
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
	private TreeTableView<ScriptResult> treeResult;

	@FXML
	private TreeTableColumn<ScriptResult, ?> testUnityColumn;

	@FXML
	private TreeTableColumn<ScriptResult, ?> testResultColumn;

	@FXML
	private TreeTableColumn<ScriptResult, ?> testMessageColumn;

	@FXML
	private void initialize() {
		cancelButton.setDisable(false);
		printButton.setDisable(true);
		testUnityColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
		testResultColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("result"));
		testMessageColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("message"));
	}

	@Override
	public void handleEvent(Event event) {

		if (event instanceof ExecuteFileScriptEvent) {
			ExecuteFileScriptEvent executeFileScriptEvent = (ExecuteFileScriptEvent) event;
			DescriptionWrapper descriptionWrapper = new DescriptionWrapper();
			UpdateUiThread updateUiThread = new UpdateUiThread(descriptionWrapper, cancelButton, treeResult);

			Thread executeTestsThread = new Thread(new Runnable() {
				@Override
				public void run() {
					JUnitExecutor executor = new JUnitExecutor();
					// EventDispatcher.getInstance().dispatch(new
					// TestExecutionStartedEvent());
					executor.execute(descriptionWrapper, executeFileScriptEvent.getFile());
					// EventDispatcher.getInstance().dispatch(new
					// TestExecutionFinishedEvent());
					// executionFinished();

					System.out.println("Stoping updateUiThread.");
					updateUiThread.stop();
				}
			});
			executeTestsThread.setDaemon(true);
			executeTestsThread.start();

			updateUiThread.start();
		}
	}

	private void executionFinished() {
		cancelButton.setDisable(true);
		printButton.setDisable(false);
	}
}
