package Models;

import java.util.List;

public class Customer implements java.io.Serializable{
    int id;
    String username;
    String password;

    List<Food> orders;

    public Customer(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Customer(){

    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
