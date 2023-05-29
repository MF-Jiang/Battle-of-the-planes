package com.comp2059.app.sounds;

import javafx.scene.media.AudioClip;

/**
 * The type Shoot sounds.
 * @Title ShootSounds
 */
public class ShootSounds implements Sound{
    /**
     * The Shoot sounds.
     */
    AudioClip shoot = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/shoot.mp3").toExternalForm());
    @Override
    public void PlaySounds() {

        shoot.play();
    }

    /**
     * Get audio clip.
     *
     * @return the audio clip
     */
    public AudioClip getAudio(){
        return shoot;
    }
}
