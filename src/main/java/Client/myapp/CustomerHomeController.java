package Client.myapp;

import Models.Customer;
import Models.Food;
import Models.Resturant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerHomeController implements Initializable {
    @FXML
    public ListView Orders;
    public ChoiceBox<String> ResturantNameBox;
    public Label ProfileName;
    @FXML
    private Label Namebar;
    @FXML
    private ChoiceBox<String> searchFood;
    @FXML
    private TextField searchField;
    @FXML
    private TextField ResturantName;
    @FXML
    private ListView<String> FoodList;

    private String Resturantname;

    private List<String> ResturantList = new ArrayList<String>();

    private List<String> OrderList = new ArrayList<String>();

    private List<String> foodNames = new ArrayList<>();

    private String[] type = {"BY NAME", "BY CATEGORY"};

    private List<Order> Total_Order = new ArrayList<>();

    private List<Food> FoodsList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchFood.getItems().addAll(type);
        searchFood.setOnAction(this::searchFoodclick);
        searchField.setPromptText("Enter Food Name");
    }

   private socketWrapper SocketWrapper;

   private Customer customer;

   private CustomerClient customerClient;



    public void setCustomer(Customer customer) {
        this.customer = customer;
        Namebar.setText("Welcome " + customer.getUsername());
        System.out.println(customer.getUsername());
        ProfileName.setText(customer.getUsername());
    }

    public void setCustomerclient(CustomerClient customerClient){
        this.customerClient = customerClient;
    }


    public CustomerClient getCustomerclient(){
        return customerClient;
    }

    public void getResturantName() throws IOException {
        GetResturant getResturant = new GetResturant();
        System.out.println("in getResturantName");
        SocketWrapper.write(getResturant);
        System.out.println("after write in getResturantName");
        List<Resturant> resturants;
        try {
            resturants = (List<Resturant>) SocketWrapper.read();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Resturant r : resturants) {
            ResturantList.add(r.getName());
        }
        showResturantNameBox();
    }

    public void setsocketWrapper(socketWrapper SocketWrapper) {
        this.SocketWrapper = SocketWrapper;
        try {
            getResturantName();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showResturantNameBox() {
        ResturantNameBox.getItems().addAll(ResturantList);
    }


    public void searchFoodclick(ActionEvent event) {
        FoodList.getItems().clear();
        Object o = null;
        String by = searchFood.getValue();
        String foodname = searchField.getText();

        if (by.equals("BY CATEGORY")) {
            searchField.clear();
            FoodList.getItems().clear();
            searchField.setPromptText("Enter Food Category");
            Resturantname = ResturantNameBox.getValue();
            if (!foodname.equals("")) {
                try {
                    SocketWrapper.write(new SearchFood(foodname, Resturantname, "category"));
                } catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    o = SocketWrapper.read();
                    specifyClient sp = (specifyClient) o;
                    if (sp.getMessage().equals("found")) {
                        List<Food> foods = (List<Food>) sp.getData();
                        foodNames.clear();
                        for (Food food : foods) {
                            System.out.println(food.getName());
                            foodNames.add(food.getName());
                        }
                        FoodList.getItems().addAll(foodNames);
                    } else {
                        System.out.println("Food Not Found");
                    }
                } catch (Exception e) {
                    System.out.println("in search Reading bug: " + e.getMessage());
                }
            }
        } else if (by.equals("BY NAME")) {
            searchField.clear();
            searchField.setPromptText("Enter the name of the Food");
            FoodList.getItems().clear();
            Resturantname = ResturantNameBox.getValue();
            if (!foodname.equals("")) {
                try {
                    SocketWrapper.write(new SearchFood(foodname, Resturantname, "SearchInResturant"));
                } catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    o = SocketWrapper.read();
                    specifyClient sp = (specifyClient) o;
                    if (sp.getMessage().equals("found")) {
                        List<Food> foods = (List<Food>) sp.getData();
                        foodNames.clear();
                        for (Food food : foods) {
                            System.out.println(food.getName());
                            foodNames.add(food.getName());
                        }
                    } else {
                        System.out.println("Food Not Found");
                    }
                    FoodList.getItems().addAll(foodNames);
                } catch (Exception e) {
                    System.out.println("in search Reading bug: " + e.getMessage());
                }
            }
        }
    }

    public void PlaceOrderClick(ActionEvent event) {
            FoodList.getItems().clear();

        Order order = new Order(Resturantname, customer.getUsername());
        for (String food : OrderList) {
            if (order.FoodMap.containsKey(food)) {
                order.FoodMap.put(food, order.FoodMap.get(food) + 1);
            } else {
                order.FoodMap.put(food, 1);
            }
        }

        for (String food : order.FoodMap.keySet()) {
            System.out.println(food + " " + order.FoodMap.get(food));
        }

        try {
            SocketWrapper.write(order);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Orders.getItems().clear();
        OrderList.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setContentText("Order placed Successfully");
        alert.show();
    }

    public void OnSelectClick(MouseEvent mouseEvent) {
        System.out.println(FoodList.getSelectionModel().getSelectedItem());
        if (FoodList.getSelectionModel().getSelectedItem() != null) {
            Orders.getItems().addAll(FoodList.getSelectionModel().getSelectedItem());
            OrderList.add(FoodList.getSelectionModel().getSelectedItem());
        }
    }


    public void SearchFoodClickonFood(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchFood.fxml"));
        Parent root = loader.load();
        SearchOnFood searchOnFood = loader.getController();
        searchOnFood.setSocketWrapper(SocketWrapper);
        searchOnFood.setCustomer(customer);
        searchOnFood.setCustomerClient(customerClient);
        stage.setTitle("Customer Home Page");
        stage.setScene(new Scene(root));
        stage.show();
    }
}

