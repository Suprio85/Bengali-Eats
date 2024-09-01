package Client.myapp;

import Models.Resturant;
import util.Order;
import util.socketWrapper;
import util.specifyClient;

public class ReadResturant implements Runnable{

    socketWrapper SocketWrapper;

    Thread t;

    Resturant r;

    ResturantHomeController resturantHomeController;

    ReadResturant(socketWrapper SocketWrapper, Resturant r, ResturantHomeController resturantHomeController) {
        this.SocketWrapper = SocketWrapper;
        this.r = r;
        this.resturantHomeController=resturantHomeController;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        Object o=null;

        while(true){
            try {
               // System.out.println("In the block of Read Resturant");
                 o = SocketWrapper.read();
            }  catch (Exception e) {
                System.out.println(e+"Exception in Read Resturant reading");
            }

            if(o instanceof specifyClient){
                specifyClient sc = (specifyClient) o;
                if(sc.getData()!=null){
                    Order order = (Order) sc.getData();
                    order.print();
                    resturantHomeController.setCustomerName(order.getFrom());
                    resturantHomeController.setOrders(order);
                    resturantHomeController.showinScreen(order);
                }
            }

            if(o instanceof Boolean){
                Boolean b = (Boolean) o;
                if(b){
                    System.out.println("Food is Added!");
                    resturantHomeController.getAddFoodController().setisadded(true);
                }
                else{
                    System.out.println("Food is not Added!");
                    resturantHomeController.getAddFoodController().setisadded(false);
                }

            }
        }
    }
}