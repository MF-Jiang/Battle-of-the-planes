package com.comp2059.app;

import javafx.scene.Node;

import java.util.ArrayList;

import static com.comp2059.app.controller.GameController.H;

/**
 * The type Fire.
 * @Title Fire
 */
public class Fire {
    /**
     * The Weapons.
     * This is an array list that stores the laser beams that are fired.
     */
    public static ArrayList<Node> weapons = new ArrayList<>();
    /**
     * The constant Fire level.
     * This game has three fire levels.
     */
    public static int Firelevel =0;

    /**
     * Fire.
     * This method causes the laser beam to move vertically upwards.
     * This game has three fire levels.
     * level 0 will shoot one laser beam at one time.
     * level 1 will shoot two laser beams at one time.
     * level 2 will shoot three laser beams at one time.
     * @param deltas the deltas
     */
    public void fire(int deltas) {
        if(Firelevel==0){
            for (int i = 0; i < weapons.size(); i++) {
                if (weapons.get(i).getLayoutY() < H) {
                    weapons.get(i).relocate(weapons.get(i).getLayoutX(), weapons.get(i).getLayoutY() - deltas);

                } else {
                    weapons.remove(i);
                }
            }
            //System.out.println(weapons.size());
        }else if (Firelevel==1) {
            for (int i = 0; i < weapons.size(); i++) {
                if (weapons.get(i).getLayoutY() < H) {
                    weapons.get(i).relocate(weapons.get(i).getLayoutX(), weapons.get(i).getLayoutY() - deltas);

                } else {
                    weapons.remove(i);
                }
            }
        } else if (Firelevel==2) {
            for (int i = 0; i < weapons.size(); i++) {
                if (weapons.get(i).getLayoutY() < H) {
                    weapons.get(i).relocate(weapons.get(i).getLayoutX(), weapons.get(i).getLayoutY() - deltas);
                } else {
                    weapons.remove(i);
                }
            }
        }
    }
}
