package startGui.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * handles the quote
 * 
 * @author mauricio
 *
 */
public class Quote_Controller implements Initializable{

    /**
     * pane
     */
    @FXML
    GridPane pane;


    /* (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setOpacity(0);
        fadeInTransition();
    }

    /**
     * fades
     */
    private void fadeInTransition(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(pane);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.play();

    }
}
