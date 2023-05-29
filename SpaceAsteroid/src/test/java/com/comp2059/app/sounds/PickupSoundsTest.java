package com.comp2059.app.sounds;

import com.comp2059.app.factory.SoundsFactory;
import javafx.scene.media.AudioClip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PickupSoundsTest {
    PickupSounds sound =(PickupSounds) SoundsFactory.getSound("PickupSounds");
    AudioClip pickup = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/pickup.mp3").toExternalForm());
    @Test
    public void AudioTest(){
        Assertions.assertEquals(pickup.getSource(),sound.getAudio().getSource());
    }

}