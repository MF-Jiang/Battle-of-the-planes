package com.comp2059.app.boss;

import com.comp2059.app.Fire;
import com.comp2059.app.controller.GameController;
import com.comp2059.app.factory.SoundsFactory;
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
 * The type Boss.
 * This class is used to create a object.
 * Once it is destroyed, It will generate a property.
 * @Title Boss
 */
public class Boss {
    private static Boss instance = new Boss();

    private Boss() {
    }

    /**
     * Get instance boss. Using Singleton Pattern at this part.
     *
     * @return the boss
     */
    public static  Boss getInstance(){
        return instance;
    }


    /**
     * The constant bossList.
     */
    public static ArrayList<Node> bossList = new ArrayList<>();

    /**
     * The Img boss created directly without Img factory.
     */
    Image imgBoss = new Image(getClass().getResource("/com/comp2059/app/img/Boss.png").toExternalForm());

    /**
     * The Sound burst created by SoundsFactory.
     */
    Sound soundBurst = SoundsFactory.getSound("BurstSounds");

    /**
     * The Lives that boss has.
     */
    public int lives = 5;

    /**
     * The Is born related to score 0.
     */
    public boolean isBorn =true;

    /**
     * The Is born 2 related to score 50 to 60.
     */
    public boolean isBorn2 =true;

    /**
     * The Is born 3 related to score 100 to 110.
     */
    public boolean isBorn3 = true;

    /**
     * The constant isDestroy.
     * Working when reload the scene.
     */
    public static boolean isDestroy = false;

    /**
     * Create a boss in a different game score situation and put them in a random place on the scene.
     * 0, 50 to 60 and 100 to 110 scores will generate a boss.
     * @param root the root
     */
    public void createBoss(AnchorPane root){
        if (!GameController.gameOver){
            if ((GameController.score == 0 && isBorn) ||
                    (GameController.score >= 50 && GameController.score <= 60&& isBorn2) ||
                    (GameController.score >= 100 && GameController.score <= 110 && isBorn3)){

                isBorn = false;
                if (GameController.score>=50)
                    isBorn2 = false;
                if (GameController.score>=100)
                    isBorn3 = false;
                Node newBoss = new ImageView(imgBoss);
                newBoss.relocate((Math.random() * (W *0.7) + 200), (Math.random() / (H)));
                bossList.add(newBoss);
                root.getChildren().add(newBoss);
            }
        }
    }

    /**
     * Collide with laser beam.
     * If the lives become zero, Boss will be destroyed with explosion gif and game score will plus 1.
     * If not collide, the weapons laser beam will be removed from the scene.
     * @param root the root
     */
    public void collide(AnchorPane root) {
        for (int i = 0; i < Fire.weapons.size(); i++) {
            for (int j = 0; j < bossList.size(); j++) {
            if (!bossList.isEmpty()) {
                 if (Fire.weapons.size()>0 && Fire.weapons.get(i).getBoundsInParent().intersects(bossList.get(j).getBoundsInParent())) {
                    root.getChildren().remove(Fire.weapons.get(i));
                    Fire.weapons.remove(i);
                    lives--;
                    if (lives<=0) {
                        soundBurst.PlaySounds();
                        Image imgExplosion = new Image(getClass().getResource("/com/comp2059/app/img/explosion.gif").toExternalForm());
                        ImageView imgviewExplosion = new ImageView(imgExplosion);
                        imgviewExplosion.relocate(bossList.get(j).getLayoutX(), bossList.get(j).getLayoutY());
                        root.getChildren().remove(bossList.get(j));
                        bossList.remove(j);
                        root.getChildren().add(imgviewExplosion);
                        PauseTransition wait = new PauseTransition(Duration.seconds(0.8)); // This is so the explosion doesn't infinitely loop.
                        wait.setOnFinished((e) -> {
                            root.getChildren().remove(imgviewExplosion);
                        });
                        wait.play();
                        //root.getChildren().remove(Fire.weapons. get(i));
                        //Fire.weapons.remove(i);
                        isDestroy = true;

                        prop.CreateProps(root,prop.getSpeed());

                        GameController.score += 1;

                    }  //GameController.Score.setText("Score: " + GameController.score);
                }else {
                     if (Fire.weapons.size() > 0 && Fire.weapons.get(i).getLayoutY() <= 0) {
                         root.getChildren().remove(Fire.weapons.get(i));
                         Fire.weapons.remove(i);
                     }
                 }
                }
            }
        }
    }

}
