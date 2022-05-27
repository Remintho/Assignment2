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
public class AdminPage {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public AdminPage(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    public ResultSet searchBy(int choice, String input){
        ResultSet rs = null;
        try {
            if(input.equalsIgnoreCase("")){
                rs = null;
            }
            else{
                switch (choice) {
                    case 0:
                        {
                            int inputINT = 0;
                            try{
                            inputINT = Integer.parseInt(input);
                            }catch(NumberFormatException ex){}
                            String dataQuery = "SELECT * FROM CUSTOMER WHERE USERID ="+inputINT+"";
                            this.statement = conn.createStatement();
                            rs = this.statement.executeQuery(dataQuery);
                            break;
                        }

                    case 1:
                        {
                            String dataQuery = "SELECT * FROM CUSTOMER WHERE FNAME ='"+input+"'";
                            this.statement = conn.createStatement();
                            rs = this.statement.executeQuery(dataQuery);
                            break;
                        }
                    case 2:
                        {
                            String dataQuery = "SELECT * FROM CUSTOMER WHERE LNAME ='"+input+"'";
                            this.statement = conn.createStatement();
                            rs = this.statement.executeQuery(dataQuery);
                            break;
                        }
                    case 3:
                        {
                            String dataQuery = "SELECT * FROM CUSTOMER WHERE EMAIL ='"+input+"'";
                            this.statement = conn.createStatement();
                            rs = this.statement.executeQuery(dataQuery);
                            break;
                        }
                    case 4:
                        {
                            String dataQuery = "SELECT * FROM CUSTOMER WHERE PHONE ='"+input+"'";
                            this.statement = conn.createStatement();
                            rs = this.statement.executeQuery(dataQuery);
                            break;
                        }                    
                    default:
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs; 
    }
    
    public void dataChange(String userId, String fName, String lName, String age, String email, String phNumber){
        int Age;
        int userID;
        try {
            Age = Integer.parseInt(age.trim());
            userID = Integer.parseInt(userId.trim());
            
        }
        catch (NumberFormatException e) {
            Age = 0;
            userID = 0;
        }
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("UPDATE CUSTOMER SET FNAME = '"+fName+"', LNAME = '"+lName+"', AGE = "+Age+", EMAIL = '"+email+"', PHONE = '"+phNumber+"' WHERE USERID = "+userID+"");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void deleteRecord(String userId){
         int userID;
        try {
            userID = Integer.parseInt(userId.trim());  
        }
        catch (NumberFormatException e) {
            userID = 0;
        }
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("DELETE FROM GUEST WHERE USERID = "+userID+"");
            this.statement.addBatch("DELETE FROM RESERVATION WHERE USERID = "+userID+"");
            this.statement.addBatch("DELETE FROM CUSTOMER WHERE USERID = "+userID+"");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
