package br.unisinos.pf2.nltest.ide.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import br.unisinos.pf2.nltest.ide.controller.thread.ExecuteTestsThread;
import br.unisinos.pf2.nltest.ide.controller.thread.UpdateResultUiThread;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteFileScriptEvent;
import br.unisinos.pf2.nltest.ide.testexecution.IdeExecutionContext;
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

			// Clear de previus test execution
			treeResult.setRoot(null);

			// Creates a new execution context for the IDE
			IdeExecutionContext ideExecutionContext = new IdeExecutionContext();

			// Starts threads to update the UI and to execute all the tests from
			// the received file
			UpdateResultUiThread.start(ideExecutionContext, cancelButton, printButton, treeResult);
			ExecuteTestsThread.start(ideExecutionContext, executeFileScriptEvent.getFile());
		}
	}

}
