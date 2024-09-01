package util;

import Models.Resturant;

public class ShowFood {
    Resturant r;
    public ShowFood(Resturant r){
        this.r = r;
    }

    public Resturant getResturant(){
        return this.r;
    }
}
