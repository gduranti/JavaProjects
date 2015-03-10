package br.unisinos.pf2.nltest.ide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.ide.controller.ActionPanelController;
import br.unisinos.pf2.nltest.ide.controller.EditorPanelController;
import br.unisinos.pf2.nltest.ide.controller.ExecutionPanelController;
import br.unisinos.pf2.nltest.ide.controller.IdeSession;
import br.unisinos.pf2.nltest.ide.controller.ProjectChooser;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.ExecuteProjectScriptsEvent;
import br.unisinos.pf2.nltest.ide.event.events.ProjectChangedEvent;
import br.unisinos.pf2.nltest.ide.event.events.ScriptChangedEvent;

public class MainApp extends Application implements EventListener {

	private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

	private TabPane tabPane;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		logger.info("Starting NLTest-IDE");

		BorderPane rootLayout = new BorderPane();

		Scene scene = new Scene(rootLayout);
		// scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle("NLTest-IDE");
		stage.setScene(scene);
		stage.show();

		FXMLLoader actionPanelLoader = new FXMLLoader();
		AnchorPane actionPanel = actionPanelLoader.load(getClass().getResourceAsStream("/fxml/ActionPanel.fxml"));
		ActionPanelController actionPanelController = actionPanelLoader.getController();
		rootLayout.setLeft(actionPanel);

		FXMLLoader editorPanelLoader = new FXMLLoader();
		AnchorPane editorPane = editorPanelLoader.load(getClass().getResourceAsStream("/fxml/EditorPanel.fxml"));
		EditorPanelController editorPanelController = editorPanelLoader.getController();

		FXMLLoader executionPanelLoader = new FXMLLoader();
		BorderPane executionPane = executionPanelLoader.load(getClass().getResourceAsStream("/fxml/ExecutionPanel.fxml"));
		ExecutionPanelController executionPanelController = executionPanelLoader.getController();

		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab editorTab = new Tab("Editor");
		editorTab.setContent(editorPane);
		tabPane.getTabs().add(editorTab);
		Tab executionTab = new Tab("Execução");
		executionTab.setContent(executionPane);
		tabPane.getTabs().add(executionTab);
		rootLayout.setCenter(tabPane);

		IdeSession.getInstance().setPrimaryStage(stage);

		EventDispatcher eventDispatcher = EventDispatcher.getInstance();
		eventDispatcher.registerListener(this);
		eventDispatcher.registerListener(actionPanelController);
		eventDispatcher.registerListener(editorPanelController);
		eventDispatcher.registerListener(executionPanelController);

		ProjectChooser projectChooser = new ProjectChooser();
		projectChooser.initialSelection();
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof ScriptChangedEvent || event instanceof ProjectChangedEvent) {
			logger.debug("Event captured " + event);
			tabPane.getSelectionModel().select(0);
		} else if (event instanceof ExecuteProjectScriptsEvent) {
			logger.debug("Event captured " + event);
			tabPane.getSelectionModel().select(1);
		}
	}
}
