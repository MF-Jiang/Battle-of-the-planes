package com.comp2059.app.props;

import com.comp2059.app.Fire;
import com.comp2059.app.Player;
import com.comp2059.app.boss.Boss;
import com.comp2059.app.factory.ImgFactory;
import com.comp2059.app.factory.SoundsFactory;
import com.comp2059.app.img.Img;
import com.comp2059.app.sounds.Sound;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * The type Fire props.
 * @Title FireProps
 */
public class FireProps extends Props {
    /**
     * The constant props.
     */
    public static ArrayList<Node> props = new ArrayList<>();
    /**
     * The Img props.
     */
    Img imgProps = ImgFactory.getImg("Props");
    /**
     * The Sound pick up.
     */
    Sound soundPickUp = SoundsFactory.getSound("PickupSounds");
    /**
     * The Speed.
     */
    int speed =2;

    @Override
    public int getSpeed(){return speed;}


    @Override
    public void moveProps(AnchorPane root, int speed){
        for (int i = 0; i < props.size(); i++) {
            //System.out.println(  asteroid.get(i).getLayoutY() +""+ -root.getHeight());
            if (props.get(i).getLayoutY() < root.getHeight()) {
                props.get(i).relocate(props.get(i).getLayoutX(), props.get(i).getLayoutY() + speed);
            } else {
                root.getChildren().remove(props.get(i));
                props.remove(i);
            }
        }
    }

    @Override
    public void CreateProps(AnchorPane root, int speed){
        if (Boss.isDestroy){
            imgProps.creatImg(root);
            //moveProps(root,getSpeed());
        }

    }
    @Override
    public void collide(AnchorPane root) {
        for (int i=0; i<props.size();i++){
            if(Player.rocket.size()>0 && props.size()>0 && props.get(i).getBoundsInParent().intersects(Player.rocket.get(0).getBoundsInParent())){
                if(Fire.Firelevel!=2){
                    Fire.Firelevel+=1;
                    soundPickUp.PlaySounds();
                }
                //System.out.println(Fire.Firelevel);
                root.getChildren().remove(props.get(i));
                props.remove(i);
            }else {
                if(Player.rocket.size()>0 && props.size()>0 && props.get(i).getLayoutY()<=0){
                    root.getChildren().remove(props.get(i));
                    props.remove(i);
                }
            }
        }
    }
}
