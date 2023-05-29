package com.comp2059.app.asteroid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowSpeedBehaviorTest {
    LowSpeedBehavior lowSpeedBehavior = new LowSpeedBehavior();
    @Test
    void speed() {
        Assertions.assertEquals(1,lowSpeedBehavior.speed());
    }
}