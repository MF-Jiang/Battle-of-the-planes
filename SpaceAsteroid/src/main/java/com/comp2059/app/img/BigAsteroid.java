package com.comp2059.app.img;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import static com.comp2059.app.controller.GameController.H;
import static com.comp2059.app.controller.GameController.W;
import static com.comp2059.app.asteroid.BigAsteroid.bigAsteroid;

/**
 * The type Big asteroid that create big asteroid image.
 * @Title BigAsteroid
 */
public class BigAsteroid implements Img{
    /**
     * The Img asteroid that be created.
     */
    Image imgAsteroid = new Image(getClass().getResource("/com/comp2059/app/img/Asteroid/big_asteroid.png").toExternalForm());
    @Override
    public void creatImg(AnchorPane root) {
        Node newBigAsteroid = new ImageView(imgAsteroid);
        newBigAsteroid.relocate((Math.random() * (W)), (Math.random() / (H)));
        bigAsteroid.add(newBigAsteroid);
        root.getChildren().add(newBigAsteroid);
    }
}
