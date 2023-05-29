package com.comp2059.app.sounds;

import com.comp2059.app.factory.SoundsFactory;
import javafx.scene.media.AudioClip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackgroundSoundsTest {
    BackgroundSounds sound = (BackgroundSounds) SoundsFactory.getSound("BackgroundSounds");
    AudioClip BGM = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/BGM.wav").toExternalForm());
    @Test
    public void AudioTest(){
        Assertions.assertEquals(BGM.getSource(),sound.getAudio().getSource());
    }
}