package com.example.demo.helper;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Pause {

    public static PauseTransition slowShow(double seconds, Pane display, Label label) {
        //to make pause
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(e -> {
            display.getChildren().add(label);
        });
        return pause;
    }

    public static void updateLabelWithPause (Label label, String newText, double seconds,Runnable onFinish){

    PauseTransition pause = new PauseTransition(Duration.seconds(seconds));

    pause.setOnFinished( event -> {
        label.setText(newText);
        if (onFinish != null) {
            onFinish.run();
        }
    });
    pause.play();

    }

    public static void updateImageWithPause( ImageView imageView, Image image, double seconds, Runnable onFinish) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> {
            imageView.setImage(image);
            if (onFinish != null) {
                onFinish.run();
            }
        });
        pause.play();
    }

    public static void updateHorsePlaceWithPause( ImageView imageView, double seconds, double newLayaout, Runnable onFinish) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> {
            imageView.setLayoutX(newLayaout);
            if (onFinish != null) {
                onFinish.run();
            }
        });
        pause.play();
    }



}


