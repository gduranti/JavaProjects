package br.unisinos.pf2.nltest.ide.controller;

import java.io.File;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import br.unisinos.pf2.nltest.ide.controller.thread.UpdateTimeThread;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteFileScriptEvent;
import br.unisinos.pf2.nltest.ide.event.events.ProjectChangedEvent;
import br.unisinos.pf2.nltest.ide.event.events.TreeChangeEventAdapter;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFileTreeBuilder;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFileWritter;

public class ActionPanelController implements EventListener {

	@FXML
	private Label sysdateLabel;

	@FXML
	private Label pcConfigLabel;

	@FXML
	private TreeView<ScriptFile> fileTree;

	@FXML
	private void initialize() {
		pcConfigLabel.setText(String.format("%s, Java %s", System.getProperty("os.name"), System.getProperty("java.version")));
		fileTree.getSelectionModel().selectedItemProperty().addListener(new TreeChangeEventAdapter());
		UpdateTimeThread.start(sysdateLabel);
	}

	@FXML
	public void handleOpenProject() {
		ProjectChooser projectChooser = new ProjectChooser();
		projectChooser.chooseProjectDirectory();
	}

	@FXML
	public void handleSaveProject() {
		if (fileTree.getRoot() != null) {
			int selectedIndex = fileTree.getSelectionModel().getSelectedIndex();
			if (selectedIndex != -1) {
				fileTree.getSelectionModel().clearSelection();
				fileTree.getSelectionModel().select(selectedIndex);
			}

			ScriptFileWritter scriptFileWritter = new ScriptFileWritter();
			scriptFileWritter.write(fileTree, IdeSession.getInstance().getProjectDirectory());
		}
	}

	@FXML
	public void handleExecuteProject() {
		handleSaveProject();
		EventDispatcher.getInstance().dispatch(new ExecuteFileScriptEvent(IdeSession.getInstance().getProjectDirectory()));
	}

	@FXML
	public void handleNewFile() {
		createNewFile("Novo arquivo de teste", "Nome do arquivo:", ".nlt", false);
	}

	@FXML
	public void handleNewFolder() {
		createNewFile("Nova pasta", "Nome da pasta:", "", true);
	}

	private void createNewFile(String alertTitle, String alertQuestion, String fileExtension, boolean directory) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(alertTitle);
		dialog.setHeaderText(null);
		dialog.setContentText(alertQuestion);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			TreeItem<ScriptFile> folder = getNearestFolderFromSelection();

			ScriptFile scriptFile;
			if (directory) {
				scriptFile = ScriptFile.createDirectory(result.get());
			} else {
				scriptFile = ScriptFile.createFile(result.get());
			}

			TreeItem<ScriptFile> newTreeItem = new TreeItem<ScriptFile>(scriptFile);
			newTreeItem.setExpanded(true);
			folder.getChildren().add(newTreeItem);
		}
	}

	private TreeItem<ScriptFile> getNearestFolderFromSelection() {
		TreeItem<ScriptFile> selectedItem = fileTree.getSelectionModel().getSelectedItem();
		if (selectedItem.getValue().isDirectory()) {
			return selectedItem;
		} else {
			return selectedItem.getParent();
		}
	}

	@FXML
	public void handleDeleteFile() {
		TreeItem<ScriptFile> selectedItem = fileTree.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Atenção");
		alert.setHeaderText(null);
		String fileType = selectedItem.isLeaf() ? "o arquivo" : "a pasta";
		alert.setContentText(String.format("Tem certeza que deseja remover %s %s?", fileType, selectedItem.getValue()));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			selectedItem.getParent().getChildren().remove(selectedItem);
		}
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof ProjectChangedEvent) {
			ProjectChangedEvent projectChangedEvent = (ProjectChangedEvent) event;
			File newProjectDirectory = projectChangedEvent.getNewProject();
			if (newProjectDirectory != null) {
				ScriptFileTreeBuilder treeBuilder = new ScriptFileTreeBuilder();
				treeBuilder.build(fileTree, newProjectDirectory);
			}
		}
	}

}
