package util;
import java.io.Serializable;
    public class SearchFood implements Serializable {
        public String str;
        public double minScore;
        public double maxScore;
        public String pram;

        public String Resturant;

        public SearchFood(String str, String pram) {
            this.str = str;
            this.pram = pram;
        }
        public String getName() {
            return str;
        }

        public String getResturantName() {
            return Resturant;
        }

        public SearchFood(String str, String Resturant , String pram) {
            this.str = str;
            this.pram = pram;
            this.Resturant = Resturant;
        }
        public String getPram() {
            return pram;
        }


    }
