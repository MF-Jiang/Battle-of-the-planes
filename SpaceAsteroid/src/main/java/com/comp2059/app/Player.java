package com.comp2059.app;

import com.comp2059.app.asteroid.BigAsteroid;
import com.comp2059.app.asteroid.SmallAsteroid;
import com.comp2059.app.boss.Boss;
import com.comp2059.app.controller.GameController;
import com.comp2059.app.controller.PreController;
import com.comp2059.app.factory.SoundsFactory;
import com.comp2059.app.props.FireProps;
import com.comp2059.app.sounds.Sound;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import static com.comp2059.app.boss.Boss.bossList;
import static com.comp2059.app.controller.GameController.boss;

/**
 * The type Player.
 * @Title Player
 */
public class Player {
    /**
     * The Rocket.
     */
    public static ArrayList<Node> rocket = new ArrayList<>();

    /**
     * The constant HighMark.
     */
    public static ArrayList<String> HighMark = new ArrayList<>();
    /**
     * The soundBurst created by SoundsFactory.
     */
    Sound soundBurst = SoundsFactory.getSound("BurstSounds");

    /**
     * Collide.
     * This is if the shuttle collided with asteroid.
     * If collide with asteroid, the player will be dead and game will be over.
     * At the same time, popup will appear.
     * The score will be compared with the database's data.
     * The top 5 scores will be shown on the scene.
     * @param Rocket the rocket
     * @param root   the root
     * @param timer  the timer
     * @throws IOException the io exception
     */
    public void collide(ImageView Rocket, AnchorPane root, AnimationTimer timer) throws IOException {
        if(GameController.gameOver==false && rocket.size()==0){
            rocket.add(Rocket);
        }
        for (int j = 0; j < SmallAsteroid.asteroid.size(); j++) {
            if (rocket.size()>0 && rocket.get(0).getBoundsInParent().intersects(SmallAsteroid.asteroid.get(j).getBoundsInParent())) {
                GameController.gameOver = true;
                soundBurst.PlaySounds();
                Image imgExplosion = new Image(getClass().getResource("img/explosion.gif").toExternalForm());
                ImageView imgviewExplosion = new ImageView(imgExplosion);
                imgviewExplosion.relocate(rocket.get(0).getLayoutX(), rocket.get(0).getLayoutY());
                root.getChildren().remove(SmallAsteroid.asteroid.get(j));
                PauseTransition wait = new PauseTransition(Duration.seconds(0.8));
                wait.setOnFinished((e) -> {
                    root.getChildren().remove(imgviewExplosion);
                });
                wait.play();
                SmallAsteroid.asteroid.remove(j);
                root.getChildren().add(imgviewExplosion);
                root.getChildren().remove(rocket.get(0));
                rocket.remove(0);

                Alert endshow = new Alert(Alert.AlertType.INFORMATION);
                endshow.setContentText(PreController.namestr+",This time you get "+GameController.score+"!");
                endshow.show();

                Connection c=null;
                Statement stmt =null;
                try{
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
                    c.setAutoCommit(false);
                    stmt=c.createStatement();
                    String sql = "INSERT INTO HighMark ('Name', 'Score')"+
                            "VALUES('"+PreController.namestr+"','"+ GameController.score+"');";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    c.commit();
                    c.close();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Text txtGameOver = new Text(450, 120, "Gameover!");
                txtGameOver.setFill(Color.RED);
                Font font3 = Font.font("Segoui UI", FontWeight.BOLD, FontPosture.REGULAR, 60);
                txtGameOver.setFont(font3);
                root.getChildren().add(txtGameOver);

                GridPane pane = new GridPane();
                c=null;
                stmt=null;
                try{
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
                    c.setAutoCommit(false);
                    stmt=c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM HighMark ORDER BY Score DESC LIMIT 5");
                    while (rs.next()){
                        String name = rs.getString("Name");
                        HighMark.add(name);
                        int mark = rs.getInt("Score");
                        HighMark.add(String.valueOf(mark));
                    }
                    rs.close();
                    stmt.close();
                    c.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                Font font4 = Font.font("Segoui UI", FontWeight.BOLD, FontPosture.REGULAR, 30);
                Text title = new Text("High Mark!");
                title.setFont(font4);
                pane.add(title,0,0);
                if(HighMark.size()>=2){
                    Text no1 = new Text("NO.1\t"+HighMark.get(0)+"\t"+HighMark.get(1));
                    no1.setFont(font4);
                    pane.add(no1,0,1);
                }
                if(HighMark.size()>=4){
                    Text no2 = new Text("NO.2\t"+HighMark.get(2)+"\t"+HighMark.get(3));
                    no2.setFont(font4);
                    pane.add(no2,0,2);
                }
                if(HighMark.size()>=6){
                    Text no3 = new Text("NO.3\t"+HighMark.get(4)+"\t"+HighMark.get(5));
                    no3.setFont(font4);
                    pane.add(no3,0,3);
                }
                if(HighMark.size()>=8){
                    Text no4 = new Text("NO.4\t"+HighMark.get(6)+"\t"+HighMark.get(7));
                    no4.setFont(font4);
                    pane.add(no4,0,4);
                }
                if(HighMark.size()>=10){
                    Text no5 = new Text("NO.5\t"+HighMark.get(8)+"\t"+HighMark.get(9));
                    no5.setFont(font4);
                    pane.add(no5,0,5);
                }
                Text thistime = new Text(PreController.namestr+",This time you get "+GameController.score+"!");
                thistime.setFont(font4);
                pane.add(thistime,0,6);

                pane.setMargin(title,new Insets(0,0,0,50));
                pane.setAlignment(Pos.CENTER);
                pane.setVgap(20);
                pane.setScaleX(1);
                pane.setScaleY(1);
                pane.setPrefSize(500,150);
                pane.setStyle("-fx-background-color: red;");
                pane.setTranslateX(300);
                pane.setTranslateY(150);
                root.getChildren().add(pane);

                if(HighMark.size()>=10){
                    c=null;
                    stmt=null;
                    try{
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
                        c.setAutoCommit(false);
                        stmt=c.createStatement();
                        String sql = "Delete FROM HighMark WHERE Score < "+HighMark.get(9);
                        stmt.executeUpdate(sql);
                        c.commit();
                        stmt.close();
                        c.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

                Button btnMenu = new Button("Menu");
                btnMenu.setScaleX(3);
                btnMenu.setScaleY(2);
                btnMenu.setTextFill(Color.YELLOW);
                btnMenu.setTranslateX(1000);
                btnMenu.setTranslateY(600);
                btnMenu.setStyle("-fx-background-color: red;");
                btnMenu.setFocusTraversable(false);
                root.getChildren().add(btnMenu);
                btnMenu.setOnAction(e -> {
                    //Platform.exit();
                    Stage stage =(Stage) btnMenu.getScene().getWindow();
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
                });

                Button btnRestart = new Button("Restart");
                btnRestart.setScaleX(3);
                btnRestart.setScaleY(2);
                btnRestart.setTextFill(Color.YELLOW);
                btnRestart.setTranslateX(200);
                btnRestart.setTranslateY(600);
                btnRestart.setStyle("-fx-background-color: red;");
                btnRestart.setFocusTraversable(false);
                root.getChildren().add(btnRestart);
                btnRestart.setOnAction(e -> {
                    //Platform.exit();
                    Stage stage =(Stage) btnRestart.getScene().getWindow();
                    Parent menuroot = null;
                    try {
                        Boss.isDestroy = false;
                        boss.isBorn  = true;
                        boss.lives = 5;
                        menuroot = FXMLLoader.load(getClass().getResource("/com.comp2059.app/game-page.fxml"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    timer.stop();
                    GameController.gameOver=false;
                    SmallAsteroid.asteroid.clear();
                    BigAsteroid.bigAsteroid.clear();
                    rocket.clear();
                    FireProps.props.clear();
                    Fire.weapons.clear();
                    Fire.Firelevel=0;
                    GameController.score=0;
                    HighMark.clear();
                    bossList.clear();
                    stage.setTitle("Space Asteroids");
                    stage.setScene(new Scene(menuroot));
                    stage.setResizable(false);
                });
            }
        }
        for (int j = 0; j < BigAsteroid.bigAsteroid.size(); j++) {
            if (rocket.size()>0 && rocket.get(0).getBoundsInParent().intersects(BigAsteroid.bigAsteroid.get(j).getBoundsInParent())) {
                soundBurst.PlaySounds();
                GameController.gameOver = true;
                Image imgExplosion = new Image(getClass().getResource("img/explosion.gif").toExternalForm());
                ImageView imgviewExplosion = new ImageView(imgExplosion);
                imgviewExplosion.relocate(rocket.get(0).getLayoutX(), rocket.get(0).getLayoutY());
                root.getChildren().remove(BigAsteroid.bigAsteroid.get(j));
                PauseTransition wait = new PauseTransition(Duration.seconds(0.8));
                wait.setOnFinished((e) -> {
                    root.getChildren().remove(imgviewExplosion);
                });
                wait.play();
                BigAsteroid.bigAsteroid.remove(j);
                root.getChildren().add(imgviewExplosion);
                root.getChildren().remove(rocket.get(0));
                rocket.remove(0);

                Alert endshow = new Alert(Alert.AlertType.INFORMATION);
                endshow.setContentText(PreController.namestr+",This time you get "+GameController.score+"!");

                endshow.show();

                Connection c=null;
                Statement stmt =null;
                try{
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
                    c.setAutoCommit(false);
                    stmt=c.createStatement();
                    String sql = "INSERT INTO HighMark ('Name', 'Score')"+
                            "VALUES('"+PreController.namestr+"','"+GameController.score+"');";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    c.commit();
                    c.close();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Text txtGameOver = new Text(450, 120, "Gameover!");
                txtGameOver.setFill(Color.RED);
                Font font3 = Font.font("Segoui UI", FontWeight.BOLD, FontPosture.REGULAR, 60);
                txtGameOver.setFont(font3);
                root.getChildren().add(txtGameOver);

                GridPane pane = new GridPane();
                c=null;
                stmt=null;
                try{
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
                    c.setAutoCommit(false);
                    stmt=c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM HighMark ORDER BY Score DESC LIMIT 5");
                    while (rs.next()){
                        String name = rs.getString("Name");
                        HighMark.add(name);
                        int mark = rs.getInt("Score");
                        HighMark.add(String.valueOf(mark));
                    }
                    rs.close();
                    stmt.close();
                    c.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                Font font4 = Font.font("Segoui UI", FontWeight.BOLD, FontPosture.REGULAR, 30);
                Text title = new Text("High Mark!");
                title.setFont(font4);
                pane.add(title,0,0);
                if(HighMark.size()>=2){
                    Text no1 = new Text("NO.1\t"+HighMark.get(0)+"\t"+HighMark.get(1));
                    no1.setFont(font4);
                    pane.add(no1,0,1);
                }
                if(HighMark.size()>=4){
                    Text no2 = new Text("NO.2\t"+HighMark.get(2)+"\t"+HighMark.get(3));
                    no2.setFont(font4);
                    pane.add(no2,0,2);
                }
                if(HighMark.size()>=6){
                    Text no3 = new Text("NO.3\t"+HighMark.get(4)+"\t"+HighMark.get(5));
                    no3.setFont(font4);
                    pane.add(no3,0,3);
                }
                if(HighMark.size()>=8){
                    Text no4 = new Text("NO.4\t"+HighMark.get(6)+"\t"+HighMark.get(7));
                    no4.setFont(font4);
                    pane.add(no4,0,4);
                }
                if(HighMark.size()>=10){
                    Text no5 = new Text("NO.5\t"+HighMark.get(8)+"\t"+HighMark.get(9));
                    no5.setFont(font4);
                    pane.add(no5,0,5);
                }
                Text thistime = new Text(PreController.namestr+",This time you get "+GameController.score+"!");
                thistime.setFont(font4);
                pane.add(thistime,0,6);

                pane.setMargin(title,new Insets(0,0,0,50));
                pane.setAlignment(Pos.CENTER);
                pane.setVgap(20);
                pane.setScaleX(1);
                pane.setScaleY(1);
                pane.setPrefSize(500,150);
                pane.setStyle("-fx-background-color: red;");
                pane.setTranslateX(300);
                pane.setTranslateY(150);
                root.getChildren().add(pane);

                if(HighMark.size()>=10){
                    c=null;
                    stmt=null;
                    try{
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
                        c.setAutoCommit(false);
                        stmt=c.createStatement();
                        String sql = "Delete FROM HighMark WHERE Score < "+HighMark.get(9);
                        stmt.executeUpdate(sql);
                        c.commit();
                        stmt.close();
                        c.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

                Button btnMenu = new Button("Menu");
                btnMenu.setScaleX(3);
                btnMenu.setScaleY(2);
                btnMenu.setTextFill(Color.YELLOW);
                btnMenu.setTranslateX(1000);
                btnMenu.setTranslateY(600);
                btnMenu.setFocusTraversable(false);
                btnMenu.setStyle("-fx-background-color: red;");
                root.getChildren().add(btnMenu);
                btnMenu.setOnAction(e -> {
                    //Platform.exit();
                    Stage stage =(Stage) btnMenu.getScene().getWindow();
                    Parent menuroot = null;
                    try {
                        Boss.isDestroy = false;
                        boss.isBorn  = true;
                        boss.lives = 5;
                        menuroot = FXMLLoader.load(getClass().getResource("/com.comp2059.app/menu-page.fxml"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    timer.stop();
                    GameController.gameOver=false;
                    SmallAsteroid.asteroid.clear();
                    BigAsteroid.bigAsteroid.clear();
                    rocket.clear();
                    FireProps.props.clear();
                    Fire.weapons.clear();
                    Fire.Firelevel=0;
                    GameController.score=0;
                    HighMark.clear();
                    bossList.clear();
                    stage.setTitle("Space Asteroids");
                    stage.setScene(new Scene(menuroot));
                    stage.setResizable(false);
                });

                Button btnRestart = new Button("Restart");
                btnRestart.setScaleX(3);
                btnRestart.setScaleY(2);
                btnRestart.setTextFill(Color.YELLOW);
                btnRestart.setTranslateX(200);
                btnRestart.setTranslateY(600);
                btnRestart.setFocusTraversable(false);
                btnRestart.setStyle("-fx-background-color: red;");
                root.getChildren().add(btnRestart);
                btnRestart.setOnAction(e -> {
                    //Platform.exit();
                    Stage stage =(Stage) btnRestart.getScene().getWindow();
                    Parent menuroot = null;
                    try {
                        menuroot = FXMLLoader.load(getClass().getResource("/com.comp2059.app/game-page.fxml"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    timer.stop();
                    GameController.gameOver=false;
                    SmallAsteroid.asteroid.clear();
                    BigAsteroid.bigAsteroid.clear();
                    rocket.clear();
                    FireProps.props.clear();
                    Fire.weapons.clear();
                    Fire.Firelevel=0;
                    GameController.score=0;
                    HighMark.clear();
                    bossList.clear();
                    stage.setTitle("Space Asteroids");
                    stage.setScene(new Scene(menuroot));
                    stage.setResizable(false);
                });
            }
        }
    }
}
