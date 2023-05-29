package com.comp2059.app.controller;

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
 * The type Info controller that controls info-page.fxml.
 *  @Title InfoController
 */
public class InfoController {
    @FXML
    private AnchorPane Info;
    @FXML
    private Button Back;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        if(optionpage!=null && optionpage.getColor()!=null){
            Info.setStyle("-fx-background-color: #"+optionpage.getColor().toString().substring(2));
        }
    }

    /**
     * Back menu.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void BackMenu(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) Back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com.comp2059.app/menu-page.fxml"));
        stage.setTitle("Space Asteroids");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
    }
}
