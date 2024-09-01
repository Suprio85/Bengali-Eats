package Client.myapp;

import Models.Customer;
import Models.Food;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.SearchFood;
import util.socketWrapper;
import util.specifyClient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchOnFood implements Initializable {

    public TextField Searchbar;
    public ChoiceBox Criteria;

    public String[] criteria = {"By Name","By Category"};
    public ListView Table;

    private Customer customer;

    private List<String> foodNames = new ArrayList<>();

    private socketWrapper SocketWrapper;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        Searchbar.setPromptText("Enter Food Name");
    }

    public void setSocketWrapper(socketWrapper SocketWrapper) {
        this.SocketWrapper = SocketWrapper;
    }

    private CustomerClient customerClient;

    public void setCustomerClient(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public CustomerClient getCustomerClient() {
        return customerClient;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Criteria.getItems().addAll(criteria);
    }



    public void SearchClick(ActionEvent event) {
        System.out.println("Search Clicked");
        Table.getItems().clear();
        String search = Searchbar.getText();
        if(Criteria.getValue().equals("By Name") && !search.equals("")) {
            SearchFood searchFood = new SearchFood(search, "name");
            try {
                SocketWrapper.write(searchFood);
            } catch (Exception e) {
                e.printStackTrace();
            }

            specifyClient sc1;
            try {
                sc1 = (specifyClient) SocketWrapper.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (sc1.getMessage().equals("found")) {
                List<Food> foods = (List<Food>) sc1.getData();
                foodNames.clear();
                for (Food food : foods) {
                    System.out.println(food.getName());
                    foodNames.add("Name: "+food.getName()+"   Price:  "+food.getPrice()+"   Catagory:  "+food.getCatagory());
                }
            } else {
                System.out.println("Food Not Found");
            }
            Table.getItems().addAll(foodNames);

        }
        else if(Criteria.getValue().equals("By Category")){
            Table.getItems().clear();
            String category = Searchbar.getText();
            System.out.println("Category: " + category);
            SearchFood searchFood = new SearchFood(category, "categoryfood");
            try {
                SocketWrapper.write(searchFood);
            } catch (Exception e) {
                e.printStackTrace();
            }
            specifyClient sc1;
            try {
                sc1 = (specifyClient) SocketWrapper.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (sc1.getMessage().equals("found")) {
                List<Food> foods = (List<Food>) sc1.getData();
                foodNames.clear();
                for (Food food : foods) {
                    System.out.println(food.getName());
                    foodNames.add("Name: "+food.getName()+"   Price:  "+food.getPrice()+"   Catagory:  "+food.getCatagory());
                }
            } else {
                System.out.println("Food Not Found");
            }
            Table.getItems().addAll(foodNames);
        }
    }


    public void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
          new CustomerClient(this.customerClient);
    }
}
