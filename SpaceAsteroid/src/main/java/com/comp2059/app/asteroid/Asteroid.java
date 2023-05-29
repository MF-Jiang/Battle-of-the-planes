package com.comp2059.app.asteroid;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;

/**
 * This is the abstract Asteroid class which will be extended by another class later.
 *
 * @Title Asteroid
 *
 */
public abstract class Asteroid {

    /**
     * The Speed for every asteroid movement.
     */
    int speed;
    /**
     * The Asteroid counter for generate small Asteroid.
     */
    int asteroidCounter = 0;
    /**
     * The Asteroid counter 2 for generate big Asteroid.
     */
    int asteroidCounter2 = 0;
    /**
     * The Offset to delay the bigAsteroid spawn time.
     */
    int offset = 50;
    /**
     * The Respawn times for each Asteroid.
     */
    int modifier = 150;


    /**
     * Get speed int.
     * @Title getSpeed
     * @return the int
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Create and move Asteroid on the scene
     * @Title CreateAndMoveAsteroid
     * @param root  Javafx path
     * @param speed the speed
     */

    public abstract void CreateAndMoveAsteroid(AnchorPane root, int speed);


    /**
     * Move small and big asteroid from top to bottom.
     * @Title MoveAsteroid
     * @param root  the root
     * @param speed the speed
     */
    public abstract void moveAsteroid(AnchorPane root, int speed);


    /**
     * Collide with fire which contains destroy and game score adding.
     * @Title collide
     * @param root the root
     */

    public abstract void collide(AnchorPane root);


}

