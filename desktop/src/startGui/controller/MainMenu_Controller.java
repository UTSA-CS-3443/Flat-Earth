package startGui.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flatearth.desktop.DesktopLauncher;



public class MainMenu_Controller implements EventHandler<ActionEvent> {

    @FXML
    public ImageView img;
    @FXML
    GridPane back;
    @FXML
    Button right;
    @FXML
    Button left;

    int clickedR = 1;
    int clickedL = -1;
    
    int choseCharacter = 2;

    @Override
	@FXML
    public void handle(ActionEvent e) {

        Button pressed = (Button) e.getSource();
        String name = pressed.getId();
        
        if (name.equals("right")) {
            //if already pressed once
            if (clickedR == 1) {
                clickedR = 0;
                clickedL = 0;
                left.setText("❮");
                img.setImage(new Image(getClass().getResourceAsStream("../view/archer.png")));
                this.choseCharacter = 1;
            } else if (clickedR == 0) {
                clickedR = -1;
                clickedL = 1;
                right.setText("_");
                img.setImage(new Image(getClass().getResourceAsStream("../view/knight.png")));
                this.choseCharacter = 0;
            }
        } else if (name.equals("left")) {
            if (clickedL == 1) {
                clickedL = 0;
                clickedR = 0;
                right.setText("❯");
                img.setImage(new Image(getClass().getResourceAsStream("../view/archer.png")));
                this.choseCharacter = 1;
            } else if (clickedL == 0) {
                clickedL = -1;
                clickedR = 1;
                left.setText("_");
                img.setImage(new Image(getClass().getResourceAsStream("../view/Wizard_normal.png")));
                this.choseCharacter = 2;
            }
        } else if ( name.equals("single") ){
            //DesktoptopLunhcer.s.cract
            img.setImage(new Image(getClass().getResourceAsStream("../view/Wizard_eyesclosed.png")));
            img.setImage(new Image(getClass().getResourceAsStream("../view/Wizard_magic_blue.png")));
            makeFadeOutGame();
            Stage stage = (Stage) pressed.getScene().getWindow();
            DesktopLauncher.s.characterType = this.choseCharacter;
            stage.close();
        } else if ( name.equals("options")){
            makeFadeOutSettings();
        } else if ( name.equals("multi")){
            makeFadeOutMultiPlayer();
        }
    }


    private void makeFadeOutGame()
    {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(back);
        fade.setFromValue(1.0);
        fade.setToValue(0);
        /*fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadGameScene();
            }
        });*/
        fade.play();
    }

//    private void loadGameScene()
//    {
//        Parent singlePlayer;
//        try {
//            singlePlayer = (GridPane) FXMLLoader.load(getClass().getResource("Quote.fxml"));
//            Scene scene  = new Scene(singlePlayer, 900,900);
//            Stage currStage = (Stage) back.getScene().getWindow();
//            currStage.setScene(scene);
//        } catch (IOException e) {
//            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    private void makeFadeOutSettings()
    {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(back);
        fade.setFromValue(1.0);
        fade.setToValue(0);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadSettingsScene();
            }
        });
        fade.play();
    }

    private void loadSettingsScene()
    {
        Parent singlePlayer;
        try {
            singlePlayer = (GridPane) FXMLLoader.load(getClass().getResource("../view/Settings.fxml"));
            Scene scene  = new Scene(singlePlayer, 900,900);
            Stage currStage = (Stage) back.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void makeFadeOutMultiPlayer()
    {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(back);
        fade.setFromValue(1.0);
        fade.setToValue(0);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadMultiPlayerScene();
            }
        });
        fade.play();
    }

    private void loadMultiPlayerScene()
    {
        Parent multiPlayer;
        try {
            multiPlayer = FXMLLoader.load(getClass().getResource("../view/MultiPlayer.fxml"));
            Scene scene  = new Scene(multiPlayer, 900,900);
            Stage currStage = (Stage) back.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            Logger.getLogger(MainMenu_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}

