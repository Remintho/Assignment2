/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class Registration {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    
    public Registration(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    
    public int getLastID(){
        ResultSet rs;
       
        int lastID = 0;
        try {
            rs = this.statement.executeQuery("SELECT MAX(USERID)"
                + "FROM CUSTOMER");
            if(rs.next()){
                lastID = (((Number)rs.getObject(1)).intValue()) + 1;
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lastID; 
    }
    public void adduser(String fName, String lName, String age, String phNumber, String email){
        int Age;
        try {
            Age = Integer.parseInt(age.trim());
        }
        catch (NumberFormatException e) {
            Age = 0;
        }
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("INSERT INTO CUSTOMER (USERID, FNAME, LNAME, AGE, EMAIL, PHONE)"
                    + "VALUES("+getLastID()+", '"+fName+"', '"+lName+"', "+Age+", '"+email+"', '"+phNumber+"')");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String checkInfo(String fName, String lName, String age, String phNumber, String email){
        String returnMessage = "";
        int Age;
        try {
            Age = Integer.parseInt(age.trim());
        }
        catch (NumberFormatException e) {
            Age = 0;
        }
        //First Name
        if((fName.matches("^.*[^a-zA-Z ].*$")))
        {
            returnMessage = "invalid firstname";
            return returnMessage;
        }
        //Last Name
        else if((lName.matches("^.*[^a-zA-Z ].*$")))
        {
            returnMessage = "invalid lastname";
            return returnMessage;
        }
        //Age
        
        else if(!(Age>=18))
        {
            returnMessage = "must be 18 or older";
            return returnMessage;
        } 
        //Email
        else if(!(email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")))
        {
            returnMessage = "invalid email, hint: example@gmail.com";
            return returnMessage;
        }
        //Phone Number   
        else if((phNumber.matches("^.*[^0-9 ].*$")))
        {
            returnMessage = "invalid phone number";
            return returnMessage;
        }
        
        return returnMessage;
    }
    
    public boolean check(String fName, String lName, String age, String phNumber, String email){
        String returnMessage = "";
        int Age;
        try {
            Age = Integer.parseInt(age.trim());
        }
        catch (NumberFormatException e) {
            Age = 0;
        }
        //First Name
        if((fName.matches("^.*[^a-zA-Z ].*$")))
        {
            return false;
        }
        //Last Name
        else if((lName.matches("^.*[^a-zA-Z ].*$")))
        {
            return false;
        }
        //Age
        
        else if(!(Age>=18))
        {
            return false;
        } 
        //Email
        else if(!(email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")))
        {
            return false;
        }
        //Phone Number   
        else if((phNumber.matches("^.*[^0-9 ].*$")))
        {
            return false;
        }
        
        return true;
    }
   
}
