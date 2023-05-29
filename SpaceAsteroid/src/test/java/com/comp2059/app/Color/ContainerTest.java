package com.comp2059.app.Color;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container container = new Container();
    Memento memento = new Memento(Color.YELLOW);
    @Test
    public void ContainerTest(){
        container.add(memento);
        Assertions.assertEquals(memento,container.get(0));
    }
}