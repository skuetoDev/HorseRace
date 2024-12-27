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

    /**
     * Method to add a pause with change in one label
     *
     * @param seconds of the pause
     * @param display Object Pane name of the label to change
     * @param label   label to change a transition
     * @return Object PauseTransition, null if is the end of chain.
     */
    public static PauseTransition slowShow(double seconds, Pane display, Label label) {
        //to make pause
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(e -> {
            display.getChildren().add(label);
        });
        return pause;
    }

    /**
     * Method to update a label with a pause
     *
     * @param label    Name of update
     * @param newText  String to update the label
     * @param seconds  int with duration
     * @param onFinish Runnable Object next to make transition, null if is the end of chain.
     */
    public static void updateLabelWithPause(Label label, String newText, double seconds, Runnable onFinish) {

        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));

        pause.setOnFinished(event -> {
            label.setText(newText);
            if (onFinish != null) {
                onFinish.run();
            }
        });
        pause.play();

    }

    /**
     * Method to load an object ImageView with an Image
     *
     * @param imageView name to update
     * @param image     Object to update
     * @param seconds   duration of image
     * @param onFinish  Runnable Object next to make transition,null if is the end of chain.
     */
    public static void updateImageWithPause(ImageView imageView, Image image, double seconds, Runnable onFinish) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> {
            imageView.setImage(image);
            if (onFinish != null) {
                onFinish.run();
            }
        });
        pause.play();
    }

    /**
     * Metod to update a ImageView position in Y axis
     *
     * @param imageView to update (horse) position
     * @param seconds   duration of the movement
     * @param newLayout newPosition of the ImageView
     * @param onFinish  Runnable Object next to make transition, null if is the end of chain.
     */
    public static void updateHorsePlaceWithPause(ImageView imageView, double seconds, double newLayout, Runnable onFinish) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> {
            imageView.setLayoutX(newLayout);
            if (onFinish != null) {
                onFinish.run();
            }
        });
        pause.play();
    }


}


