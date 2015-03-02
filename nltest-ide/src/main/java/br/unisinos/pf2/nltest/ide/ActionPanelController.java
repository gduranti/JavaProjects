package br.unisinos.pf2.nltest.ide;

import java.io.File;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFileTreeBuilder;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFileWritter;
import br.unisinos.pf2.nltest.ide.testexecution.ExecutionFireListener;

public class ActionPanelController {

	private MainApp mainApp;

	@FXML
	private Label sysdateLabel;

	@FXML
	private Label pcConfigLabel;

	@FXML
	private TreeView<ScriptFile> fileTree;

	private File rootProjectDirectory;

	private ExecutionFireListener executionFireListener;

	@FXML
	private void initialize() {
		sysdateLabel.setText("01/03/2015 14:41");
		pcConfigLabel.setText("Windows 7, Intel I7, 8GB...");
	}

	public void setExecutionFireListener(ExecutionFireListener executionFireListener) {
		this.executionFireListener = executionFireListener;
	}

	public void addFileChangeListener(ChangeListener<? super TreeItem<ScriptFile>> listener) {
		fileTree.getSelectionModel().selectedItemProperty().addListener(listener);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void handleOpenProject() {

		chooseProjectDirectory();

		if (rootProjectDirectory != null) {
			ScriptFileTreeBuilder treeBuilder = new ScriptFileTreeBuilder();
			treeBuilder.build(fileTree, rootProjectDirectory);
		}
	}

	private void chooseProjectDirectory() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("JavaFX Projects");
		File defaultDirectory = new File("E:/Java/GitHub/Unisinos/nltest-ide/test/Project xyz");
		chooser.setInitialDirectory(defaultDirectory);
		rootProjectDirectory = chooser.showDialog(mainApp.getPrimaryStage());
	}

	@FXML
	public void handleNewProject() {
		chooseProjectDirectory();
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
			scriptFileWritter.write(fileTree, rootProjectDirectory);
		}
	}

	@FXML
	public void handleExecuteProject() {
		handleSaveProject();
		executionFireListener.fireExecution(rootProjectDirectory);
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

		// TODO
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			selectedItem.getParent().getChildren().remove(selectedItem);
		}
	}

}
