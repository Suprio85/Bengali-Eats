package Models;

import util.FileOperationOfFood;
import util.FileOperationOnCustomer;
import util.FileOperationOnResturant;
import util.specifyClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResturantManager implements Serializable {

    private List<Food> foodList;
    private List<Resturant> Resturants;

    private List<Customer> customers;

    public ResturantManager() throws Exception {
        foodList = FileOperationOfFood.readFromFile();
        Resturants = FileOperationOnResturant.readFromFile();
        customers = FileOperationOnCustomer.readFromFile();

    }

    public ResturantManager(List<Food> foodList, List<Resturant> resturants, List<Customer> customers) {
        this.foodList = foodList;
        Resturants = resturants;
        this.customers = customers;
    }

    public List<Food> getfoodList() {
        return foodList;
    }

    public List<Resturant> getResturants() {
        return Resturants;
    }

    public synchronized boolean addResturant(Resturant r) {

        for (int i = 0; i < Resturants.size(); i++) {
            if (r.getName().equalsIgnoreCase(Resturants.get(i).getName()) || r.getId() == Resturants.get(i).getId()) {
                return false;
            }
        }
        Resturants.add(r);
        return true;
    }

    // public boolean subStringBelongs(String main,String sub){
    //// return main.toLowerCase().contains(sub.toLowerCase());
    ////
    // }
    public boolean subStringBelongs(String main, String sub) {
        sub = sub.toLowerCase();
        main = main.toLowerCase();

        if (main.contains(sub)) {
            int i = main.indexOf(sub);
            if (i == 0) {
                return true;
            } else if (i > 0) {
                char characterBeforeSub = main.charAt(i - 1);
                return characterBeforeSub == ' ';
            }
        }

        return false;
    }

    public synchronized boolean addFood(Food f) {
        String cat = f.getCatagory();
        String nam = f.getName();
        int id = f.getResturant_Id();
        double price = f.getPrice();

        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).isSame(id, nam, cat, price)) {
                return false;
            }
        }
        // System.out.println("Invoked");
        for (int i = 0; i < Resturants.size(); i++) {
            if (f.getResturant_Id() == Resturants.get(i).getId()) {
                Resturants.get(i).getMenu().add(f);
                foodList.add(f);
                return true;
                // System.out.println("Invoked");
            }
        }
        return false;
    }

    public Resturant SearchByName(String name) {
        Resturant Rname = null;
        for (int i = 0; i < Resturants.size(); i++) {
            String resturant = Resturants.get(i).getName();

            if (subStringBelongs(resturant, name)) {
                Rname = Resturants.get(i);
                break;
            }
        }
        return Rname;
    }

    public Resturant SearchByNameForAdd(String name) {
        Resturant Rname = null;
        for (int i = 0; i < Resturants.size(); i++) {
            String resturant = Resturants.get(i).getName();
            if (resturant.equalsIgnoreCase(name)) {
                Rname = Resturants.get(i);
                break;
            }
        }
        return Rname;
    }

    public List<Resturant> SearchByScore(Double range1, Double range2) {
        List<Resturant> Rlist = new ArrayList<Resturant>();
        for (int i = 0; i < Resturants.size(); i++) {
            double score = Resturants.get(i).getScore();
            if (score >= range1 && score <= range2) {
                Rlist.add(Resturants.get(i));
            }
        }
        // for (var r : Rlist) {
        // System.out.println(r);
        // }
        return Rlist;
    }

    public List<Resturant> SearchByCatagory(String cat) {
        List<Resturant> result = new ArrayList<Resturant>();
        for (int i = 0; i < Resturants.size(); i++) {
            for (var catagory : Resturants.get(i).getCatagories()) {
                if (subStringBelongs(catagory, cat)) {
                    result.add(Resturants.get(i));
                }
            }
        }
        // for (var r : result) {
        // System.out.println(r);
        // }
        return result;
    }

    public List<Resturant> SearchByPrice(String price) {
        List<Resturant> result = new ArrayList<Resturant>();
        for (int i = 0; i < Resturants.size(); i++) {
            if (Resturants.get(i).getPrice().equalsIgnoreCase(price)) {
                result.add(Resturants.get(i));
            }
        }
        return result;
    }

    public List<Resturant> SearchByZipCode(String zip) {
        List<Resturant> result = new ArrayList<Resturant>();
        for (int i = 0; i < Resturants.size(); i++) {
            if (Resturants.get(i).getZipCode().equals(zip)) {
                result.add(Resturants.get(i));
            }
        }

        // for (var r : result) {
        // System.out.println(r);
        // }

        return result;

    }

    public List<Food> SearchFood(String foodName) {
        List<Food> result = new ArrayList<Food>();
        for (int i = 0; i < foodList.size(); i++) {
            String food = new String(foodList.get(i).getName());
            // System.out.println(food);
            // System.out.println(foodName.toUpperCase());
            if (subStringBelongs(food, foodName)) {
                result.add(foodList.get(i));
            }
        }

        return result;
    }

    public List<Food> SearchFoodByResturant(String foodName, String resturantName) {
        List<Food> result = new ArrayList<Food>();
        for (int i = 0; i < Resturants.size(); i++) {
            if (Resturants.get(i).getName().equalsIgnoreCase(resturantName)) {
                for (Food food : Resturants.get(i).getMenu()) {
                    String f = new String(food.getName());
                    if (subStringBelongs(f, foodName)) {
                        result.add(food);
                    }
                }
                break;
            }
        }
        // for (int i = 0; i < result.size(); i++) {
        // result.get(i).ShowFoodDetails();
        // }
        return result;
    }

    public List<Food> SearchFoodByCatagory(String catagory) {
        List<Food> result = new ArrayList<Food>();

        for (int i = 0; i < foodList.size(); i++) {
            String name = foodList.get(i).getCatagory();
            if (subStringBelongs(name, catagory)) {
                result.add(foodList.get(i));
            }
        }

        // for (Food r : result) {
        // r.ShowFoodDetails();
        // }
        return result;
    }

    public List<Food> SearchFoodByCatagoryInRes(String catagory, String resturant) {
        List<Food> result = new ArrayList<Food>();

        for (int i = 0; i < Resturants.size(); i++) {
            if (Resturants.get(i).getName().equalsIgnoreCase(resturant)) {
                for (int j = 0; j < Resturants.get(i).getMenu().size(); j++) {
                    String cat = Resturants.get(i).getMenu().get(j).getCatagory();
                    if (subStringBelongs(cat, catagory)) {
                        result.add(Resturants.get(i).getMenu().get(j));
                    }
                }
                break;
            }

        }
        // for (Food r : result) {
        // r.ShowFoodDetails();
        // }
        return result;

    }

    public List<Food> SearchFoodByPrice(double r1, double r2) {
        List<Food> result = new ArrayList<Food>();

        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getPrice() >= r1 && foodList.get(i).getPrice() <= r2) {
                result.add(foodList.get(i));
            }

        }

        return result;
    }

    public List<Food> SearchFoodByPriceInRes(double r1, double r2, String resturant) {
        List<Food> result = new ArrayList<Food>();

        for (int i = 0; i < Resturants.size(); i++) {

            if (Resturants.get(i).getName().equalsIgnoreCase(resturant)) {
                for (Food f : Resturants.get(i).getMenu()) {

                    if (f.getPrice() >= r1 && f.getPrice() <= r2) {
                        result.add(f);
                    }
                }

                break;
            }

        }
        return result;
    }

    public List<Food> getCostliestFood(String resturant) {
        List<Food> result = new ArrayList<Food>();

        for (int i = 0; i < Resturants.size(); i++) {
            if (resturant.equalsIgnoreCase(Resturants.get(i).getName())) {
                double max = 0.0;
                for (Food f : Resturants.get(i).getMenu()) {
                    if (f.getPrice() > max) {
                        max = f.getPrice();
                    }
                }

                for (Food f : Resturants.get(i).getMenu()) {
                    if (f.getPrice() == max) {
                        result.add(f);
                    }

                }
                break;
            }
        }
        // for (int i = 0; i < result.size(); i++) {
        // result.get(i).ShowFoodDetails();
        // }
        return result;
    }



    public List<Data> getTotalFood() {
        List<Data> result = new ArrayList<Data>();
        for (Resturant r : Resturants) {
            result.add(new Data(r.getName(), r.getMenu().size()));
        }

        // for (var x : result) {
        // System.out.println(x.Name + ": " + x.price);

        // }
        return result;
    }

    public Resturant SearchbyId(Integer id){

        for(Resturant r:Resturants){
            if(r.getId()==id){
                return r;
            }
        }
        return null;
    }

    public specifyClient SearchResturant(String user, String password) throws Exception {
        System.out.println("In Search Resturant");
        for(Resturant r : Resturants){
            r.ShowDetails();
            if(r.getName().equals(user) && r.getPassword().equals(password)){
                System.out.println("Found");
                return new specifyClient("Resturant",r);
            }
        }

        return new specifyClient("invalid",null);
    }
    public specifyClient SearchUser(String name, String password) throws Exception{
      try {
          for (Customer customer : customers) {
              if (customer.getUsername().equals(name) && customer.getPassword().equals(password)) {
                  System.out.println("Found");
                  return new specifyClient("Customer", customer);
              }
          }
          return new specifyClient("invalid", null);
      } catch (Exception e) {
          throw new Exception("Error in SearchUser");
      }
    }

    public List<String> generateCatagory() {
        List<String> result = new ArrayList<>();

        for (Resturant r : Resturants) {
            for (String s : r.getCatagories()) {
                boolean categoryExists = false;
                for (String existingCategory : result) {
                    if (existingCategory.equalsIgnoreCase(s)) {
                        categoryExists = true;
                        break;
                    }
                }
                if (!categoryExists) {
                    result.add(s);
                }
            }
        }
        // for (String category : result) {
        // System.out.println(category);
        // }

        return result;
    }

    public List<CatagoryData> getCatagoryWiseResturant() {
        List<CatagoryData> result = new ArrayList<CatagoryData>();
        List<String> categoryNames = generateCatagory();

        for (String category : categoryNames) {
            List<String> name = new ArrayList<>();
            for (Resturant r : Resturants) {
                for (String s : r.getCatagories()) {
                    if (s.equalsIgnoreCase(category)) {
                        name.add(r.getName());
                    }
                }
            }
            result.add(new CatagoryData(category, name));
        }

        // for (int i = 0; i < result.size(); i++) {
        // System.out.println(result.get(i).catagory + " :" + String.join(",",
        // result.get(i).names));
        // }

        return result;
    }

    // public static void main(String[] args) throws Exception {
    // ResturantManager manager = new ResturantManager();
    // // // manager.SearchByName("KFC");
    // // // manager.SearchByScore(1.0 * 4, 4.8 * 1.0);
    // manager.getCatagoryWiseResturant();
    // // // Food f =new Food(1,"A la Carte","A La Carte Drum",100.0);
    // // // Food f3 =new Food(1,"A la Carte","A La Carte BUM", 100);
    // // // Food f4 =new Food(4,"A la Carte","Sandwich", 150);
    // // // manager.addFood(f);
    // // // manager.addFood(f3);
    // // // manager.addFood(f4);
    // // // manager.SearchFoodByResturant("A la Carte Drum","KFC");
    // // // Food f2 =new Food(5, "chicken", "sandwich", 10.0);
    // // // manager.addFood(f2);
    // // // List<String> l=new ArrayList<String>();
    // // // l.add("Burgers");
    // // // l.add("Food");
    // // // l.add("Fast");
    // // // Resturant r = new Resturant(5,"McDonlds",4.7,"$","98346",l);
    // // // manager.addResturant(r);
    // // // manager.SearchFoodByResturant("sandwich","McDonlds");
    // // // manager.getCostliestFood("Kfc");
    // // // manager.getCostliestFood("McDonalds");
    // // // manager.getCatagoryWiseResturant();
    // // // manager.SearchByPrice(6.1);
    // // // manager.SearchFoodByCatagoryInRes("A la Carte", "Mcdonalds");
    // // // manager.getTotalFood();
    // // // ma
    // // manager.SearchFoodByCatagory("BAkery");
    // // manager.SearchByCatagory("Fast Food");
    // // manager.SearchByZipCode("98531");
    // }
}

class Data {

    public String Name;
    public int price;

    public Data(String Name, int price) {
        this.Name = Name;
        this.price = price;
    }

    public Data() {

    }
}

class CatagoryData {
    public String catagory;
    public List<String> names;

    public CatagoryData() {

    }

    public CatagoryData(String catagory, List<String> names) {
        this.names = names;
        this.catagory = catagory;
    }
}
