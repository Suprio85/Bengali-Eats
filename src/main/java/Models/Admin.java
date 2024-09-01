package Models;

public class Admin {
    private String Name;
    private String Password;

    public Admin(String Name, String Password){
        this.Name = Name;
        this.Password = Password;
    }

    public Admin(){
    }
    public String getName(){
        return this.Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getPassword(){
        return this.Password;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

}
