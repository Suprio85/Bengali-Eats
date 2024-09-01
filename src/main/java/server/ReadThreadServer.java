package server;

import Models.*;
import util.*;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {

    Server server;

    socketWrapper SocketWrapper;

    Thread t;

    Socket clientSocket;

    boolean isThreadON;

    List<Food> foods;
    List<Resturant> resturants;

    List<Admin> admins;
    List<Customer> customers;
    HashMap<String, String> ResturantpasswordList;
    HashMap<String, String> AdminpasswordList;
    HashMap<String, String> CustomerpasswordList;
    ResturantManager resturantManager;

    HashMap<String, socketWrapper> ClientResturantMap;


    public ReadThreadServer(Socket clientSocket, Server server, socketWrapper SocketWrapper) throws Exception {
        this.clientSocket = clientSocket;
        this.server = server;
        this.foods = server.getFoods();
        this.resturants = server.getResturants();
        this.admins = server.getAdmins();
        this.customers = server.getCustomers();
        this.ResturantpasswordList = server.getResturantpasswordList();
        this.AdminpasswordList = server.getAdminpasswordList();
        this.CustomerpasswordList = server.getCustomerpasswordList();
        this.resturantManager = server.getResturantManager();
        this.SocketWrapper = SocketWrapper;
        this.ClientResturantMap = server.getClientResturantMap();
        this.isThreadON = true;
        t = new Thread(this);
        t.start();
    }


    public void run() {
        while (true) {
            Object o;
            while (true) {
                try {
                    o = SocketWrapper.read();
                    System.out.println("SocketWrapper read");
                    break;
                } catch (Exception e) {
                    System.out.println(e+" in readThreadServer");
                }
            }

            if (o instanceof Integer) {
                System.out.println("read");
                Resturant r = resturantManager.SearchbyId((int) o);
                try {
                    SocketWrapper.write(r);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (o instanceof LoginOb) {
                LoginOb loginOb = (LoginOb) o;
                String username = loginOb.getUsername();
                String password = loginOb.getPassword();
                String type = loginOb.getType();
                specifyClient sc = null;
                try {
                    if (type.equals("Resturant")) {
                        sc = resturantManager.SearchResturant(username, password);
                        if (sc.getData() != null) {
                            Resturant r = (Resturant) sc.getData();
                            ClientResturantMap.put(r.getName(), SocketWrapper);
                            for (String key : ClientResturantMap.keySet()) {
                                System.out.println("Key: " + key +" socekt "+ClientResturantMap.get(key).getSocket());

                            }
                        }
                    } else if (type.equals("Customer")) {
                        System.out.println("in customer");
                        System.out.println("in customer");
                        sc = resturantManager.SearchUser(username, password);
                    }
                } catch (Exception e) {
                    System.out.println("in login part");
                    throw new NullPointerException();
                }
                try {
                    SocketWrapper.write(sc);
                } catch (IOException e) {
                    System.out.println("in writing part");
                    throw new RuntimeException(e);
                }
            }

            if (o instanceof ShowFood) {
                ShowFood showFood = (ShowFood) o;
                Resturant r = showFood.getResturant();
                try {
                    List<Food> foods = r.getMenu();
                    SocketWrapper.write(foods);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            if (o instanceof SearchFood) {
                SearchFood searchFood = (SearchFood) o;


                if (searchFood.getPram().equals("name")) {
                    String foodname = searchFood.getName();
                    List<Food> foods = resturantManager.SearchFood(foodname);
                    try {
                        if (!foods.isEmpty()) {
                            SocketWrapper.write(new specifyClient("found", foods));
                        } else
                            SocketWrapper.write(new specifyClient("not found", null));
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } else if (searchFood.getPram().equals("category")) {
                    String category = searchFood.getName();
                    String resturantname = searchFood.getResturantName();
                    List<Food> foods = resturantManager.SearchFoodByCatagoryInRes(category, resturantname);

                    try {
                        if(!foods.isEmpty()) {
                        SocketWrapper.write(new specifyClient("found", foods));
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                } else if (searchFood.getPram().equals("SearchInResturant")) {
                    String foodname = searchFood.getName();
                    String resturantname = searchFood.getResturantName();
                    List<Food> foods = resturantManager.SearchFoodByResturant(foodname, resturantname);
                    try {
                        if(!foods.isEmpty()) {
                        SocketWrapper.write(new specifyClient("found", foods));
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                else if(searchFood.getPram().equals("categoryfood")){
                    String category = searchFood.getName();
                    List<Food> foods = resturantManager.SearchFoodByCatagory(category);
                    try {
                        if(!foods.isEmpty()) {
                            SocketWrapper.write(new specifyClient("found", foods));
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
            if (o instanceof Order) {
                Order order = (Order) o;
                System.out.println("Orders to "+order.getName());
                socketWrapper socketWrapper = ClientResturantMap.get(order.getName());
                try {
                    if (socketWrapper != null) {
                        System.out.println("SENT");
                        socketWrapper.write(new specifyClient("found", order));
                    } else {
                        System.out.println("SocketWrapper is null");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(o instanceof AddFoodOb){
                AddFoodOb addFoodOb = (AddFoodOb)o;
                int id = addFoodOb.getId();
                String foodName = addFoodOb.getFoodName();
                String Categories = addFoodOb.getCategory();
                double price = addFoodOb.getPrice();
               Food food =new Food(id,Categories,foodName,price);
              boolean isdone= resturantManager.addFood(food);

              try{
                  SocketWrapper.write(isdone);
              }catch(IOException e){
                  System.out.println(e.getMessage()+" in add food");
              }
              if(isdone){
                  this.server.incrementUpdateCount();
              }
            }

            if(o instanceof GetResturant){
                System.out.println("in get resturant");
                List<Resturant> resturants = (List<Resturant>) resturantManager.getResturants();
                try{
                    SocketWrapper.write(resturants);
                }catch(IOException e){
                    System.out.println(e.getMessage()+" in get resturant");
                }
            }
        }
    }
}







