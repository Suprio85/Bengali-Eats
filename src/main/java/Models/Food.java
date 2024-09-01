package Models;

import java.io.Serializable;

public class Food implements Serializable {
    private int Resturant_Id;
    private String Catagory;
    private String Name;
    private double Price; 
    

    public Food(){
        Resturant_Id = -1;
    }

    public Food(int Resturant_Id, String Catagory, String Name, double Price){
        this.Resturant_Id = Resturant_Id;
        this.Name = Name;
        this.Catagory = Catagory;
        this.Price = Price;
    }

    public void setResturant_Id(int Resturant_Id){
        this.Resturant_Id = Resturant_Id;
    }
    public void setName(String Name){
        this.Name = Name;
    }

    public void setCatagory(String Catagory){
        this.Catagory = Catagory;
    }

    public void setPrice(double Price){
        this.Price=Price;
    }

    public int getResturant_Id(){
        return this.Resturant_Id;
    }

    public String getName(){
        return this.Name;
    }

    public double getPrice(){
        return this.Price;
    }

    public String getCatagory(){
        return this.Catagory;
    }

public boolean isSame(int id, String name, String catagory, double price)
{
    if(this.Resturant_Id==id && this.Name.equalsIgnoreCase(name) && this.Catagory.equalsIgnoreCase(catagory) && this.Price==price){
        return true;
    }

    return false;
}

public void ShowFoodDetails(){
        System.out.println("Resturant Id of Food: "+this.Resturant_Id);
        System.out.println("Food Name:"+this.Name);
        System.out.println("Catagory: "+this.Catagory);
        System.out.println("Price : "+this.Price);
    }
}
