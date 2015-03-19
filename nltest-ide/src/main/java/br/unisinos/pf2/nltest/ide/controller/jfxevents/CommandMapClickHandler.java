package br.unisinos.pf2.nltest.ide.controller.jfxevents;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CommandMapClickHandler implements EventHandler<MouseEvent> {

	private ListView<String> commandListView;
	private TextArea scriptTextEditor;

	public CommandMapClickHandler(ListView<String> commandListView, TextArea scriptTextEditor) {
		this.commandListView = commandListView;
		this.scriptTextEditor = scriptTextEditor;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				String selectedItem = commandListView.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					int anchor = insertCommand(selectedItem);
					selectFirstParameter(anchor, selectedItem);
				}
			}
		}
	}

	private int insertCommand(String selectedItem) {
		int anchor = scriptTextEditor.getAnchor();
		scriptTextEditor.insertText(anchor, selectedItem);
		return anchor;
	}

	private void selectFirstParameter(int anchor, String selectedItem) {
		int firstParameterStart = selectedItem.indexOf("{");
		int firstParameterEnd = selectedItem.indexOf("}");
		if (firstParameterStart != -1 && firstParameterEnd != -1) {
			scriptTextEditor.selectRange(anchor + firstParameterStart, anchor + firstParameterEnd + 1);
			scriptTextEditor.requestFocus();
		}
	}

}
