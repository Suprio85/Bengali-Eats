package Client.myapp;

import Models.Food;
import Models.Resturant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import util.Order;
import util.socketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResturantHomeController {
    @FXML
    public ListView PendingOrders;
    public ListView MenuList;
    public Label ResturantName;
    Resturant resturant;

    socketWrapper SocketWrapper;

    Order orders;

    private String CustomerName;

    public boolean isorder=false;

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    public void setisorder(boolean isorder) {
        this.isorder = isorder;
    }

    public void setCustomerName(String customerName) {
        this.CustomerName =customerName;
    }

    private List<String> list = new ArrayList<>();

    private AddFoodController addFoodController;

    private List<String> getFoodList = new ArrayList<>();

    public void setAddFoodController(AddFoodController addFoodController){
        this.addFoodController = addFoodController;

    }

    public AddFoodController getAddFoodController(){
        return addFoodController;
    }

    private int total=0;

    public void setsocketWrapper(socketWrapper SocketWrapper) {
        this.SocketWrapper = SocketWrapper;
    }

    public void setResturant(Resturant r) {
        this.resturant = r;
        ShowMenuList();
        ResturantName.setText(resturant.getName());

    }

    public void printasString(Order order) {
        counttotal();
        list.add("Order From  : "+CustomerName);
        for(String food:order.FoodMap.keySet()){
            list.add(food+"   Quantity: "+order.FoodMap.get(food));
            //System.out.println(food+" "+order.FoodMap.get(food));
        }
        list.add("Total items : "+total);
    }

    public void ShowMenuList(){
        for(Food f : resturant.getMenu()){
       getFoodList.add(f.getName()+"   Price: "+f.getPrice());
        }
        MenuList.getItems().addAll(getFoodList);
    }

    public void counttotal(){
        total=0;
        for(String food: orders.FoodMap.keySet()){
            total+=orders.FoodMap.get(food);
        }
    }

    public void showinScreen(Order order) {
        PendingOrders.getItems().clear();
        printasString(order);
        PendingOrders.getItems().addAll(list);
    }

    public void AddFoodClick(ActionEvent event) throws IOException {
        System.out.println("Add Food Clicked");
        //Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFood.fxml"));
        Parent root = loader.load();
        AddFoodController addFoodController = loader.getController();
        setAddFoodController(addFoodController);
        stage.setTitle("AddFood");
        stage.setScene(new Scene(root));
        stage.show();
        if (addFoodController != null)
            System.out.println("Not Null");
        else{
            System.out.println("Null");
        }
        addFoodController.setResturant(resturant);
        addFoodController.setSocketWrapper(SocketWrapper);
        addFoodController.print("paul");
//        Parent root = loader.load();
//        stage.setTitle("AddFood");
//        stage.setScene(new Scene(root));
//        stage.show();
    }


    public void ServeFoodClick(ActionEvent event) {
        PendingOrders.getItems().clear();
        list.clear();
        total=0;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Orders Delivered");
        alert.setContentText("All Orders Delivered Successfully");
        alert.show();
    }
}
