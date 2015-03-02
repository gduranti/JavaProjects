package br.unisinos.pf2.nltest.ide;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeView;

import org.junit.runner.JUnitCore;

import br.unisinos.pf2.nltest.ide.testexecution.ExecutionFireListener;
import br.unisinos.pf2.nltest.ide.testexecution.LoggingRunListener;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;
import br.unisinos.pf2.nltest.ide.testexecution.TestClass;

public class ExecutionPanelController implements ExecutionFireListener {

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

	public void execute() {

		JUnitCore jUnitCore = new JUnitCore();
		jUnitCore.addListener(new LoggingRunListener());
		jUnitCore.run(TestClass.class);

		executionFinished();

	}

	private void executionFinished() {
		cancelButton.setDisable(true);
		printButton.setDisable(false);
	}

	@Override
	public void fireExecution(File file) {
		// TODO Auto-generated method stub

	}

}
