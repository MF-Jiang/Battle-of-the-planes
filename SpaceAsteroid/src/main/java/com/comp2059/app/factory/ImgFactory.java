package com.comp2059.app.factory;

import com.comp2059.app.img.*;
import com.comp2059.app.sounds.*;

/**
 * The type Img factory using factory design pattern.
 * @Title ImgFactory
 */
public class ImgFactory{

    /**
     * Get img.
     * create different type of Img by the input string.
     *
     * @param ImgType the img type
     * @return the img
     */
    public static Img getImg(String ImgType){
        if(ImgType == null){
            return null;
        }
        if(ImgType.equalsIgnoreCase("BigAsteroid")){
            return new BigAsteroid();
        }else if(ImgType.equalsIgnoreCase("Props")){
            return new Props();
        }else if(ImgType.equalsIgnoreCase("SmallAsteroid")){
            return new SmallAsteroid();
        }
        return null;
    }
}
