package br.unisinos.pf2.nltest.ide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.ide.controller.ActionPanelController;
import br.unisinos.pf2.nltest.ide.controller.EditorPanelController;
import br.unisinos.pf2.nltest.ide.controller.ExecutionPanelController;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteFileScriptEvent;
import br.unisinos.pf2.nltest.ide.event.events.ScriptChangedEvent;
import br.unisinos.pf2.nltest.ide.event.events.ShowEditorEvent;

public class MainApp extends Application implements EventListener {

	private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

	private Stage primaryStage;
	private BorderPane rootLayout;
	private AnchorPane editorPane;
	private BorderPane executionPane;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void start(Stage stage) throws Exception {

		this.primaryStage = stage;

		logger.info("Starting NLTest-IDE");

		rootLayout = new FXMLLoader().load(getClass().getResourceAsStream("/fxml/RootLayout.fxml"));

		Scene scene = new Scene(rootLayout);
		// scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle("NLTest-IDE");
		stage.setScene(scene);
		stage.show();

		FXMLLoader actionPanelLoader = new FXMLLoader();
		AnchorPane actionPanel = actionPanelLoader.load(getClass().getResourceAsStream("/fxml/ActionPanel.fxml"));
		ActionPanelController actionPanelController = actionPanelLoader.getController();
		actionPanelController.setMainApp(this);
		rootLayout.setLeft(actionPanel);

		FXMLLoader editorPanelLoader = new FXMLLoader();
		editorPane = editorPanelLoader.load(getClass().getResourceAsStream("/fxml/EditorPanel.fxml"));
		EditorPanelController editorPanelController = editorPanelLoader.getController();
		rootLayout.setCenter(this.editorPane);

		FXMLLoader executionPanelLoader = new FXMLLoader();
		executionPane = executionPanelLoader.load(getClass().getResourceAsStream("/fxml/ExecutionPanel.fxml"));
		ExecutionPanelController executionPanelController = executionPanelLoader.getController();

		EventDispatcher eventDispatcher = EventDispatcher.getInstance();
		eventDispatcher.registerListener(this);
		eventDispatcher.registerListener(actionPanelController);
		eventDispatcher.registerListener(editorPanelController);
		eventDispatcher.registerListener(executionPanelController);
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof ShowEditorEvent || event instanceof ScriptChangedEvent) {
			logger.debug("Event captured " + event);
			rootLayout.setCenter(editorPane);
		} else if (event instanceof ExecuteFileScriptEvent) {
			logger.debug("Event captured " + event);
			rootLayout.setCenter(executionPane);
		}
	}
}
