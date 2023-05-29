package com.comp2059.app.props;

import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropsTest {
    Props props = new Props() {
        @Override
        public void moveProps(AnchorPane root, int speed) {

        }

        @Override
        public void CreateProps(AnchorPane root, int speed) {

        }

        @Override
        public void collide(AnchorPane root) {

        }
    };
    @Test
    void getSpeed() {
        props.speed=2;
        Assertions.assertEquals(2,props.getSpeed());
    }
}