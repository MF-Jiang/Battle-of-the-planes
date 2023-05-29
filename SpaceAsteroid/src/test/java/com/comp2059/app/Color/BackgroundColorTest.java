package com.comp2059.app.Color;

import com.comp2059.app.Color.BackgroundColor;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BackgroundColorTest {
    BackgroundColor backgroundColor = new BackgroundColor();

    @Test
    void ColorTest(){
        backgroundColor.setColor(Color.RED);
        Assertions.assertEquals(Color.RED,backgroundColor.getColor());
    }
}