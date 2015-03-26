package br.unisinos.pf2.nltest.ide.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.unisinos.pf2.nltest.core.parser.CommandMap;
import br.unisinos.pf2.nltest.ide.controller.jfxevents.CommandMapClickHandler;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ScriptChangedEvent;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;

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
	private VBox commandMapBox;

	@FXML
	private HBox editorHBox;

	@FXML
	private void initialize() {
		fillCommandList();
		commandListView.setOnMouseClicked(new CommandMapClickHandler(commandListView, scriptTextEditor));
		showCommandMapButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-about-24.png"))));
		fileName.setText(null);
		scriptTextEditor.setDisable(true);
		scriptTextEditor.setText(null);
		handleShowCommandMap();
	}

	private void fillCommandList() {
		List<CommandMap> commands = CommandMap.loadAll();
		for (CommandMap mappedCommand : commands) {
			if (mappedCommand.isExecutable()) {
				commandListView.getItems().add(mappedCommand.toString());
			}
		}
	}

	@FXML
	public void handleShowCommandMap() {
		if (showCommandMapButton.isSelected()) {
			if (!editorHBox.getChildren().contains(commandMapBox)) {
				editorHBox.getChildren().add(commandMapBox);
			}
		} else {
			editorHBox.getChildren().remove(commandMapBox);
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
