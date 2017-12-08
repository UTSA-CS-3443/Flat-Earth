package startGui.controller;

import javafx.animation.FadeTransition;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilities.Sys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * controller for multip tab
 * @author mauricio
 *
 */
public class MultiPlayer_Controller implements EventHandler {

    /**
     * background
     */
    @FXML
    BorderPane background;
    
    /**
     * button
     */
    @FXML
    Button back;
    /**
     * button
     */
    @FXML
    Button client;
    /**
     * button
     */
    @FXML
    Button host;

    /**
     * starts the tab
     */
    public void initialize() {
        background.setOpacity(0);
        fadeInTransition();
    }

    /**
     * fades
     */
    private void fadeInTransition(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(background);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.play();

    }

    /* (non-Javadoc)
     * @see javafx.event.EventHandler#handle(javafx.event.Event)
     */
    @Override
    public void handle(Event event) {
        Button pressed = (Button) event.getSource();
        String name = pressed.getId();
        if (name.equals("host")) {
            loadHostScene();
        } else if (name.equals("client")) {
            //loadClientScene();
        } else if ( name.equals("back") ){
            loadMainScene();
        }
    }
//
//    private void loadClientScene() {
//        Parent singlePlayer;
//        try {
//            singlePlayer = (BorderPane) FXMLLoader.load(getClass().getResource("/View/Client.fxml"));
//            Scene scene  = new Scene(singlePlayer, 900,900);
//            Stage currStage = (Stage) background.getScene().getWindow();
//            currStage.setScene(scene);
//        } catch (IOException e) {
//            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    /**
     * 
     */
    private void loadMainScene()
    {
        Parent singlePlayer;
        try {
            singlePlayer = (GridPane) FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
            Scene scene  = new Scene(singlePlayer, 900,900);
            Stage currStage = (Stage) background.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * not used
     */
    private void makeFadeOutHost()
    {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(background);
        fade.setFromValue(1.0);
        fade.setToValue(0);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadHostScene();
            }
        });
        fade.play();
    }

    /**
     * loads host screen
     */
    private void loadHostScene()
    {
        Parent singlePlayer;
        try {
            singlePlayer = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Host.fxml"));
            Scene scene  = new Scene(singlePlayer, 900,900);
            Stage currStage = (Stage) background.getScene().getWindow();
            currStage.setScene(scene);
        } catch (Exception e) {
            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
