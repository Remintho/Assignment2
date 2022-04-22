

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class LoginInfo {

    Customer customer;
    private String userName;
    private String password;
    private int ID;
    public LoginInfo(){};
 
    
     /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    
     /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    
    public LoginInfo(String password, int ID){
        this.password = password;
        this.ID = ID;
    }
  
  
    public LoginInfo(String password){
        this.password = password;
    }
   
   
     /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }
    
       
    // toString 
    @Override
    public String toString() {
        String output = getPassword()+" "+getID();
        return output;
    }
  
  //end of the class   
}
