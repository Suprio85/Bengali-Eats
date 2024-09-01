package Client.myapp;

import Models.Resturant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AddFoodOb;
import util.socketWrapper;

public class AddFoodController {

    @FXML
    private TextField FoodName;
    @FXML
    private TextField Category;
    @FXML
    private TextField Price;
    private socketWrapper SocketWrapper;
    private Resturant resturant;

    private boolean isadded = false;

    public void setisadded(boolean isadded) {
        this.isadded = isadded;
    }

    public boolean getisadded(){
        return isadded;
    }



    public void AddFoodClick(ActionEvent event) {
        String foodName = FoodName.getText();
        String category = Category.getText();
        String priceText = Price.getText();
        double price = 0;
        if (!priceText.isEmpty()) {
            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                System.out.println("Price is not a number");
            }
        }
        System.out.println("FoodName: " + foodName);
        System.out.println("Category: " + category);
        System.out.println("Price: " + price);
        AddFoodOb addFoodOb = new AddFoodOb(resturant.getId(), foodName, category, price);

        try {
            System.out.println("Writing");
            SocketWrapper.write(addFoodOb);
            System.out.println("Written");
        } catch (Exception e) {
            System.out.println("Error in writing");
        }
        try{
            Thread.sleep(Long.parseLong("100"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(isadded){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Food Added");
            alert.setHeaderText("Food Added Successfully");
            alert.setContentText("Food Added Successfully");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Food Not Added");
            alert.setHeaderText("Food Was already Added");
            alert.setContentText("Food was already Added");
            alert.showAndWait();
        }
        System.out.println(isadded);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();
    }

    public void setResturant(Resturant resturant) {
        this.resturant= resturant;

    }

    public void setSocketWrapper(socketWrapper SocketWrapper) {
        this.SocketWrapper= SocketWrapper;
    }

    public void print(String message){
        System.out.println(message);
    }
}
