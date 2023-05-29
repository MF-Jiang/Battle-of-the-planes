package com.comp2059.app.asteroid;

import com.comp2059.app.Fire;
import com.comp2059.app.controller.GameController;
import com.comp2059.app.factory.ImgFactory;
import com.comp2059.app.factory.SoundsFactory;
import com.comp2059.app.img.Img;
import com.comp2059.app.sounds.Sound;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

import static com.comp2059.app.controller.GameController.*;

/**
 * The type Big asteroid extends the Asteroid which contains all the variable and method of bigAsteroid.
 */
public class BigAsteroid extends Asteroid {
    /**
     * This bigAsteroid array list is used to store spawned big asteroids
     */
    public static ArrayList<Node> bigAsteroid = new ArrayList<>();
    /**
     * The Img big asteroid created by the ImgFactory.
     */
    Img imgBigAsteroid = ImgFactory.getImg("BigAsteroid");
    /**
     * The Sound burst created by the SoundsFactory.
     */
    Sound soundBurst = SoundsFactory.getSound("BurstSounds");
    /**
     * The Speed for bigAsteroid.
     */
    int speed;
    /**
     * The Total life of one bigAsteroid.
     */
    int totalLife =2;

    /**
     * Instantiates a new Big asteroid.
     *
     * @param speed the speed of big asteroid moving on the scene
     */
    public BigAsteroid(int speed){this.speed = new LowSpeedBehavior().speed();}
    @Override
    public int getSpeed(){
        return speed;
    }

    /**
     * Move big asteroid by the following speed on the scene. Once the big asteroid moves across the scene, destroy it.
     *
     * @param root  Javafx path
     * @param speed the speed of big asteroid moving on the scene
     *
     */
    @Override
    public void moveAsteroid(AnchorPane root, int speed){
            for (int i = 0; i < bigAsteroid.size(); i++) {
                if (bigAsteroid.get(i).getLayoutY() < root.getHeight()) {
                    bigAsteroid.get(i).relocate(bigAsteroid.get(i).getLayoutX(), bigAsteroid.get(i).getLayoutY() + speed);
                } else {
                    root.getChildren().remove(bigAsteroid.get(i));
                    bigAsteroid.remove(i);
                }
            }
    }

    /**
     * Create and move big Asteroid on the scene by specific time period.
     * The higher game score, the faster the big asteroid's speed is.
     * @param root  Javafx path
     * @param speed the speed of big asteroid moving on the scene
     */
    @Override
    public void CreateAndMoveAsteroid(AnchorPane root, int speed) {
        asteroidCounter2++;
        if (!GameController.gameOver){
            if (GameController.score < lowScore) {
                if (asteroidCounter2 % (modifier+offset) == 0) {
                    imgBigAsteroid.creatImg(root);
                }
                moveAsteroid(root,getSpeed()+1);
            } else if (GameController.score < middleScore) {
                if (asteroidCounter2 % (modifier+offset) == 0) {
                    imgBigAsteroid.creatImg(root);
                }

                moveAsteroid(root,getSpeed());
            } else if (GameController.score < highScore) {
                if (asteroidCounter2 % (modifier+offset) == 0) {
                    imgBigAsteroid.creatImg(root);
                }
                moveAsteroid(root,getSpeed()+2);
            } else {
                if (asteroidCounter2 % (modifier+offset) == 0) {
                    imgBigAsteroid.creatImg(root);
                }
                moveAsteroid(root,getSpeed()+3);
            }
        }
    }
    /**
     * Collide.
     * This is if the laser beam collided with asteroid it will cause an explosion, you will gain 4 points.
     * If not collide, the weapons laser beam will be removed from the scene.
     * @param root Javafx path
     *
     */
    @Override
    public void collide(AnchorPane root) {
        for (int i = 0; i < Fire.weapons.size(); i++) {
            for (int j = 0; j < bigAsteroid.size(); j++) {
                if (Fire.weapons.size()>0 && i<Fire.weapons.size() && Fire.weapons.get(i).getBoundsInParent().intersects(bigAsteroid.get(j).getBoundsInParent())) {
                    totalLife--;
                    root.getChildren().remove(Fire.weapons.get(i));
                    Fire.weapons.remove(i);
                    if (totalLife<=0) {
                        soundBurst.PlaySounds();
                        Image imgExplosion = new Image(getClass().getResource("/com/comp2059/app/img/explosion.gif").toExternalForm());
                        ImageView imgViewExplosion = new ImageView(imgExplosion);
                        imgViewExplosion.relocate(bigAsteroid.get(j).getLayoutX(), bigAsteroid.get(j).getLayoutY());
                        root.getChildren().remove(bigAsteroid.get(j));
                        bigAsteroid.remove(j);
                        root.getChildren().add(imgViewExplosion);
                        PauseTransition wait = new PauseTransition(Duration.seconds(0.8));
                        wait.setOnFinished((e) -> {
                            root.getChildren().remove(imgViewExplosion);
                        });
                        wait.play();
                        totalLife =2;
                        GameController.score += 4;
                    }

                }else {
                    if(Fire.weapons.size()>0 && i<Fire.weapons.size() && Fire.weapons.get(i).getLayoutY()<=0){
                        root.getChildren().remove(Fire.weapons.get(i));
                        Fire.weapons.remove(i);
                    }
                }
            }
        }
    }

}
