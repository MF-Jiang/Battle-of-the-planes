package com.comp2059.app.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Container.
 * @Title Container
 */
public class Container {
    private List<Memento> mementoList = new ArrayList<Memento>();

    /**
     * Add Memento into mementoList.
     *
     * @param state the state
     */
    public void add(Memento state){
        mementoList.add(state);
    }

    /**
     * Get memento from mementoList by index.
     *
     * @param index the index
     * @return the memento
     */
    public Memento get(int index){
        return mementoList.get(index);
    }
}
