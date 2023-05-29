package com.comp2059.app.Color;

import javafx.scene.paint.Color;

/**
 * The type Background color.
 * @Title BackgroundColor
 */
public class BackgroundColor {

    /**
     * The Memnto pro.
     */
    public MemntoPro memntoPro = new MemntoPro();
    /**
     * The Container.
     */
    public Container container= new Container();

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        //return color;
        return memntoPro.getColor();
    }

    /**
     * Sets color.
     *
     * @param getC the get c
     */
    public void setColor(Color getC) {
        //color = getC;
        memntoPro.setStatecolor(getC);
        container.add(memntoPro.saveColorToMemento());
    }
}
