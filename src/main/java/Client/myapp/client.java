package Client.myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.socketWrapper;

import java.io.IOException;

public class client extends Application {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    private socketWrapper s;

    public socketWrapper getSocketWrapper() {
        return s;
    }






    @Override
    public void start(Stage Primarystage) throws Exception {
        this.stage = Primarystage;
        connectToServer();
        System.out.println("Connected to server");
        showLoginpage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        s = new socketWrapper(serverAddress, serverPort);
    }

    public void showLoginpage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        // Set the controller for the FXML file (replace YourControllerClass with your actual controller class)
       // loader.setController(new LoginController());
        System.out.println("loded");
        Parent root = loader.load();
        System.out.println("loded 2");
        LoginController loginController = loader.getController();
        loginController.setclnt(this);
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
        loginController.print("paul");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
    }

}

