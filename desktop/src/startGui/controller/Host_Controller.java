package startGui.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * controller for hosting tab
 * 
 * @author mauricio
 *
 */
public class Host_Controller implements EventHandler{

    /**
     * background
     */
    @FXML
    BorderPane back;

    /**
     * label for num
     */
    @FXML
    Label num;

    /* (non-Javadoc)
     * @see javafx.event.EventHandler#handle(javafx.event.Event)
     */
    @Override
    public void handle(Event e) {
        Button pressed = (Button) e.getSource();
        String name = pressed.getId();

        if (name.equals("listen")) {
            num.setText("Clients Connected : 2");
        } else if (name.equals("host")) {
            loadMultiPlayerScene();
        }
    }

    /**
     * loads the game
     */
    private void loadMultiPlayerScene()
    {
        Parent singlePlayer;
        try {
            singlePlayer = (BorderPane) FXMLLoader.load(getClass().getResource("../view/MultiPlayer.fxml"));
            Scene scene  = new Scene(singlePlayer, 900,900);
            Stage currStage = (Stage) back.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
