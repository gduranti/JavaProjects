package br.unisinos.pf2.nltest.ide;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;
import br.unisinos.pf2.nltest.ide.testexecution.ExecutionFireListener;

public class EditorPanelController implements ChangeListener<TreeItem<ScriptFile>> {

	@FXML
	private Label fileName;

	@FXML
	private Button executeButton;

	@FXML
	private TextArea scriptTextEditor;

	private ExecutionFireListener executionFireListener;

	@FXML
	private void initialize() {
		executeButton.setDisable(true);
		scriptTextEditor.setDisable(true);
		fileName.setText(null);
		scriptTextEditor.setText(null);
	}

	public void setExecutionFireListener(ExecutionFireListener executionFireListener) {
		this.executionFireListener = executionFireListener;
	}

	@FXML
	public void handleExecuteScript() {
		executionFireListener.fireExecution(rootProjectDirectory);
	}

	@Override
	public void changed(ObservableValue<? extends TreeItem<ScriptFile>> observable, TreeItem<ScriptFile> oldFile, TreeItem<ScriptFile> newFile) {

		// Update the previus selected file with the actual editing text
		if (oldFile != null && !oldFile.getValue().isDirectory()) {
			oldFile.getValue().setScriptContent(scriptTextEditor.getText());
		}

		boolean isEditableFile = newFile != null && !newFile.getValue().isDirectory();
		executeButton.setDisable(!isEditableFile);
		scriptTextEditor.setDisable(!isEditableFile);

		// Show de new selected file in the editor
		if (isEditableFile) {
			fileName.setText(newFile.getValue().getName());
			scriptTextEditor.setText(newFile.getValue().getScriptContent());

		} else {
			// Clear...
			fileName.setText(null);
			scriptTextEditor.setText(null);
		}
	}

}
