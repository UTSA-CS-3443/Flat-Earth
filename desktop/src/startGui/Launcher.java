package startGui;

import java.net.URL;


import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * doesnt actually do anything
 *
 *
 */
public class Launcher extends Application {

	
	public static void launchGui(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		URL location = getClass().getResource("view/MainMenu.fxml");
		//Sys.exit(location.toString());
		//URL location = new URL("MainMenu.fxml");
		//Sys.exit(location.toString());
		Parent root = FXMLLoader.load(location);
		primaryStage.setTitle("Flat-Earth");
		Scene scene = new Scene(root, 800, 900);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
}
