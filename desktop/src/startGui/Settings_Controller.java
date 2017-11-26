package startGui;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings_Controller implements EventHandler{

    @FXML
    GridPane set;
    @FXML
    Button right;
    @FXML
    Button left;
    @FXML
    Label choice;
    @FXML
    Button calibrate;

    int clickedR = 1;
    int clickedL = -1;


    public void initialize() {
        set.setOpacity(0);
        fadeInTransition();
    }

    private void fadeInTransition(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(set);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.play();

    }

    @Override
    public void handle(Event e) {
        Button pressed = (Button) e.getSource();
        String name = pressed.getId();

        if (name.equals("right")) {
            //if already pressed once
            if (clickedR == 1) {
                clickedR = 0;
                clickedL = 0;
                left.setText("❮");
                choice.setText("Joystick");
                calibrate.setVisible(true);
            } else if (clickedR == 0) {
                clickedR = -1;
                clickedL = 1;
                right.setText("_");
                choice.setText("D-Pad");
                calibrate.setVisible(false);
            }
        } else if (name.equals("left")) {
            if (clickedL == 1) {
                clickedL = 0;
                clickedR = 0;
                right.setText("❯");
                choice.setText("Joystick");
                calibrate.setVisible(true);
            } else if (clickedL == 0) {
                clickedL = -1;
                clickedR = 1;
                left.setText("_");
                choice.setText("Keyboard");
                calibrate.setVisible(false);
            }
        } else if ( name.equals("back") ){
            loadMainScene();
        }
    }

    private void loadMainScene()
    {
        Parent singlePlayer;
        try {
            singlePlayer = (GridPane) FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene  = new Scene(singlePlayer, 900,900);
            Stage currStage = (Stage) set.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

