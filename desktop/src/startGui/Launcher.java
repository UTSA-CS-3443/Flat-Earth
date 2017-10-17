package startGui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import utilities.*;
import utilities.ConnectionSettings;

/**
 * 
 * doesnt actually do anything
 *
 */

public class Launcher extends Application {

	
	public static void launchGui(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		 primaryStage.setTitle("Start Game");
	        Button btn = new Button();
	        btn.setText("Start Game");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	 
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	                primaryStage.close();
	            }
	        });
	        
	        StackPane root = new StackPane();
	        root.getChildren().add(btn);
	        primaryStage.setScene(new Scene(root, 300, 250));
	        primaryStage.show();		
	}
	
}
