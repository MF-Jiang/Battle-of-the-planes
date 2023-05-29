package com.comp2059.app.controller;

import com.comp2059.app.Fire;
import com.comp2059.app.Player;
import com.comp2059.app.asteroid.*;
import com.comp2059.app.factory.SoundsFactory;
import com.comp2059.app.props.FireProps;
import com.comp2059.app.props.Props;
import com.comp2059.app.boss.Boss;
import com.comp2059.app.sounds.Sound;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static com.comp2059.app.Player.HighMark;
import static com.comp2059.app.Player.rocket;
import static com.comp2059.app.boss.Boss.bossList;

/**
 * The type Game controller control game-page.fxml.
 * @Title GameController
 */
public class GameController {
    /**
     * The constant lowScore.
     */
    public static int lowScore =25;
    /**
     * The constant middleScore.
     */
    public static int middleScore = 60;
    /**
     * The constant highScore.
     */
    public static int highScore = 85;
    /**
     * The L of the scene.
     */
    public static double L = 800,
    /**
     * The W of the scene.
     */
    W = 1000,
    /**
     * The H of the scene.
     */
    H = 720;
    /**
     * The Left boundary.
     */
    int leftBoundary = -50;
    /**
     * The Right boundary.
     */
    int rightBoundary = 1120;
    /**
     * The Top boundary.
     */
    int topBoundary = -7;
    /**
     * The Down boundary.
     */
    int downBoundary = 560;
    /**
     * The Timerbock.
     */
    boolean timerbock = false;
    /**
     * The Go up.
     */
    boolean goUp,
    /**
     * The Go down.
     */
    goDown,
    /**
     * The Go left.
     */
    goLeft,
    /**
     * The Go right.
     */
    goRight,
    /**
     * The Shoot.
     */
    shoot;
    /**
     * The constant gameOver.
     */
    public static boolean gameOver =false;
    /**
     * The Timeout.
     */
    public Boolean Timeout = false;
    /**
     * The Score.
     */
    public static int score =0;
    /**
     * The Root.
     */
    @FXML AnchorPane root;
    /**
     * The Score.
     */
    @FXML Text Score;
    /**
     * The Rocket.
     */
    @FXML ImageView Rocket;
    /**
     * The Asteroid.
     */
    static Asteroid smallAsteroid = new SmallAsteroid(new HighSpeedBehavior().speed());
    /**
     * The constant bigAsteroid.
     */
    public static Asteroid bigAsteroid = new BigAsteroid(new LowSpeedBehavior().speed());
    /**
     * The Boss.
     */
    public static Boss boss = Boss.getInstance();
    /**
     * The constant prop.
     */
    public static Props prop = new FireProps();
    /**
     * The Shuttle.
     */
    Player shuttle = new Player();
    /**
     * The Fire.
     */
    static Fire fire = new Fire();

    /**
     * The Timeout pane.
     */
    Pane timeoutpane = new Pane();
    /**
     * The Timeout txt.
     */
    Label Timeouttxt = new Label("TIME OUT!");

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        score = 0;
        boss.isBorn=true;
        boss.isBorn2=true;
        boss.isBorn3=true;
        Boss.isDestroy=false;
        boss.lives=5;
        timer = new AnimationTimer() {
            double delta = 2;

            /**
             * Move the player in the delta speed and create item in the scene.
             * @param now
             *
             */
            @Override
            public void handle(long now) {
                if(Timeout==false){
                    double currX = Rocket.getLayoutX();
                    double currY = Rocket.getLayoutY();
                    if (Rocket.getLayoutX() < rightBoundary && Rocket.getLayoutX()>leftBoundary && Rocket.getLayoutY()>topBoundary && Rocket.getLayoutY()<downBoundary)
                    {
                        if (goUp) {
                            currY -= delta;
                        }
                        if (goDown) {
                            currY += delta;
                        }
                        if (goLeft) {
                            currX -= delta;
                        }
                        if (goRight) {
                            currX += delta;
                        }
                    }
                    if(Rocket.getLayoutX()>=rightBoundary){
                        currX-=delta;
                    }
                    if(Rocket.getLayoutX()<=leftBoundary){
                        currX+=delta;
                    }
                    if(Rocket.getLayoutY()>=downBoundary){
                        currY-=delta;
                    }
                    if(Rocket.getLayoutY()<=topBoundary){
                        currY+=delta;
                    }

                    Rocket.relocate(currX, currY);
                    fire.fire(5);
                    if(!gameOver){
                        try {
                            shuttle.collide(Rocket,root,timer);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    prop.collide(root);
                    prop.moveProps(root, new LowSpeedBehavior().speed());
                    bigAsteroid.CreateAndMoveAsteroid(root, bigAsteroid.getSpeed());
                    bigAsteroid.collide(root);
                    smallAsteroid.CreateAndMoveAsteroid(root, smallAsteroid.getSpeed());
                    smallAsteroid.collide(root);

                    boss.createBoss(root);
                    boss.collide(root);
                    Score.setText("Score: "+score);
                    //System.out.println(gameOver);

                }
            }
        };
    }

    /**
     * The Timer.
     */
    AnimationTimer timer = new AnimationTimer() {
        double delta = 2;

        /**
         * @param now
         * Move the player in the delta speed and create item in the scene
         */
        @Override
        public void handle(long now) {
            if(Timeout==false){
                double currX = Rocket.getLayoutX();
                double currY = Rocket.getLayoutY();
                if (Rocket.getLayoutX() < rightBoundary && Rocket.getLayoutX()>leftBoundary && Rocket.getLayoutY()>topBoundary && Rocket.getLayoutY()<downBoundary)
                {
                    if (goUp) {
                        currY -= delta;
                    }
                    if (goDown) {
                        currY += delta;
                    }
                    if (goLeft) {
                        currX -= delta;
                    }
                    if (goRight) {
                        currX += delta;
                    }
                }
                if(Rocket.getLayoutX()>=rightBoundary){
                    currX-=delta;
                }
                if(Rocket.getLayoutX()<=leftBoundary){
                    currX+=delta;
                }
                if(Rocket.getLayoutY()>=downBoundary){
                    currY-=delta;
                }
                if(Rocket.getLayoutY()<=topBoundary){
                    currY+=delta;
                }

                Rocket.relocate(currX, currY);
                fire.fire(5);
                if(!gameOver){
                    try {
                        shuttle.collide(Rocket,root,timer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                prop.getIntoScene(root,new LowSpeedBehavior().speed());

                bigAsteroid.CreateAndMoveAsteroid(root, bigAsteroid.getSpeed());
                bigAsteroid.collide(root);
                smallAsteroid.CreateAndMoveAsteroid(root, smallAsteroid.getSpeed());
                smallAsteroid.collide(root);

                boss.createBoss(root);
                boss.collide(root);
                Score.setText("Score: "+score);
                //System.out.println(gameOver);

            }
        }
    };


    /**
     * Start transfrom.
     * This method can control the rocket that on the scene.
     * @param keyEvent the key event
     */
    @FXML
    public void StartTransfrom(KeyEvent keyEvent) {
        if(!timerbock){
            timer.start();
            timerbock=true;
        }
        switch (keyEvent.getCode()) {
            case UP:
                goUp=true;
                //Rocket.relocate(Rocket.getLayoutX(),Rocket.getLayoutY()-4.0);
                break;
            case DOWN:
                goDown=true;
                //Rocket.relocate(Rocket.getLayoutX(),Rocket.getLayoutY()+4.0);
                break;
            case LEFT:
                goLeft=true;
                //Rocket.relocate(Rocket.getLayoutX()-4.0,Rocket.getLayoutY());
                break;
            case RIGHT:
                goRight=true;
                //Rocket.relocate(Rocket.getLayoutX()+4.0,Rocket.getLayoutY());
                break;
            case SPACE:
                if(gameOver) {
                    return;
                }
                if(!shoot){
                    if(Fire.Firelevel==0){
                        //bullet is here
                        Rectangle rect2 = new Rectangle();
                        rect2.setWidth(5.0f);
                        rect2.setHeight(10.0f);
                        rect2.setFill(Color.RED);
                        Node newWeapon = rect2;
                        newWeapon.relocate(Rocket.getLayoutX() + 55, Rocket.getLayoutY() - 5);
                        Fire.weapons.add(newWeapon);
                        root.getChildren().add(newWeapon);
                        shoot = true;
                    } else if (Fire.Firelevel==1) {
                        //Rotate rec1 = new Rotate(45,100,100);
                        Rectangle rect2 = new Rectangle();
                        rect2.setWidth(5.0f);
                        rect2.setHeight(10.0f);
                        rect2.setFill(Color.RED);
                        //rect2.getTransforms().add(rec1);
                        Node newWeapon = rect2;
                        newWeapon.relocate(Rocket.getLayoutX() + 55, Rocket.getLayoutY() - 5);
                        Fire.weapons.add(newWeapon);
                        root.getChildren().add(newWeapon);

                        //Rotate rec2 = new Rotate(315,100,100);
                        Rectangle rect3 = new Rectangle();
                        rect3.setWidth(5.0f);
                        rect3.setHeight(10.0f);
                        rect3.setFill(Color.BLUE);
                        //rect3.getTransforms().add(rec2);
                        Node newWeapon2 = rect3;
                        newWeapon2.relocate(Rocket.getLayoutX() + 55, Rocket.getLayoutY() - 150);
                        Fire.weapons.add(newWeapon2);




                        root.getChildren().add(newWeapon2);

                        //root.getChildren().add(twoweapon);

                        shoot = true;
                    } else if (Fire.Firelevel==2) {
                        //Rotate rec1 = new Rotate(45,100,100);
                        Rectangle rect2 = new Rectangle();
                        rect2.setWidth(5.0f);
                        rect2.setHeight(10.0f);
                        rect2.setFill(Color.RED);
                        //rect2.getTransforms().add(rec1);
                        Node newWeapon = rect2;
                        newWeapon.relocate(Rocket.getLayoutX() + 55, Rocket.getLayoutY() - 5);
                        Fire.weapons.add(newWeapon);
                        root.getChildren().add(newWeapon);

                        //Rotate rec2 = new Rotate(315,100,100);
                        Rectangle rect3 = new Rectangle();
                        rect3.setWidth(5.0f);
                        rect3.setHeight(10.0f);
                        rect3.setFill(Color.BLUE);
                        //rect3.getTransforms().add(rec2);
                        Node newWeapon2 = rect3;
                        newWeapon2.relocate(Rocket.getLayoutX() + 55, Rocket.getLayoutY() - 150);
                        Fire.weapons.add(newWeapon2);



                        root.getChildren().add(newWeapon2);

                        Rectangle rect4 = new Rectangle();
                        rect4.setWidth(5.0f);
                        rect4.setHeight(10.0f);
                        rect4.setFill(Color.YELLOW);
                        //rect2.getTransforms().add(rec1);
                        Node newWeapon3 = rect4;
                        newWeapon3.relocate(Rocket.getLayoutX() + 55, Rocket.getLayoutY() - 300);
                        Fire.weapons.add(newWeapon3);
                        root.getChildren().add(newWeapon3);

                        //root.getChildren().add(twoweapon);

                        shoot = true;
                    }
                }
                break;
            case ESCAPE:
                if(Timeout==true){
                    Timeout = false;
                    root.getChildren().remove(timeoutpane);
                    timeoutpane.getChildren().clear();
                } else if (Timeout==false) {
                    Timeout = true;

                    Button Continue = new Button("Continue");
                    Button Menu = new Button("Menu");
                    Continue.setStyle("-fx-background-color: red;");
                    Menu.setStyle("-fx-background-color: red;");
                    Continue.setScaleX(6);
                    Continue.setScaleY(4);
                    Menu.setScaleX(8);
                    Menu.setScaleY(4);

                    Continue.setTextFill(Color.YELLOW);
                    Menu.setTextFill(Color.YELLOW);
                    Continue.setTranslateX(600);
                    Continue.setTranslateY(300);
                    Menu.setTranslateX(600);
                    Menu.setTranslateY(500);

                    Continue.setOnAction(e ->{
                        Timeout = false;
                        root.getChildren().remove(timeoutpane);
                        timeoutpane.getChildren().clear();
                    });

                    Menu.setOnAction(e ->{
                        Alert Message = new Alert(Alert.AlertType.CONFIRMATION);
                        Message.setTitle("WARNING");
                        Message.setContentText("Tip: If you return now, the game data will not be saved.");
                        Optional<ButtonType> reuslt = Message.showAndWait();
                        if(reuslt.get() == ButtonType.OK){
                            Stage stage =(Stage) Menu.getScene().getWindow();
                            Parent menuroot = null;
                            try {
                                menuroot = FXMLLoader.load(getClass().getResource("/com.comp2059.app/menu-page.fxml"));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            timer.stop();
                            GameController.gameOver=false;
                            SmallAsteroid.asteroid.clear();
                            BigAsteroid.bigAsteroid.clear();
                            rocket.clear();
                            Fire.weapons.clear();
                            FireProps.props.clear();
                            Fire.Firelevel=0;
                            GameController.score=0;
                            HighMark.clear();
                            bossList.clear();
                            stage.setTitle("Space Asteroids");
                            stage.setScene(new Scene(menuroot));
                            stage.setResizable(false);
                        }else {
                            return;
                        }
                    });

                    Font font = Font.font("Segoui UI", FontWeight.BOLD, FontPosture.REGULAR, 60);
                    Timeouttxt.setFont(font);
                    Timeouttxt.setTranslateX(450);
                    Timeouttxt.setTranslateY(30);
                    Timeouttxt.setTextFill(Color.RED);

                    timeoutpane.getChildren().add(Timeouttxt);
                    timeoutpane.getChildren().add(Continue);
                    timeoutpane.getChildren().add(Menu);

                    root.getChildren().add(timeoutpane);
                }
            default:
                break;
        }

    }

    /**
     * End transfrom.
     * This method can control the rocket that on the scene.
     * @param keyEvent the key event
     */
    @FXML
    public void EndTransfrom(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                goUp = false;
                break;
            case DOWN:
                goDown = false;
                break;
            case LEFT:
                goLeft = false;
                break;
            case RIGHT:
                goRight = false;
                break;
            case SPACE:
                shoot = false;
                Sound soundShoot = SoundsFactory.getSound("ShootSounds");
                soundShoot.PlaySounds();
                break;
            default:
                break;
        }
    }

}
