package startGui;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MultiPlayer_Controller implements EventHandler {

    @FXML
    BorderPane background;

    private void fadeInTransition(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(background);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.play();

    }

    @Override
    public void handle(Event event) {

    }
}
