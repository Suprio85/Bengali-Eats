//package Client;
//
//import Models.Customer;
//import Models.Resturant;
//import util.socketWrapper;
//import util.specifyClient;
//
//public class ReadClientThread implements Runnable {
//
//    private Thread t;
//    private socketWrapper SocketWrapper;
//
//    public ReadClientThread(socketWrapper SocketWrapper) {
//        this.SocketWrapper = SocketWrapper;
//        t = new Thread(this);
//        t.start();
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            Object o = null;
//            while (true) {
//                try {
//                    o = SocketWrapper.read();
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//                if (o instanceof Resturant) {
//                    ((Resturant) o).ShowDetails();
//
//                }
//
//                if (o instanceof specifyClient) {
//                    var s = (specifyClient) o;
//                    if (s.getMessage().equals("Customer")) {
//                        new CustomerClient(SocketWrapper, (Customer) s.getData());
//
//                    } else if (s.getMessage().equals("Resturant")) {
//                        System.out.println("Restaurant");
//                        new ResturantClient(SocketWrapper, (Resturant) s.getData());
//                    } else {
//                        System.out.println("Invalid");
//                    }
//
//                }
//
//            }
//        }
//
//    }
//}

