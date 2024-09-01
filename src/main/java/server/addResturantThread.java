package server;

import Models.Resturant;
import Models.ResturantManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class addResturantThread implements Runnable {

    Thread t;
    ResturantManager resturantManager;

    Server server;

    addResturantThread( Server server){
        this.server=server;
        this.resturantManager = server.getResturantManager();
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try {
            addResturant();
        } catch (Exception e) {
            System.out.println("Error in addResturantThread + " +e.getMessage());
        }

    }

    public void addResturant() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Resturant");
            System.out.println("Enter :");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                int id = resturantManager.getResturants().size() + 1;
                System.out.println("Enter Restaurant name: ");
                String name = sc.nextLine();
                Resturant res = resturantManager.SearchByNameForAdd(name);
                if (res != null) {
                    System.out.println("restaurant Already exists");
                    break;
                }
                double score;
                while (true) {
                    System.out.println("Enter Restaurant score(1-5): ");
                    score = sc.nextDouble();
                    if (score <= 5 || score >= 1) {
                        break;
                    } else {
                        System.out.println("Score should be between 1 to 5");
                    }
                }
                sc.nextLine();
                System.out.println("Enter Restaurant price($, $$ or $$$): ");
                String price = sc.nextLine();
                while (true) {
                    if (price.equals("$") || price.equals("$$") || price.equals("$$$")) {
                        break;
                    }
                    System.out.println("Enter Restaurant price($, $$ or $$$): ");
                    price = sc.nextLine();
                }

                System.out.println("Enter Restaurant Zipcode: ");
                String zipcode = sc.nextLine();
                List<String> catagories = new ArrayList<String>();
                String cat;
                while (true) {
                    System.out.println("Enter catagory 1: ");
                    cat = sc.nextLine();
                    if (cat.equals("")) {
                        System.out.println("You must enter at least one catagory");
                    } else {
                        catagories.add(cat);
                        break;
                    }
                }
                for (int i = 1; i < 3; i++) {
                    System.out.println("Eneter Catagory " + (i + 1) + ":(Press enter to Skip)");
                    cat = sc.nextLine();
                    if (!cat.equals("")) {
                        catagories.add(cat);
                    }
                }
                System.out.println("Password :");
                String Password = sc.nextLine();

                Resturant restaurant = new Resturant(id, name, score, price, zipcode, catagories, Password);

                boolean added = resturantManager.addResturant(restaurant);
                if (added) {
                    System.out.println("Added restaurant to the Database");
                   // FileOperationOnResturant.WriteIntoFile(resturantManager.getResturants());
                    if(server!=null){
                        server.incrementUpdateCount();
                    }
                    else{
                        System.out.println("Server is null");
                    }

                }

                else {
                    System.out.println("Resturant is Already there");
                }

            }
        }
    }
}
