package clases.mvc;

public class MvcVar{
    protected int userID;
    protected String username;
    protected String userRole;
    
    public int getUserID(){
        return userID;
    }
    
    public void setUserID(int userID){
        this.userID=userID;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getUserRole(){
        return userRole;
    }
    
    public void setUserRole(String userRole){
        this.userRole=userRole;
    }
}
