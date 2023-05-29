package com.comp2059.app.controller;

import com.comp2059.app.Color.BackgroundColor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * The type Option controller that controls the option-page.fxml.
 * @Title OptionController
 */
public class OptionController {
    /**
     * The constant option page.
     */
    public static BackgroundColor optionpage;
    private Stage stage;
    @FXML private ColorPicker colorpick;
    @FXML private Button Back;
    @FXML private AnchorPane background;
    @FXML private Text text1;
    @FXML private Text text2;
    @FXML private Text text3;
    @FXML private Text text4;
    @FXML private Text text5;
    @FXML private Button Delete;
    private ArrayList<String> data = new ArrayList<>();


    /**
     * Initialize with database using.
     */
    @FXML
    public void initialize(){
        if(optionpage==null){
            optionpage = new BackgroundColor();
        }
        if(optionpage!=null && optionpage.getColor()!=null){
            background.setStyle("-fx-background-color: #"+optionpage.getColor().toString().substring(2));
        }
        Connection c=null;
        Statement stmt =null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
            c.setAutoCommit(false);
            stmt=c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HighMark ORDER BY Score DESC LIMIT 5");
            while (rs.next()){
                String name = rs.getString("Name");
                data.add(name);
                int mark = rs.getInt("Score");
                data.add(String.valueOf(mark));

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font font4 = Font.font("Segoui UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        if(data.size()>=2){
            text1.setText("NO.1\t"+data.get(0)+"\t"+data.get(1));
            text1.setFont(font4);
        }
        if(data.size()>=4){
            text2.setText("NO.2\t"+data.get(2)+"\t"+data.get(3));
            text2.setFont(font4);
        }
        if(data.size()>=6){
            text3.setText("NO.3\t"+data.get(4)+"\t"+data.get(5));
            text3.setFont(font4);
        }
        if(data.size()>=8){
            text4.setText("NO.4\t"+data.get(6)+"\t"+data.get(7));
            text4.setFont(font4);
        }
        if(data.size()>=10){
            text5.setText("NO.5\t"+data.get(8)+"\t"+data.get(9));
            text5.setFont(font4);
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
        stage =(Stage) Back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com.comp2059.app/menu-page.fxml"));
        stage.setTitle("Space Asteroids");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
    }

    /**
     * Choose color.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void choosecolor(ActionEvent actionEvent) throws IOException {
        Color getC = colorpick.getValue();
        optionpage.setColor(getC);
        background.setStyle("-fx-background-color: #"+optionpage.getColor().toString().substring(2));
        colorpick.setValue(getC);
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/com.comp2059.app/option-page.fxml"));
            String inputtext = null;
            String str;
            Boolean firstline = true;
            while ((str = in.readLine()) != null) {
                if(firstline==true){
                    inputtext = str+"\n";
                    firstline = false;
                }else {
                    if(str.contains("AnchorPane fx:id=\"background\"")){
                        str= "<AnchorPane fx:id=\"background\" maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"720.0\" prefWidth=\"1200.0\" style=\"-fx-background-color: #"+ optionpage.getColor().toString().substring(2) +";\" xmlns=\"http://javafx.com/javafx/18\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"com.comp2059.app.controller.OptionController\">";
                    }
                    inputtext = inputtext+str+"\n";
                }
            }
            in.close();
            File file = new File("src/main/resources/com.comp2059.app/option-page.fxml");
            file.delete();
            FileWriter fw = new FileWriter(new File("src/main/resources/com.comp2059.app/option-page.fxml"));
            BufferedWriter out = new BufferedWriter(fw);
            out.write(inputtext);
            out.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/com.comp2059.app/menu-page.fxml"));
            String inputtext = null;
            String str;
            Boolean firstline = true;
            while ((str = in.readLine()) != null) {
                if(firstline==true){
                    inputtext = str+"\n";
                    firstline = false;
                }else {
                    if(str.contains("AnchorPane fx:id=\"MenuPage\"")){
                        str="<AnchorPane fx:id=\"MenuPage\" maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"720.0\" prefWidth=\"1200.0\" style=\"-fx-background-color: #"+optionpage.getColor().toString().substring(2)+";\" xmlns=\"http://javafx.com/javafx/18\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"com.comp2059.app.controller.MenuController\">";
                    }
                    inputtext = inputtext+str+"\n";
                }
            }
            in.close();
            File file = new File("src/main/resources/com.comp2059.app/menu-page.fxml");
            file.delete();
            FileWriter fw = new FileWriter(new File("src/main/resources/com.comp2059.app/menu-page.fxml"));
            BufferedWriter out = new BufferedWriter(fw);
            out.write(inputtext);
            out.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/com.comp2059.app/info-page.fxml"));
            String inputtext = null;
            String str;
            Boolean firstline = true;
            while ((str = in.readLine()) != null) {
                if(firstline==true){
                    inputtext = str+"\n";
                    firstline = false;
                }else {
                    if(str.contains("AnchorPane fx:id=\"Info\"")){
                        str="<AnchorPane fx:id=\"Info\" fx:controller=\"com.comp2059.app.controller.InfoController\" maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"720.0\" prefWidth=\"1200.0\" style=\"-fx-background-color: #"+optionpage.getColor().toString().substring(2)+";\" xmlns=\"http://javafx.com/javafx/18\" xmlns:fx=\"http://javafx.com/fxml/1\">";
                    }
                    inputtext = inputtext+str+"\n";
                }
            }
            in.close();
            File file = new File("src/main/resources/com.comp2059.app/info-page.fxml");
            file.delete();
            FileWriter fw = new FileWriter(new File("src/main/resources/com.comp2059.app/info-page.fxml"));
            BufferedWriter out = new BufferedWriter(fw);
            out.write(inputtext);
            out.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/com.comp2059.app/pre-page.fxml"));
            String inputtext = null;
            String str;
            Boolean firstline = true;
            while ((str = in.readLine()) != null) {
                if(firstline==true){
                    inputtext = str+"\n";
                    firstline = false;
                }else {
                    if(str.contains("AnchorPane fx:id=\"prepage\"")){
                        str="<AnchorPane fx:id=\"prepage\" maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"720.0\" prefWidth=\"1200.0\" style=\"-fx-background-color: #"+optionpage.getColor().toString().substring(2)+";\" xmlns=\"http://javafx.com/javafx/18\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"com.comp2059.app.controller.PreController\">";
                    }
                    inputtext = inputtext+str+"\n";
                }
            }
            in.close();
            File file = new File("src/main/resources/com.comp2059.app/pre-page.fxml");
            file.delete();
            FileWriter fw = new FileWriter(new File("src/main/resources/com.comp2059.app/pre-page.fxml"));
            BufferedWriter out = new BufferedWriter(fw);
            out.write(inputtext);
            out.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete data on the database and print it out on the scene.
     *
     * @param actionEvent the action event
     */
    public void Deletedata(ActionEvent actionEvent) {
        Font font4 = Font.font("Segoui UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        text1.setText(" ");
        text1.setFont(font4);
        text2.setText(" ");
        text2.setFont(font4);
        text3.setText(" ");
        text3.setFont(font4);
        text4.setText(" ");
        text4.setFont(font4);
        text5.setText(" ");
        text5.setFont(font4);

        Connection c=null;
        Statement stmt =null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:highmark.db");
            c.setAutoCommit(false);
            stmt=c.createStatement();
            String sql = "DELETE FROM HighMark";
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
}
