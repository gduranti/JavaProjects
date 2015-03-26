package br.unisinos.pf2.nltest.ide.controller;

import java.util.Arrays;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import br.unisinos.pf2.nltest.core.executor.Browser;
import br.unisinos.pf2.nltest.ide.controller.thread.ExecuteTestsThread;
import br.unisinos.pf2.nltest.ide.controller.thread.UpdateResultUiThread;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteProjectScriptsEvent;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionFinishedEvent;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionStartedEvent;
import br.unisinos.pf2.nltest.ide.testexecution.IdeExecutionContext;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;

public class ExecutionPanelController implements EventListener {

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Button runButton;

	@FXML
	private Button printButton;

	@FXML
	private Button configButton;

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

		runButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-play-24.png"))));
		printButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-print-24.png"))));
		configButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-gear-24.png"))));

		printButton.setDisable(true);
		configButton.setDisable(true);
		testUnityColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("displayName"));
		testResultColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("displayResult"));
		testMessageColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("displayMessage"));
	}

	@FXML
	public void handleExecuteAgain() {
		EventDispatcher.getInstance().dispatch(new ExecuteProjectScriptsEvent());
	}

	@FXML
	public void handlePrintResult() {

	}

	@FXML
	public void handleConfig() {
		ChoiceDialog<Browser> dialog = new ChoiceDialog<>(IdePrefs.getDefaultBroser(), Arrays.asList(Browser.values()));
		dialog.setTitle("Configurações");
		dialog.setHeaderText(null);
		dialog.setContentText("Browser padrão:");

		Optional<Browser> result = dialog.showAndWait();
		result.ifPresent(browser -> IdePrefs.saveDefaultBrowser(browser));
	}

	@Override
	public void handleEvent(Event event) {

		if (event instanceof TestExecutionStartedEvent) {
			enableActionButtons(true);

		} else if (event instanceof TestExecutionFinishedEvent) {
			enableActionButtons(false);

		} else if (event instanceof ExecuteProjectScriptsEvent) {

			// Clear de previus test execution
			treeResult.getSelectionModel().clearSelection();
			progressBar.setProgress(0.0);

			// Creates a new execution context for the IDE
			IdeExecutionContext ideExecutionContext = new IdeExecutionContext();

			// Starts threads to update the UI and to execute all the tests from
			// the received file
			UpdateResultUiThread.start(ideExecutionContext, progressBar, treeResult);
			ExecuteTestsThread.start(ideExecutionContext);
		}
	}

	private void enableActionButtons(boolean b) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				runButton.setDisable(b);
				printButton.setDisable(b);
				configButton.setDisable(b);
			}
		});
	}

}
