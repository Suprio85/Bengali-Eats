package Models;

import util.FileOperationOfFood;
import util.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Resturant implements Serializable{

    private int Id;
    private String Name;
    private double Score;
    private String Price;
    private String Zip_Code;
    private List<String> Catagories;
    private List<Food> Menu;

    private List<Order> orders ;
    private String Password;

    private int newOrderCount = 0;

    public Resturant() {

    }

    public Resturant(int Id, String Name, double Score, String Price, String Zip_Code, List<String> Catagories, String Password)
            throws Exception {
        this.Id = Id;
        this.Name = Name;
        this.Score = Score;
        this.Price = Price;
        this.Zip_Code = Zip_Code;
        this.Catagories = Catagories;
        Menu = new ArrayList<Food>();
        orders = new ArrayList<Order>();
        this.Password = Password;
        storeFoodItem();
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public double getScore() {
        return Score;
    }

    public String getPrice() {
        return Price;
    }

    public List<String> getCatagories() {
        return Catagories;
    }

    public String getZipCode() {
        return Zip_Code;
    }

    public List<Food> getMenu() {
        return Menu;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setCatagory(List<String> Catagories) {
        this.Catagories = Catagories;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public synchronized void storeFoodItem() throws Exception {
      try {
        List<Food> foods = FileOperationOfFood.readFromFile();
        for (int i = 0; i < foods.size(); i++) {
            int ID = foods.get(i).getResturant_Id();
            Food food = foods.get(i);
            if (this.Id == ID) {
                boolean added = false;
                for (int j = 0; j < Menu.size(); j++) {
                    if (Menu.get(j).isSame(food.getResturant_Id(),food.getName(),food.getCatagory(),food.getPrice())) {
                        added = true;
                        break;
                    }
                }
                if (!added) {
                        Menu.add(food);
                }
            }

        }} catch (Exception e) {
            System.out.println("Error in Resturant storeFoodItem");
        }
    }

    public void ShowDetails() {

        System.out.println("Resturant Id : " + this.Id);
        System.out.println("Resturant Name :" + this.Name);
        System.out.println("Score: " + this.Score);
        System.out.println("Zip Code : " + this.Zip_Code);
        System.out.println("Catagories : " + this.Catagories);
    }

    public Food searchFood(String foodName, String foodCategory) {
        for (Food food : Menu) {
            if (food.getName().equalsIgnoreCase(foodName) && food.getCatagory().equalsIgnoreCase(foodCategory)){
                return food;
            }
        }
        return null;
    }

    public List<String> ShowFooditems(){
        List<String> fooditems = new ArrayList<String>();
        for (int i = 0; i < Menu.size(); i++) {
            fooditems.add(Menu.get(i).getName());
        }
        return fooditems;
    }

    public void RefreshOrders() {
        newOrderCount=0;
    }

//    public void addOrder(int customerid, String foodName, String foodCategory, boolean isaccepted) {
//        Food food = searchFood(foodName, foodCategory);
//        if (food != null) {
//            orders.add(new Order(customerid, food, isaccepted));
//        }
//    }

//    synchronized public void addNewOrder(int customerId, String foodName, String foodCategory, Boolean isAccepted) {
//        orders.add(0, new Order(customerId, searchFood(foodName, foodCategory), isAccepted));
//        newOrderCount++;
//    }

}
