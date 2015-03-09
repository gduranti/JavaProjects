package br.unisinos.pf2.nltest.ide.controller;

import java.io.File;

import javafx.stage.Stage;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.events.ProjectChangedEvent;

class IdeSession {

	private Stage primaryStage;
	private File projectDirectory;

	private static IdeSession instance = new IdeSession();

	private IdeSession() {
	}

	public static IdeSession getInstance() {
		if (instance == null) {
			instance = new IdeSession();
		}
		return instance;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public File getProjectDirectory() {
		return projectDirectory;
	}

	public void setProjectDirectory(File projectDirectory) {
		File oldDirectory = this.projectDirectory;
		this.projectDirectory = projectDirectory;
		EventDispatcher.getInstance().dispatch(new ProjectChangedEvent(oldDirectory, projectDirectory));
	}
}
