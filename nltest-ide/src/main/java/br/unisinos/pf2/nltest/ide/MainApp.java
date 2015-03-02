package br.unisinos.pf2.nltest.ide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

	private static final Logger log = LoggerFactory.getLogger(MainApp.class);
	private Stage primaryStage;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void start(Stage stage) throws Exception {

		this.primaryStage = stage;

		log.info("Starting NLTest-IDE");

		BorderPane rootLayout = new FXMLLoader().load(getClass().getResourceAsStream("/fxml/RootLayout.fxml"));

		Scene scene = new Scene(rootLayout);
		// scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle("NLTest-IDE");
		stage.setScene(scene);
		stage.show();

		FXMLLoader actionPanelLoader = new FXMLLoader();
		AnchorPane actionPanel = actionPanelLoader.load(getClass().getResourceAsStream("/fxml/ActionPanel.fxml"));
		ActionPanelController actionPanelController = (ActionPanelController) actionPanelLoader.getController();
		actionPanelController.setMainApp(this);
		rootLayout.setLeft(actionPanel);

		FXMLLoader editorPanelLoader = new FXMLLoader();
		AnchorPane editorPanel = editorPanelLoader.load(getClass().getResourceAsStream("/fxml/EditorPanel.fxml"));
		EditorPanelController editorPanelController = (EditorPanelController) editorPanelLoader.getController();
		rootLayout.setCenter(editorPanel);

		actionPanelController.addFileChangeListener(editorPanelController);
	}
}
