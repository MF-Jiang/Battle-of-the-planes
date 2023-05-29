package com.comp2059.app.img;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import static com.comp2059.app.controller.GameController.H;
import static com.comp2059.app.controller.GameController.W;
import static com.comp2059.app.asteroid.SmallAsteroid.asteroid;

/**
 * The type Small asteroid create small asteroid image.
 * @Title SmallAsteroid.
 */
public class SmallAsteroid implements Img {
    /**
     * The Img asteroid that be created.
     */
    Image imgAsteroid = new Image(getClass().getResource("/com/comp2059/app/img/Asteroid/asteroid.png").toExternalForm());
    @Override
    public void creatImg(AnchorPane root) {
        Node newsmallAsteroid = new ImageView(imgAsteroid);
        newsmallAsteroid.relocate((Math.random() * (W)), (Math.random() / (H)));
        asteroid.add(newsmallAsteroid);
        root.getChildren().add(newsmallAsteroid);
    }
}
