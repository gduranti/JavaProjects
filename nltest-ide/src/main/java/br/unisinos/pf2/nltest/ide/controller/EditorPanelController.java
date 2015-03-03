package br.unisinos.pf2.nltest.ide.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ScriptChangedEvent;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;

public class EditorPanelController implements EventListener {

	@FXML
	private Label fileName;

	@FXML
	private Button executeButton;

	@FXML
	private TextArea scriptTextEditor;

	@FXML
	private void initialize() {
		executeButton.setDisable(true);
		scriptTextEditor.setDisable(true);
		fileName.setText(null);
		scriptTextEditor.setText(null);
	}

	@FXML
	public void handleExecuteScript() {
		// TODO
		// EventDispatcher.getInstance().dispatch(new
		// ExecuteFileScriptEvent(file));
	}

	@Override
	public void handleEvent(Event event) {

		if (event instanceof ScriptChangedEvent) {

			ScriptChangedEvent scriptChangedEvent = (ScriptChangedEvent) event;

			ScriptFile oldScript = scriptChangedEvent.getOldScript();
			ScriptFile newScript = scriptChangedEvent.getNewScript();

			// Update the previus selected file with the actual editing text
			if (oldScript != null && !oldScript.isDirectory()) {
				oldScript.setScriptContent(scriptTextEditor.getText());
			}

			boolean isEditableFile = newScript != null && !newScript.isDirectory();
			executeButton.setDisable(!isEditableFile);
			scriptTextEditor.setDisable(!isEditableFile);

			// Show de new selected file in the editor
			if (isEditableFile) {
				fileName.setText(newScript.getName());
				scriptTextEditor.setText(newScript.getScriptContent());

			} else {
				// Clear...
				fileName.setText(null);
				scriptTextEditor.setText(null);
			}
		}
	}

}
