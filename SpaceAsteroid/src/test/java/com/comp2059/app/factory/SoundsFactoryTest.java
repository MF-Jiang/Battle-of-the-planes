package com.comp2059.app.factory;

import com.comp2059.app.sounds.BurstSounds;
import com.comp2059.app.sounds.Sound;
import javafx.scene.media.AudioClip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundsFactoryTest {
    SoundsFactory soundsFactory = new SoundsFactory();
    BurstSounds sound =(BurstSounds) SoundsFactory.getSound("BurstSounds");
    AudioClip burst = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/brust.mp3").toExternalForm());
    @Test
    public void soundTest(){
        Assertions.assertEquals(burst.getSource(),sound.getAudio().getSource());
        //System.out.println(sound.getAudio().getSource());
        //System.out.println(burst.getSource());
        //System.out.println(sound.getAudio().equals(new AudioClip(getClass().getResource("/com/comp2059/app/sounds/brust.mp3").toExternalForm())));
        //System.out.println(burst.equals(new AudioClip(getClass().getResource("/com/comp2059/app/sounds/brust.mp3").toExternalForm())));
    }

}