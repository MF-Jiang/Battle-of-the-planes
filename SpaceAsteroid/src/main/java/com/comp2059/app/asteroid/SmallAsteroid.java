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
 * The type Small asteroid extends the Asteroid which contains all the variable and method of smallAsteroid.
 */
public class SmallAsteroid extends Asteroid {
    /**
     * The Asteroid.
     * This array list is used to store spawned small asteroids.
     */
    public static ArrayList<Node> asteroid = new ArrayList<>();

    /**
     * The Img small asteroid created by the ImgFactory.
     */
    Img imgSmallAsteroid = ImgFactory.getImg("SmallAsteroid");
    /**
     * The Sound burst created by the SoundsFactory.
     */
    Sound soundBurst = SoundsFactory.getSound("BurstSounds");
    /**
     * The Speed for smallAsteroid.
     */
    int speed;

    /**
     * Instantiates a new Small asteroid.
     *
     * @param speed the speed of small asteroid moving on the scene
     */
    public SmallAsteroid(int speed){
        this.speed = new HighSpeedBehavior().speed();
    }
    @Override
    public int getSpeed(){
        return speed;
    }


    /**
     * Move small asteroid by the following speed on the scene. Once the big asteroid moves across the scene, destroy it.
     *
     * @param root  Javafx path
     * @param speed the speed of small asteroid moving on the scene.
     */
    @Override
    public void moveAsteroid(AnchorPane root, int speed){
        for (int i = 0; i < asteroid.size(); i++) {
            //System.out.println(  asteroid.get(i).getLayoutY() +""+ -root.getHeight());
            if (asteroid.get(i).getLayoutY() < root.getHeight()) {
                asteroid.get(i).relocate(asteroid.get(i).getLayoutX(), asteroid.get(i).getLayoutY() + speed);
            } else {
                root.getChildren().remove(asteroid.get(i));
                asteroid.remove(i);
            }
        }
    }


    /**
     * Create and move small Asteroid on the scene by specific time period.
     * The higher game score, the faster the big asteroid's speed is.
     * @param root  Javafx path
     * @param speed the speed of big asteroid moving on the scene
     */
    @Override
    public void CreateAndMoveAsteroid(AnchorPane root, int speed) {
        asteroidCounter++;
        int smallAsteroidNumber = 0; //how many small Asteroid will be created
        //asteroidCounter2++;
        if (!GameController.gameOver){
            if (GameController.score < lowScore) {
                if (asteroidCounter % modifier == 0) {
                    smallAsteroidNumber++;
                    for (int i=0;i<smallAsteroidNumber;i++){
                        imgSmallAsteroid.creatImg(root);
                    }
                }
                moveAsteroid(root,getSpeed());
            } else if (GameController.score < middleScore) {
                if (asteroidCounter % modifier == 0) {
                    smallAsteroidNumber++;
                    for (int i=0;i<smallAsteroidNumber;i++){
                        imgSmallAsteroid.creatImg(root);
                    }
                }
                moveAsteroid(root,getSpeed()+1);
            } else if (GameController.score < highScore) {
                if (asteroidCounter % modifier == 0) {
                    smallAsteroidNumber++;
                    for (int i=0;i<smallAsteroidNumber;i++){
                        imgSmallAsteroid.creatImg(root);
                    }
                }
                moveAsteroid(root,getSpeed());
            } else {
                if (asteroidCounter % modifier == 0) {
                    smallAsteroidNumber++;
                    for (int i=0;i<smallAsteroidNumber;i++){
                        imgSmallAsteroid.creatImg(root);
                    }
                }
                moveAsteroid(root,getSpeed()+2);
            }
        }
    }
    /**
     * Collide.
     * This is if the laser beam collided with asteroid it will cause an explosion, you will gain 2 points.
     * If not collide, the weapons laser beam will be removed from the scene.
     * @param root Javafx path
     *
     */
    @Override
    public void collide(AnchorPane root) {
        for (int i = 0; i < Fire.weapons.size(); i++) {
            for (int j = 0; j < asteroid.size(); j++) {
                //System.out.println(Fire.weapons.size()+" "+asteroid.size());
                if (Fire.weapons.size()>0 && i<Fire.weapons.size() && Fire.weapons.get(i).getBoundsInParent().intersects(asteroid.get(j).getBoundsInParent())) {
                    soundBurst.PlaySounds();
                    //System.out.println("hitsmall");
                    Image imgExplosion = new Image(getClass().getResource("/com/comp2059/app/img/explosion.gif").toExternalForm());
                    ImageView imgViewExplosion = new ImageView(imgExplosion);
                    imgViewExplosion.relocate(asteroid.get(j).getLayoutX(), asteroid.get(j).getLayoutY());
                    root.getChildren().remove(asteroid.get(j));
                    asteroid.remove(j);
                    root.getChildren().add(imgViewExplosion);
                    PauseTransition wait = new PauseTransition(Duration.seconds(0.8)); // This is so the explosion doesn't infinitely loop.
                    wait.setOnFinished((e) -> {
                        root.getChildren().remove(imgViewExplosion);
                    });
                    wait.play();
                    root.getChildren().remove(Fire.weapons.get(i));
                    Fire.weapons.remove(i);
                    GameController.score += 2;
                    //GameController.Score.setText("Score: " + GameController.score);
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
