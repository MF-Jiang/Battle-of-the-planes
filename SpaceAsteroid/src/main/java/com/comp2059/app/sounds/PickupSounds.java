package com.comp2059.app.sounds;

import javafx.scene.media.AudioClip;

/**
 * The type Pickup sounds.
 * @Title PickupSounds
 */
public class PickupSounds implements Sound{
    /**
     * The Pickup sounds.
     */
    AudioClip pickup = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/pickup.mp3").toExternalForm());
    @Override
    public void PlaySounds() {
        pickup.play();
    }

    /**
     * Get audio clip.
     *
     * @return the audio clip
     */
    public AudioClip getAudio(){
        return pickup;
    }
}
