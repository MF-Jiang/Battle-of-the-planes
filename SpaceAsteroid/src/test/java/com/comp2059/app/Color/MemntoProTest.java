package com.comp2059.app.Color;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemntoProTest {
    MemntoPro memntoPro = new MemntoPro();
    @Test
    public void ColorTest(){
        memntoPro.setStatecolor(Color.YELLOW);
        Assertions.assertEquals(Color.YELLOW,memntoPro.getColor());
    }
    @Test
    public void MementoColorTest(){
        memntoPro.setStatecolor(Color.YELLOW);
        Memento memento = memntoPro.saveColorToMemento();
        memntoPro.getColorFromMemento(memento);
        Assertions.assertEquals(Color.YELLOW,memntoPro.getColor());
    }
}