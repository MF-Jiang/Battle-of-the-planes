package com.comp2059.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.comp2059.app.controller.OptionController.optionpage;

/**
 * The type Pre controller that controls pre-page.fxml.
 * @Title PreController
 */
public class PreController {
    /**
     * The constant namestr.
     */
    public static String namestr;
    /**
     * The Back.
     */
    @FXML public Button Back;
    /**
     * The Play.
     */
    @FXML public Button Play;
    /**
     * The Namebox.
     */
    @FXML public TextField namebox;
    /**
     * The Prepage.
     */
    @FXML public AnchorPane prepage;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        if(optionpage!=null && optionpage.getColor()!=null){
            prepage.setStyle("-fx-background-color: #"+optionpage.getColor().toString().substring(2));
        }
    }

    /**
     * Back menu.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void BackMenu(ActionEvent event) throws IOException {
        Stage stage =(Stage) Back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com.comp2059.app/menu-page.fxml"));
        stage.setTitle("Space Asteroids");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
    }

    /**
     * Start playing.
     * Load game-page.fxml.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void StartPlaying(ActionEvent event) throws IOException {
        if(namebox.getText().isEmpty()){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Entry Error");
            error.setContentText("Name is a required field.");
            error.show();
            return;
        }else {
            for (int i = 0; i < namebox.getText().length(); i++) {
                if (Character.isDigit(namebox.getText().charAt(i)) == true) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Entry Error");
                    error.setContentText("Name must not contain a digit");
                    error.show();
                    return;
                }
            }
            Stage stage =(Stage) Play.getScene().getWindow();
            namestr = namebox.getText();
            Parent root =FXMLLoader.load(getClass().getResource("/com.comp2059.app/game-page.fxml"));
            stage.setTitle("Space Asteroids");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
        }
    }
}
