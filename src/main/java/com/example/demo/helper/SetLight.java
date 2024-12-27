package com.example.demo.helper;

import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class SetLight {

    /**
     * Method to get lighting Effect in a dynamics buttons
     *
     * @return object of type effect
     */
    public static Effect setLight() {
        Lighting lightingEffect = new Lighting();
        Light.Distant light = new Light.Distant();

        light.setColor(Color.WHITE);
        light.setAzimuth(45);
        light.setElevation(45);
        lightingEffect.setLight(light);

        lightingEffect.setDiffuseConstant(1.37);
        lightingEffect.setSurfaceScale(3.22);

        return lightingEffect;

    }


}
