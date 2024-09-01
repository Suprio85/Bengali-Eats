package server;

import Models.ResturantManager;
import util.FileOperationOfFood;
import util.FileOperationOnResturant;

import java.util.HashMap;

public class FileWriteThread implements Runnable {
    Server server;

    Thread t;
    ResturantManager resturantManager;
    HashMap<String, String> Resturantpasswordlist;
    HashMap<String, String> CustomerpasswordList;



    private int count;

    FileWriteThread(Server server) {
        this.server = server;
        this.resturantManager = server.getResturantManager();
        this.CustomerpasswordList = server.getCustomerpasswordList();
        this.Resturantpasswordlist = server.getResturantpasswordList();
        count = 0;
        t = new Thread(this);
        t.start();
    }

    public void run() {

        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            if (server.getUpdateCount() > count) {
                count = server.getUpdateCount();

                try{
                    FileOperationOfFood.WriteIntoFile(resturantManager.getfoodList());
                    FileOperationOnResturant.WriteIntoFile(resturantManager.getResturants());

                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println("Updated");

            }

            else{
                //System.out.println("No update found");
            }

        }
    }
}


