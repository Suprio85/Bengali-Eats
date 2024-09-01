package util;

import java.util.HashMap;

public class Order implements java.io.Serializable{
    private String name;
    private String food;

    private String from;

    public HashMap<String,Integer> FoodMap=new HashMap<>();

    private boolean isaccepted;

   public Order(String name){
       this.name=name;
   }

   public Order(String name, String from){
       this.name=name;
       this.from=from;
   }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setFood(String food){
        this.food = food;
    }

    public String getFood(){
        return this.food;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom(){
       return this.from;
    }

    public boolean getIsAccepted(){
        return this.isaccepted;
    }

    public void print(){
        for(String food:FoodMap.keySet()){
            System.out.println(food+" "+FoodMap.get(food));
        }

    }



}
