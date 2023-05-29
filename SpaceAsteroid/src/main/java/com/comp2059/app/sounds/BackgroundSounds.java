package com.comp2059.app.sounds;

import javafx.scene.media.AudioClip;

/**
 * The type Background sounds.
 *  @Title BackgroundSounds
 */
public class BackgroundSounds implements Sound{
    /**
     * The Bgm sounds.
     */
    AudioClip BGM = new AudioClip(getClass().getResource("/com/comp2059/app/sounds/BGM.wav").toExternalForm());
    @Override
    public void PlaySounds() {

        BGM.play();
    }

    /**
     * Stop Bgm sounds.
     */
    public void StopSounds(){
        BGM.stop();
    }

    /**
     * Get audio clip.
     *
     * @return the audio clip
     */
    public AudioClip getAudio(){
        return BGM;
    }
}
