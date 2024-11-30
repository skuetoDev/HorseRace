package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class controller2 {

    public Button playGameButton;
    public Button display2Winners;
    @FXML
    private Button display2BackButton;


    @FXML
    protected  void goToDisplay1(){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display1.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) display2BackButton.getScene().getWindow();

            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    protected void goToDisplay3(){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display3.fxml")));
            Scene scene = new Scene(root);
            Stage stage =(Stage) playGameButton.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
