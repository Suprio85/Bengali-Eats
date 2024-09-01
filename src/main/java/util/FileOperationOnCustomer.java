package util;

import Models.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileOperationOnCustomer {
    private static final String INPUT_FILE_NAME = "Customer.txt";
    private static final String OUTPUT_FILE_NAME = "Customer.txt";

    public static List<Customer> readFromFile() throws Exception {
        List<Customer> customers = new ArrayList<Customer>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            // System.out.println(line);
            String[] array = line.split(",(?! )", -1);
            for (String s : array) {
                System.out.println(s);
            }
            int id = Integer.parseInt(array[0]);
            Customer customer = new Customer(id,array[1],array[2]);
            customers.add(customer);
        }
        br.close();
        return customers;
    }

}
