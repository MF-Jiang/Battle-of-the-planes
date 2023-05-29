package com.comp2059.app.Color;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MementoTest {
    Memento memento = new Memento(Color.YELLOW);
    @Test
    public void MementoColorTest(){
        Assertions.assertEquals(Color.YELLOW,memento.getStatecolor());
    }
}