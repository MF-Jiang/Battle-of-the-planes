package com.comp2059.app.asteroid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighSpeedBehaviorTest {
    HighSpeedBehavior highSpeedBehavior = new HighSpeedBehavior();
    @Test
    void speed() {
        Assertions.assertEquals(2, highSpeedBehavior.speed());
    }
}