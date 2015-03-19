package br.unisinos.pf2.nltest.ide.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ScriptChangedEvent;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;
import br.unisinos.pf2.nltest.parser.CommandMap;

public class EditorPanelController implements EventListener {

	@FXML
	private Label fileName;

	@FXML
	private ToggleButton showCommandMapButton;

	@FXML
	private TextArea scriptTextEditor;

	@FXML
	private ListView<String> commandListView;

	@FXML
	private TitledPane commandMapPane;

	@FXML
	private HBox editorHBox;

	@FXML
	private void initialize() {

		fillCommandList();

		showCommandMapButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-about-24.png"))));

		scriptTextEditor.setDisable(true);
		fileName.setText(null);
		scriptTextEditor.setText(null);
		handleShowCommandMap();
	}

	private void fillCommandList() {
		List<CommandMap<?>> commands = CommandMap.load();
		for (CommandMap<?> mappedCommand : commands) {
			commandListView.getItems().add(mappedCommand.toString());
		}
	}

	@FXML
	public void handleShowCommandMap() {
		if (showCommandMapButton.isSelected()) {
			if (!editorHBox.getChildren().contains(commandMapPane)) {
				editorHBox.getChildren().add(commandMapPane);
			}
		} else {
			editorHBox.getChildren().remove(commandMapPane);
		}
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
