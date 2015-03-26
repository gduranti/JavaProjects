package someValuePack;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

		stage.setTitle("AddressApp");

		try {
			// Load the root layout from the fxml file
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/RootLayout.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			// scene.getStylesheets().add(getClass().getResource("view/DarkTheme.css").toExternalForm());
			stage.setScene(scene);

			// Give the controller access to the main app
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			stage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

}
