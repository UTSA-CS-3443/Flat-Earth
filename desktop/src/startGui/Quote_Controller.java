package startGui;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Quote_Controller implements Initializable{

    @FXML
    GridPane pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setOpacity(0);
        fadeInTransition();
    }

    private void fadeInTransition(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(pane);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.play();

    }
}
