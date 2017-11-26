package startGui;

import java.net.URL;

import com.flatearth.desktop.DesktopLauncher;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utilities.Sys;

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
		URL location = getClass().getResource("MainMenu.fxml");
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
