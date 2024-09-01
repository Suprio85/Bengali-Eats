package util;

import java.io.Serializable;

public class AddFoodOb implements Serializable {
    private int id;
    private String foodName;
    private String Category;

    private double Price;

    public AddFoodOb(int id,String foodName, String Category, double Price){
        this.id = id;
        this.foodName = foodName;
        this.Category = Category;
        this.Price = Price;
    }

    public String getFoodName(){
        return foodName;
    }

    public String getCategory(){
        return Category;
    }

    public double getPrice(){
        return Price;
    }

    public int getId() {
        return id;
    }

    public void setFoodName(String foodName){
        this.foodName = foodName;
    }
}
