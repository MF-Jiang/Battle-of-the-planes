package com.comp2059.app.Color;

import javafx.scene.paint.Color;

/**
 * The type Memnto pro.
 * @Title MemntoPro
 */
public class MemntoPro {
    private Color statecolor;

    /**
     * Get color.
     *
     * @return the color
     */
    public Color getColor(){
        return statecolor;
    }

    /**
     * Set state color.
     *
     * @param color the color
     */
    public void setStatecolor(Color color){
        this.statecolor=color;
    }

    /**
     * Save color to memento.
     *
     * @return the memento
     */
    public Memento saveColorToMemento(){
        return new Memento(statecolor);
    }

    /**
     * Get color from memento.
     *
     * @param memento the memento
     */
    public void getColorFromMemento(Memento memento){
        statecolor = memento.getStatecolor();
    }
}
