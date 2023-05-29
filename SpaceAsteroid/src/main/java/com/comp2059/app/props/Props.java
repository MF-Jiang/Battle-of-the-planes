package com.comp2059.app.props;

import javafx.scene.layout.AnchorPane;

/**
 * The type Props.
 * @Title Props
 */
public abstract class Props {

    /**
     * The Speed.
     */
    int speed;

    /**
     * Get speed int.
     * This shows the falling speed about props on the scene.
     *
     * @return the int
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Move the props on the scene.
     *
     * @param root  Javafx path
     * @param speed the speed
     */
    public abstract void moveProps(AnchorPane root, int speed);

    /**
     * Create props.
     *
     * @param root  the root
     * @param speed the speed
     */
    public abstract void CreateProps(AnchorPane root, int speed);

    /**
     * Collide with player.
     * If it is collided, player can shoot laser beams twice at one time.
     * If not, props will be destroyed.
     * @param root the root
     */
    public abstract void collide(AnchorPane root);

    /**
     * Get into scene.
     * Let props show in the scene.
     * @param root  the root
     * @param speed the speed
     */
    public void getIntoScene(AnchorPane root, int speed){
        collide(root);
        moveProps(root, speed);
    }
}
