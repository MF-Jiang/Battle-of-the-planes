package com.comp2059.app.sounds;

import com.comp2059.app.factory.SoundsFactory;
import javafx.scene.media.AudioClip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShootSoundsTest {
    ShootSounds sound =(ShootSounds) SoundsFactory.getSound("ShootSounds");
    AudioClip shoot = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/shoot.mp3").toExternalForm());
    @Test
    public void AudioTest(){
        Assertions.assertEquals(shoot.getSource(),sound.getAudio().getSource());
    }
}