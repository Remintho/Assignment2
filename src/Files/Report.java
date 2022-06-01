/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Report extends Reservation {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public Report(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
   //returns todays checkin data 
    public ResultSet getTodaysCheckin(String checcInDate){
        Date checkin = Date.valueOf(checcInDate);
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT USERID, ROOM_NUMBER, AMOUNT, PAYMENT_STATUES FROM RESERVATION WHERE CHECKIN_DATE = '"+checkin+"'";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
        
        //the end
    }
    
    //returns todays checkout data
    public ResultSet getTodaysCheckOut(String checkOutDate){
        Date checkOut = Date.valueOf(checkOutDate);
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT USERID, ROOM_NUMBER, AMOUNT, PAYMENT_STATUES FROM RESERVATION WHERE CHECKOUT_DATE = '"+checkOut+"'";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
        
        //the end
    }
    
    //returns names that matches the userID
    public ResultSet getFullName(int userID){
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT FNAME, LNAME FROM CUSTOMER WHERE USERID = "+userID+"";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
        
        //the end
    }
    
    //get amount spend on a specific room
    public int getRevenue(ArrayList<Integer> arrList ){
        ResultSet rs;
        int total = 0;
        try {
            for(int  room: arrList){
             String Query = "SELECT AMOUNT FROM RESERVATION WHERE ROOM_NUMBER = "+room+"";           
                this.statement = conn.createStatement();
                rs = this.statement.executeQuery(Query);
                while(rs.next()){
                    int amount = rs.getInt("AMOUNT");
                    total += amount;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
        
        //the end
    }
    
    //get all rooms number of specific bedtype
    public ArrayList<Integer> getRooms(String bedType){
        ResultSet rs;
        ArrayList<Integer> arrList = new ArrayList<>();
        try {
            String dataQuery = "SELECT ROOM_NUMBER FROM ROOM WHERE BED_TYPE = '"+bedType+"'";           
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
             while(rs.next()){
                int room_Number = rs.getInt("ROOM_NUMBER");
                arrList.add(room_Number);
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arrList;
        
        //the end
    }
    
    //get all amount thats paid and not paid
    public int getStatus(String status){
        ResultSet rs;
        int total = 0;
        try {
            String dataQuery = "SELECT AMOUNT FROM RESERVATION WHERE PAYMENT_STATUES = '"+status+"'";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
            while(rs.next()){
                int npaid = rs.getInt("AMOUNT");
                total += npaid;
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
        
        //the end
    }
    

    //get all customers data 
    public ResultSet getCust(){
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT * FROM CUSTOMER";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return rs;
        
        //the end
    }
    
    
    //get all reservation data 
    public ResultSet getReserve(){
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT * FROM RESERVATION";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return rs;  
        
        //the end
    }
    
    //get all rooms data
    public ResultSet getRoom(){
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT * FROM ROOM";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return rs;
        
        //the end
    }
    
    
    //the end of the class
}

