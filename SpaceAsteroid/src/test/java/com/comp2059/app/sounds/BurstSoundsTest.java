package com.comp2059.app.sounds;

import com.comp2059.app.factory.SoundsFactory;
import javafx.scene.media.AudioClip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BurstSoundsTest {
    BurstSounds sound =(BurstSounds) SoundsFactory.getSound("BurstSounds");
    AudioClip burst = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/brust.mp3").toExternalForm());
    @Test
    public void AudioTest(){
        Assertions.assertEquals(burst.getSource(),sound.getAudio().getSource());
    }

}