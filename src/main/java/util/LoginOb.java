package util;

public class LoginOb implements java.io.Serializable{
private String username;
    private String password;
    private boolean status;

    private String type;

    public LoginOb(String username, String password, String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public LoginOb(){

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

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus(){
        return this.status;
    }

    public String getType(){
        return this.type;
    }

}
