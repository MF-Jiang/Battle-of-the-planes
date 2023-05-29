package com.comp2059.app.asteroid;

import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidTest {
    Asteroid asteroid = new Asteroid() {
        @Override
        public void CreateAndMoveAsteroid(AnchorPane root, int speed) {

        }

        @Override
        public void moveAsteroid(AnchorPane root, int speed) {

        }

        @Override
        public void collide(AnchorPane root) {

        }
    };
    @Test
    public void initializeDataTest(){
        Assertions.assertEquals(0,asteroid.asteroidCounter);
        Assertions.assertEquals(0,asteroid.asteroidCounter2);
        Assertions.assertEquals(50,asteroid.offset);
        Assertions.assertEquals(150,asteroid.modifier);
    }
    @Test
    public void getSpeedTest(){
        asteroid.speed=10;
        Assertions.assertEquals(10,asteroid.getSpeed());
    }

}