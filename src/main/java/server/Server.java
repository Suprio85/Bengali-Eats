package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import Models.Admin;
import Models.Customer;
import Models.Food;
import Models.Resturant;
import Models.ResturantManager;
import util.FileOperationOfFood;
import util.FileOperationOnCustomer;
import util.FileOperationOnResturant;
import util.socketWrapper;


public class Server {
    List<Food> foods;
    List<Resturant> resturants;
    List<Admin> admins;
    List<Customer> customers;
    HashMap<String, String> ResturantpasswordList;
    HashMap<String, String> AdminpasswordList;
    HashMap<String, String> CustomerpasswordList;
    ResturantManager resturauntManager;

    HashMap<String, socketWrapper> ClientResturantMap;

    ServerSocket serverSocket;
    socketWrapper SocketWrapper;
    public volatile int clientcount;
    public volatile int updatecount;


    public HashMap<String, String> getResturantpasswordList() {
        return ResturantpasswordList;
    }

    public List<Resturant> getResturants() {
        return resturants;
    }

    public void setResturants(List<Resturant> resturants) {
        this.resturants = resturants;
    }

    public void setResturantpasswordList(HashMap<String, String> ResturantpasswordList) {
        this.ResturantpasswordList = ResturantpasswordList;
    }

    public void setAdminpasswordList(HashMap<String, String> AdminpasswordList) {
        this.AdminpasswordList = AdminpasswordList;
    }

    public void setCustomerpasswordList(HashMap<String, String> CustomerpasswordList) {
        this.CustomerpasswordList = CustomerpasswordList;
    }

    public HashMap<String, String> getAdminpasswordList() {
        return AdminpasswordList;
    }

    public HashMap<String, String> getCustomerpasswordList() {
        return CustomerpasswordList;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public ResturantManager getResturantManager() {
        return resturauntManager;
    }

    public int getUpdateCount() {
        return updatecount;
    }

    public int getClientCount() {
        return clientcount;
    }

    public  void incrementUpdateCount() {
        updatecount++;
    }

    public synchronized void decrementUpdateCount() {
        updatecount--;
    }

    public synchronized void incrementClientCount() {
        clientcount++;
    }

    public synchronized void decrementClientCount() {
        clientcount--;
    }

    public HashMap<String, socketWrapper> getClientResturantMap() {
        return ClientResturantMap;
    }


    Server() {
        try {
            updatecount =0;
            clientcount = 0;
            //admins = FileOperationOnAdmin.readFromFile();
            // customers = FileOperationOnCustomer.readFromFile();
            foods = FileOperationOfFood.readFromFile();
            resturants = FileOperationOnResturant.readFromFile();
            customers = FileOperationOnCustomer.readFromFile();
            resturauntManager = new ResturantManager(foods, resturants, customers);

            HashMap<String, String> ResturantpasswordList = new HashMap<String, String>();
            ClientResturantMap = new HashMap<String, socketWrapper>();
            for (Resturant resturant : resturants) {
                ResturantpasswordList.put(resturant.getName(), resturant.getPassword());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        new FileWriteThread(this);
        new addResturantThread(this);
        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("Server is waiting...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server accepts  client...");
                SocketWrapper = new socketWrapper(clientSocket);
                new ReadThreadServer(clientSocket, this, SocketWrapper);
            }

        } catch (Exception e) {
            //System.out.println("Server failed to start" + e);
        }
    }



    public static void main(String[] args) {
        Server s = new Server();

    }

}
