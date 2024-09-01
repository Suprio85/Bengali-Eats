package Controllers;

import Client.myapp.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.LoginOb;

import java.io.IOException;

public class LoginController {

    client clnt;

    public void setClient(client clnt){
        this.clnt = clnt;
    }
    @FXML
   public TextField userName;
    @FXML
    public PasswordField password;

    @FXML
    Button loginButton;

    @FXML
    Button loginasCustomerButton;

    public LoginController() {

    }

    @FXML


    public void init(){

    }

    @FXML
    public void LoginAction(ActionEvent action){

        System.out.println("Login button clicked");
        String username = this.userName.getText();
        String password = this.password.getText();
        System.out.println(username);
        System.out.println(password);
        LoginOb login = new LoginOb(username,password,"Customer");
        try {
            clnt.getSocketWrapper().write(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
