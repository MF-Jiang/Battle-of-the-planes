package com.comp2059.app.Color;

import javafx.scene.paint.Color;

/**
 * The type Memento.
 * @Title Memento
 */
public class Memento {
    private Color statecolor;

    /**
     * Instantiates a new Memento.
     *
     * @param color the color
     */
    public Memento(Color color){
        this.statecolor = color;
    }

    /**
     * Get state color.
     *
     * @return the color
     */
    public Color getStatecolor(){
        return statecolor;
    }
}
