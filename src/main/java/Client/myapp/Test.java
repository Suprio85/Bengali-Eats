package Client.myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.socketWrapper;

public class Test extends Application {

    socketWrapper SocketWrapper;

    @Override
    public void start(Stage stage) throws Exception {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        SocketWrapper = new socketWrapper(serverAddress, serverPort);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
