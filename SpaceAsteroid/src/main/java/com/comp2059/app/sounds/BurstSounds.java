package com.comp2059.app.sounds;

import javafx.scene.media.AudioClip;

/**
 * The type Burst sounds.
 * @Title BurstSounds
 */
public class BurstSounds implements Sound {
    /**
     * The Burst sounds.
     */
    AudioClip burst = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/brust.mp3").toExternalForm());
    @Override
    public void PlaySounds() {

        burst.play();
    }

    /**
     * Get audio clip.
     *
     * @return the audio clip
     */
    public AudioClip getAudio(){
        return burst;
    }
}
