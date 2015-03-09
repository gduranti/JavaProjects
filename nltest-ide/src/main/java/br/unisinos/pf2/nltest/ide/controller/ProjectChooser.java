package br.unisinos.pf2.nltest.ide.controller;

import java.io.File;
import java.util.Optional;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;

public class ProjectChooser {

	private static final String PREFS_NODE = "br/unisinos/nltest-ide";
	private static final String LAST_PROJECT_DIR_KEY = "last-project-dir";

	public void initialSelection() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Escolher projeto");
		alert.setHeaderText(null);
		alert.setContentText("Você deseja abrir um projeto existente ou iniciar um novo?");

		ButtonType openProjectType = new ButtonType("Abrir Projeto");
		ButtonType newProjectType = new ButtonType("Novo Projeto");
		ButtonType cancelType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(openProjectType, newProjectType, cancelType);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == openProjectType) {
			chooseProjectDirectory();

		} else if (result.get() == newProjectType) {
			String newProjectName = askNewProjectName();
			File newParentProjectDirectory = askProjectDirectory();
			File rootProjectDirectory = new File(newParentProjectDirectory.getPath() + "\\" + newProjectName);
			rootProjectDirectory.mkdir();
			IdeSession.getInstance().setProjectDirectory(rootProjectDirectory);

		} else {
			System.exit(0);
		}
	}

	private String askNewProjectName() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Novo Projeto");
		dialog.setHeaderText(null);
		dialog.setContentText("Nome do novo projeto:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		} else {
			System.exit(0);
			return null;
		}
	}

	private File askProjectDirectory() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("JavaFX Projects");
		chooser.setInitialDirectory(getLastDirectory());
		File rootProjectDirectory = chooser.showDialog(IdeSession.getInstance().getPrimaryStage());
		saveLastDirectory(rootProjectDirectory);
		return rootProjectDirectory;
	}

	private void saveLastDirectory(File rootProjectDirectory) {
		if (rootProjectDirectory != null) {
			try {
				Preferences prefs = loadPreferences();
				prefs.put(LAST_PROJECT_DIR_KEY, rootProjectDirectory.getPath());
				prefs.flush();
			} catch (BackingStoreException e) {
			}
		}
	}

	private File getLastDirectory() {
		String lastDirPath = loadPreferences().get(LAST_PROJECT_DIR_KEY, null);
		if (lastDirPath != null) {
			return new File(lastDirPath);
		}
		return null;
	}

	private Preferences loadPreferences() {
		return Preferences.userRoot().node(PREFS_NODE);
	}

	public void chooseProjectDirectory() {
		File rootProjectDirectory = askProjectDirectory();
		IdeSession.getInstance().setProjectDirectory(rootProjectDirectory);
	}

}
