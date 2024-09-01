package Client.myapp;

import Models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.LoginOb;
import util.socketWrapper;
import util.specifyClient;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private socketWrapper SocketWrapper;

    private client clnt;

    public void setclnt(client client) {
        this.clnt = client;
    }

    public client getclnt() {
        return this.clnt;
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException, ClassNotFoundException {
        this.SocketWrapper = clnt.getSocketWrapper();
        // Implement your login logic here
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        LoginOb login = new LoginOb(username, password,"Customer");
        try {
            SocketWrapper.write(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
        specifyClient sc1 =(specifyClient) SocketWrapper.read();
        if(sc1.getMessage().equals("Customer")){
            Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
            new CustomerClient(SocketWrapper,(Customer)sc1.getData(),stage);

        }
        else{
            System.out.println("invalid");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Wrong Username or Password");
            alert.setContentText("Wrong Username or Password");
            alert.showAndWait();
        }

    }

    public void handelResturantLogin(ActionEvent event) throws IOException {
        Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResturantLogin.fxml"));

        // Set the controller for the FXML file (replace YourControllerClass with your actual controller class)
        // loader.setController(new LoginController());
        //System.out.println("loded");
        Parent root = loader.load();

        ResturantLoginController resturantLoginController = loader.getController();
        resturantLoginController.setclnt(clnt);

        System.out.println("loded 2");
        stage.setTitle("LoginAsResturant");
        stage.setScene(new Scene(root));
        stage.show();
        resturantLoginController.print("Hello World");
    }

    public void print(String message){
        System.out.println(message);
    }
}
