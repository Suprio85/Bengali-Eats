package util;

import Models.Resturant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOperationOnResturant {
    private static final String INPUT_FILE_NAME = "restaurant.txt";
    private static final String OUTPUT_FILE_NAME = "restaurant.txt";

    public static List<Resturant> readFromFile() throws Exception {
        List<Resturant> resturants = new ArrayList<Resturant>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
         //  System.out.println(line);
            String[] array = line.split(",", -1);
            // for (int i = 0; i < array.length; i++) {
            // System.out.println(array[i]);
            // }
            int id = Integer.parseInt(array[0]);
            String name = array[1];
            double score = Double.parseDouble(array[2]);
            String price = array[3];
            String zip = array[4];
            List<String> cata = new ArrayList<String>();
            for (int j = 5; j < 8; j++) {
               // System.out.println(array[j]);
                if (!array[j].equals("")) {
                    cata.add(array[j]);
                   // System.out.println(" Entering : " + array[j]);

                }
            }
            String password = array[8];
            Resturant r = new Resturant(id, name, score, price, zip, cata, password);
            resturants.add(r);
        }
        br.close();
        return resturants;
    }
    

    public static void WriteIntoFile(List<Resturant> resturants) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));

        for(Resturant r : resturants) {
            if(r.getCatagories().size()==1){
                r.getCatagories().add("");
                r.getCatagories().add("");                
            }
            else if(r.getCatagories().size()==2){
                r.getCatagories().add("");
            }
            String Catagories = String.join(",", r.getCatagories());
            String Password = r.getPassword();
            bw.write(Integer.toString(r.getId())+","+r.getName()+","+Double.toString(r.getScore())+","+r.getPrice()+","+r.getZipCode()+","+Catagories+","+Password);
            bw.newLine();
        }
        bw.close();
    }
}