package br.unisinos.pf2.nltest.ide.controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.ide.controller.thread.UpdateTimeThread;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteProjectScriptsEvent;
import br.unisinos.pf2.nltest.ide.event.events.NewProjectEvent;
import br.unisinos.pf2.nltest.ide.event.events.ProjectChangedEvent;
import br.unisinos.pf2.nltest.ide.event.events.TreeChangeEventAdapter;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFileTreeBuilder;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFileWritter;

public class ActionPanelController implements EventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActionPanelController.class);

	@FXML
	private Button openProjectButton;

	@FXML
	private Button saveProjectButton;

	@FXML
	private Button runProjectButton;

	@FXML
	private Label sysdateLabel;

	@FXML
	private Label pcConfigLabel;

	@FXML
	private TreeView<ScriptFile> fileTree;

	@FXML
	private void initialize() {

		openProjectButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-open-24.png"))));
		saveProjectButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-save-24.png"))));
		runProjectButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/icon-play-24.png"))));

		openProjectButton.setStyle("-fx-background-position: left;");

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
		EventDispatcher.getInstance().dispatch(new ExecuteProjectScriptsEvent());
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
			ScriptFile scriptFile;
			if (directory) {
				scriptFile = ScriptFile.createDirectory(result.get());
			} else {
				scriptFile = ScriptFile.createFile(result.get());
			}

			addScript(scriptFile);
		}
	}

	private void addScript(ScriptFile scriptFile) {
		TreeItem<ScriptFile> folder = getNearestFolderFromSelection();
		TreeItem<ScriptFile> newTreeItem = new TreeItem<ScriptFile>(scriptFile);
		newTreeItem.setExpanded(true);
		folder.getChildren().add(newTreeItem);
		fileTree.getSelectionModel().select(newTreeItem);
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

	@FXML
	public void handleShowAbout() {
		// TODO
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof NewProjectEvent) {
			createExampleScript();
		} else if (event instanceof ProjectChangedEvent) {
			buildTree(event);
		}
	}

	private void createExampleScript() {
		try {
			String scriptContent = new String(IOUtils.toByteArray(getClass().getResourceAsStream("/example.nlt")));
			addScript(ScriptFile.createFile("Script-1.nlt", scriptContent));
		} catch (IOException e) {
			LOGGER.error("Erro ao criar script de exemplo.", e);
		}
	}

	private void buildTree(Event event) {
		ProjectChangedEvent projectChangedEvent = (ProjectChangedEvent) event;
		File newProjectDirectory = projectChangedEvent.getNewProject();
		if (newProjectDirectory != null) {
			ScriptFileTreeBuilder treeBuilder = new ScriptFileTreeBuilder();
			treeBuilder.build(fileTree, newProjectDirectory);
		}

		if (fileTree.getRoot() != null) {
			fileTree.getSelectionModel().select(fileTree.getRoot());
		}
	}

}
