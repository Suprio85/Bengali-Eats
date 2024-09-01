package Client.myapp;

import Models.Resturant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.LoginOb;
import util.socketWrapper;
import util.specifyClient;

import java.io.IOException;

public class ResturantLoginController {
client clnt;

socketWrapper SocketWrapper;

public void setclnt(client client) {
        this.clnt = client;
        }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

 @FXML
  public void OnLoginAction(ActionEvent event) throws IOException, ClassNotFoundException {
        this.SocketWrapper = clnt.getSocketWrapper();
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        LoginOb login = new LoginOb(username, password,"Resturant");

     try {
         SocketWrapper.write(login);
     } catch (IOException e) {
         e.printStackTrace();
     }
     specifyClient sc1 =(specifyClient) SocketWrapper.read();
     if(sc1.getMessage().equals("Resturant")){
         Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
         new ResturantClient(SocketWrapper,(Resturant) sc1.getData(),stage);
     }
     else{
         System.out.println("invalid");
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Login Error");
         alert.setHeaderText("Wrong Resturantname or Password");
         alert.setContentText("Wrong Resturantname or Password");
         alert.showAndWait();
     }
    }

    public void print(String message){
        System.out.println(message);
    }

}
