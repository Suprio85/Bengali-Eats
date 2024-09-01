package Client.myapp;

import Models.Customer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.socketWrapper;

import java.io.IOException;

public class CustomerClient {

    private socketWrapper SocketWrapper;
    private Customer customer;


    private Stage stage;

    public socketWrapper getSocketWrapper() {
        return SocketWrapper;
    }

    public Stage getstage() {
        return stage;
    }

    public void setstage(Stage stage) {
        this.stage = stage;
    }

    public Customer getCustomer() {
        return customer;
    }

    CustomerClient(socketWrapper SocketWrapper, Customer customer, Stage stage) throws IOException {
        this.SocketWrapper = SocketWrapper;
        this.customer = customer;
        this.stage = stage;

        showHomePage();
    }

    CustomerClient(CustomerClient customer) throws IOException {
        this.SocketWrapper = customer.getSocketWrapper();
        this.customer = customer.getCustomer();
        this.stage = customer.getstage();

        showHomePage();
    }



    public void showHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();
        CustomerHomeController customerHomeController = loader.getController();
        customerHomeController.setCustomer(customer);
        customerHomeController.setsocketWrapper(SocketWrapper);
        customerHomeController.setCustomerclient(this);
        stage.setTitle("Customer Home Page");
        stage.setScene(new Scene(root));
        stage.show();
    }
}



