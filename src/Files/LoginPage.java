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
public class LoginPage {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public LoginPage(){
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
                lastID = (((Number)rs.getObject(1)).intValue());
                
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lastID; 
    }
    
    public void addLogin(String uN, String pW){
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("INSERT INTO GUEST (USERID, USERNAME, PASSWORD)"
                    + "VALUES((SELECT USERID FROM CUSTOMER WHERE USERID ="+getLastID()+"), '"+uN+"', '"+pW+"')");
        this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
        try {
            this.statement.close();
            this.conn.close();
        } catch (SQLException e){}
    }
    public boolean checkLogin(String username, String password){
        ResultSet rs;
        try {
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery("SELECT * FROM GUEST WHERE USERNAME ='"+username+"' AND PASSWORD = '"+password+"' ");
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
            this.statement.close();
            this.conn.close();
        } catch (SQLException e){}
        return false;
    }
    
    public boolean checkUserName(String username){
        ResultSet rs;
        try {
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery("SELECT USERNAME FROM GUEST WHERE USERNAME ='"+username+"'");
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
            this.statement.close();
            this.conn.close();
        } catch (SQLException e){}
        
        return false;
    }
    
    public static void main(String[] args) {
        LoginPage lp = new LoginPage();
//        if(lp.checkLogin("home1989", "dacey321") == true){
//            System.out.println("true");
//        }
//        else{
//            System.out.println("false");
//        }


        if(lp.checkUserName("rame") == true){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
        
    }
}
