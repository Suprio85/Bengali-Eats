package util;

import Models.Food;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOperationOfFood {

    private static final String INPUT_FILE_NAME = "menu.txt";
    private static final String OUTPUT_FILE_NAME = "menu.txt";

    public static List<Food> readFromFile() throws Exception {
        List<Food> foods = new ArrayList<Food>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
           // System.out.println(line);
            String[] array = line.split(",(?! )", -1);
            //   for (int i = 0; i < array.length; i++) {
            //   System.out.println(array[i]);
            //   }
            Food food = new Food();
            food.setResturant_Id(Integer.parseInt(array[0]));
            food.setName(array[2]);
            food.setCatagory(array[1]);
            double price = Double.parseDouble(array[3]);
            food.setPrice(price);
            foods.add(food);
            //System.out.println("Done!");
        }
        br.close();
        return foods;
    }

    public static void WriteIntoFile(List<Food> foods) throws Exception { 
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));

        for(Food food : foods) {
            bw.write(Integer.toString(food.getResturant_Id()) + "," + food.getCatagory() + "," + food.getName() + "," + Double.toString(food.getPrice()));
            bw.newLine();
        }
        bw.close();
    }

}
