package com.example.demo.helper;

import javafx.scene.control.Alert;

public class AlertUtil {


    /**
     * Method static to show a custom alert
     * @param alertType type of Alert
     * @param title title of the window
     * @param message message in the window
     */
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Method static to show  custom warning alert
     * @param title title of the warning window
     * @param message message in the window of warning
     */
    public static void showWarning(String title, String message) {
        showAlert(Alert.AlertType.WARNING, title, message);
    }

    /**
     * Method to show  custom information alert
     * @param title title of information alert window
     * @param message message of information alert window
     */
    public static void showInformation(String title, String message) {
        showAlert(Alert.AlertType.INFORMATION, title, message);
    }

    /**
     * Method to show error alert
     * @param title title of error alert window
     * @param message message of error alert window
     */
    public static void showError(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);
    }

}
