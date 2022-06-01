/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author User
 */
public class LoginPage {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    private PreparedStatement prepStatement;

    public LoginPage(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    //get last ID
    public int getLastID(){
        ResultSet rs;
       
        int lastID = 0;
        try {
            String sql = "SELECT MAX(USERID)"
                + "FROM CUSTOMER";
            this.prepStatement = conn.prepareStatement(sql);
            rs = this.prepStatement.executeQuery();
            if(rs.next()){
                lastID = (((Number)rs.getObject(1)).intValue());
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lastID; 
        
        //the end
    }
    
    //return result of username and password info using the userID input
    public ResultSet getLogin(int userID){
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT USERNAME, PASSWORD FROM GUEST WHERE USERID = "+userID+"";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
        
       //the end 
    }
    
    //update login using userID, username and password
    public void updateLogin(int userID, String uN, String pW){
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("UPDATE GUEST SET USERNAME = '"+uN+"', PASSWORD = '"+pW+"'  WHERE USERID = "+userID+"");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        //the end
    }
    
    //add new username and password for new user
    public void addLogin(String uN, String pW){
        try {
            String sql = "INSERT INTO GUEST (USERID, USERNAME, PASSWORD)"
                    + "VALUES((SELECT USERID FROM CUSTOMER WHERE USERID ="+getLastID()+"), '"+uN+"', '"+pW+"')";
            this.statement = conn.createStatement();
            this.statement.addBatch(sql);
        this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
        try {
            this.statement.close();
            this.conn.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        //the end
    }
    
    //check login for a match with the input of username and password
    public boolean checkLogin(String username, String password){
        ResultSet rs;
        try {
            String sql = "SELECT * FROM GUEST WHERE USERNAME ='"+username+"' AND PASSWORD = '"+password+"' ";
            this.prepStatement = conn.prepareStatement(sql);
            rs = this.prepStatement.executeQuery();
                if(rs.next()){
                    rs.close();
                    return true;     
                }
                else {
                    return false;
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            this.prepStatement.close();
            this.conn.close();
        } catch (SQLException ex){
        System.out.println(ex.getMessage());}
        return false;
        
        //the end
    }
    
    //return username match using the input userID
    public String getUserName(int userID){
        ResultSet rs;
        String userName = "";
        try {
            String dataQuery = "SELECT USERNAME FROM GUEST WHERE USERID = "+userID+"";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
            while(rs.next())
            {
                userName = rs.getString("USERNAME");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return userName;  
        
        //the end
    }

    //check username for a match and retrun result
    public boolean checkUserName(String username){
        ResultSet rs;
        try {
            String sql = "SELECT USERNAME FROM GUEST WHERE USERNAME ='"+username+"'";
            this.prepStatement = conn.prepareStatement(sql);
            rs = this.prepStatement.executeQuery();
                if(rs.next()){
                    rs.close();
                    return true;     
                }
                else {
                    return false;
                }
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            this.prepStatement.close();
            this.conn.close();
        } catch (SQLException ex){
        System.out.println(ex.getMessage());}
        
        return false;
        
        //the end
    }
    
    //checks username inout and whether its change or not
    public boolean checkUserName(int userID, String user_Name){
        if(!user_Name.equalsIgnoreCase(getUserName(userID))){
            return !(checkUserName(user_Name) || "User Name".equalsIgnoreCase(user_Name));    
            }
        return true;
        
        //the end
    }
    
    
    //the end of the class
}
