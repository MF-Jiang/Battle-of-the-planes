package com.comp2059.app.factory;

import com.comp2059.app.img.Img;
import com.comp2059.app.sounds.*;

/**
 * The type Sounds factory using factory design pattern.
 *  @Title SoundsFactory
 */
public class SoundsFactory{

    /**
     * Get sound .
     * create different type of sound by the input string.
     *
     * @param soundType the sound type
     * @return the sound
     */
    public static Sound getSound(String soundType){
        if(soundType == null){
            return null;
        }
        if(soundType.equalsIgnoreCase("BackgroundSounds")){
            return new BackgroundSounds();
        } else if(soundType.equalsIgnoreCase("BurstSounds")){
            return new BurstSounds();
        } else if(soundType.equalsIgnoreCase("PickupSounds")){
            return new PickupSounds();
        }else if(soundType.equalsIgnoreCase("ShootSounds")){
            return new ShootSounds();
        }
        return null;
    }

}
