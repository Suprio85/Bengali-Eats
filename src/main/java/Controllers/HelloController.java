package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        welcomeText.setStyle("-fx-text-fill: red;");
        welcomeText.setStyle("-fx-font-size: 24px;");
        welcomeText.setStyle("-fx-font-weight: bold;    font-style: italic;");
       // welcomeText.setStyle("-fx-background-color: yellow;   -fx-text-fill: red;   -fx-font-size: 24px;   -fx-font-weight: bold;   -fx-font-style: italic;");
        welcomeText.setStyle("-fx-background-color: blue;   -fx-text-fill: black;   -fx-font-size: 24px;   -fx-font-weight: bold;   -fx-font-style: italic;   -fx-border-color: violet;   -fx-border-width: 5px;");
    }

    @FXML
    protected void onGoodbyeButtonClick() {
        welcomeText.setText("Goodbye!");
    }
}