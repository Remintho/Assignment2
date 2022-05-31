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
public class Report {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public Report(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
   
    public ResultSet getTodaysCheckin(String check_In){
        Date checkin = Date.valueOf(check_In);
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT USERID, ROOM_NUMBER, AMOUNT, PAYMENT_STATUES FROM RESERVATION WHERE CHECKIN_DATE = '"+checkin+"'";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    
    public ResultSet getTodaysCheckOut(String check_Out){
        Date checkOut = Date.valueOf(check_Out);
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT USERID, ROOM_NUMBER, AMOUNT, PAYMENT_STATUES FROM RESERVATION WHERE CHECKOUT_DATE = '"+checkOut+"'";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    
    
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
    }
    
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
    }
    
    public ArrayList<Integer> getRooms(String bedType){
        ResultSet rs;
        int total = 0;
        ArrayList<Integer> arrList = new ArrayList<>();
        try {
            String dataQuery = "SELECT ROOM_NUMBER FROM ROOM WHERE BED_TYPE = '"+bedType+"'";           
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
             while(rs.next()){
                int roomNumber = rs.getInt("ROOM_NUMBER");
                arrList.add(roomNumber);
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arrList;
    }
    
    public int notPaid(String status){
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
    }
    
    public int Paid(String status){
        ResultSet rs;
        int total = 0;
        try {
            String dataQuery = "SELECT AMOUNT FROM RESERVATION WHERE PAYMENT_STATUES = '"+status+"'";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
            while(rs.next()){
                int paid = rs.getInt("AMOUNT");
                total += paid;
                
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public static void main(String[] args) {
        Report rp = new Report();
        rp.Paid("Payed");
        rp.notPaid("NotPayed");
    }
}
