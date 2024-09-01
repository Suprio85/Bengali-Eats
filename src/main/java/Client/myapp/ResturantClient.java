package Client.myapp;

import Models.Resturant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.socketWrapper;

import java.io.IOException;

public class ResturantClient{

    socketWrapper SocketWrapper;
    Resturant r;

    Stage stage;


    ResturantClient(socketWrapper SocketWrapper, Resturant r, Stage stage) throws IOException {
        this.stage=stage;
        this.SocketWrapper = SocketWrapper;
        this.r = r;
        ShowResturantHome();
    }

    public void ShowResturantHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResturantHome.fxml"));
        Parent root = loader.load();
        ResturantHomeController resturantHomeController = loader.getController();
        new ReadResturant(SocketWrapper,r,resturantHomeController);
        resturantHomeController.setResturant(r);
        resturantHomeController.setsocketWrapper(SocketWrapper);
        stage.setTitle("Customer Home Page");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
