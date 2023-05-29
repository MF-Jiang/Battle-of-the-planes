package com.comp2059.app.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.comp2059.app.controller.OptionController.optionpage;

/**
 * The type Menu controller that controls the menu-page.fxml.
 *  @Title MenuController
 */
public class MenuController {
    /**
     * The Next.
     */
    @FXML
    Button next;
    /**
     * The Option.
     */
    @FXML
    Button Option;
    /**
     * The Info.
     */
    @FXML
    Button Info;
    @FXML private AnchorPane MenuPage;
    /**
     * The Stage.
     */
    Stage stage;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        if(optionpage!=null && optionpage.getColor()!=null){
            MenuPage.setStyle("-fx-background-color: #"+optionpage.getColor().toString().substring(2));
        }
    }

    /**
     * Load the next stage.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void LoadinNextStage(ActionEvent event) throws IOException {
        stage =(Stage) next.getScene().getWindow();
        Parent root =FXMLLoader.load(getClass().getResource("/com.comp2059.app/pre-page.fxml"));
        stage.setTitle("Space Asteroids");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
    }

    /**
     * Quit game.
     *
     * @param event the event
     */
    @FXML
    void QuitGame(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Load option stage.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void LoadinoptionStage(ActionEvent actionEvent) throws IOException {
        stage =(Stage) Option.getScene().getWindow();
        Parent root =FXMLLoader.load(getClass().getResource("/com.comp2059.app/option-page.fxml"));
        stage.setTitle("Space Asteroids");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
    }

    /**
     * Load info stage.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void LoadinfoStage(ActionEvent actionEvent) throws IOException {
        stage =(Stage) Info.getScene().getWindow();
        Parent root =FXMLLoader.load(getClass().getResource("/com.comp2059.app/info-page.fxml"));
        stage.setTitle("Space Asteroids");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
    }
}
