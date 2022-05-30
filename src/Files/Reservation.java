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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Reservation {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    LoginPage lPage = new LoginPage();
    int roomNumber;
    String userName;
    
    public Reservation(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    public void getuserName(String userName){
        this.userName = userName;
    }
  
    
    public int getLastReserveID(){
        ResultSet rs;
       
        int lastID = 0;
        try {
            rs = this.statement.executeQuery("SELECT MAX(RESERVEID) FROM RESERVATION");
            if(rs.next()){
                lastID = (((Number)rs.getObject(1)).intValue()) + 1;
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lastID; 
    }
    
    public int getPrice(){
        ResultSet rs;
       
        int price = 0;
        try {
            rs = this.statement.executeQuery("SELECT PRICE FROM ROOM WHERE ROOM_NUMBER = "+roomNumber+"");
            if(rs.next()){
                price = (((Number)rs.getObject(1)).intValue());
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return price; 
    }
    
    //checks the difference between two dates then returns amount number
    public float getAmount(Date checkIn, Date checkOut) {
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate bookIn = LocalDate.parse(checkIn.toString(), format);
        LocalDate bookOut = LocalDate.parse(checkOut.toString(), format);
        long differ = ChronoUnit.DAYS.between(bookIn, bookOut);
        float price = differ * getPrice();
        return price;
    }
    
    public int getIDLogin(String userName){
        ResultSet rs;
        int userID = 0;
        try {
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery("SELECT USERID FROM GUEST WHERE USERNAME = '"+userName+"' ");
            if(rs.next()){
                userID = rs.getInt("USERID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userID; 
    }
    
    
    public void addReserve(int userID, String ROOM_NUMBER, String CHECKIN_DATE, String CHECKOUT_DATE, String PAYMENT_STATUES){
        Date checkin = Date.valueOf(CHECKIN_DATE);
        Date checkOut = Date.valueOf(CHECKOUT_DATE);
        int room_Number = Integer.parseInt(ROOM_NUMBER);
        this.roomNumber = room_Number;
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("INSERT INTO RESERVATION (RESERVEID, USERID, ROOM_NUMBER, CHECKIN_DATE, CHECKOUT_DATE, AMOUNT, PAYMENT_STATUES) VALUES("+getLastReserveID()+",(SELECT USERID FROM CUSTOMER WHERE USERID ="+userID+"), "+room_Number+", '"+checkin+"', '"+checkOut+"', "+getAmount(checkin, checkOut)+", '"+PAYMENT_STATUES+"')");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
    }
    
  
    
    public ArrayList<Integer> getRoomNumber(String selectedItem){
        ResultSet rs;
        ArrayList<Integer> room_Number = new ArrayList<>();
        try {
            String dataQuery = "SELECT ROOM_NUMBER FROM ROOM WHERE BED_TYPE = '"+selectedItem+"'";
            this.statement = conn.createStatement();
            rs = statement.executeQuery(dataQuery);
            while(rs.next()){
                    int number = rs.getInt("ROOM_NUMBER");
                    room_Number.add(number);
                }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        return room_Number;   
    }
    
    
}
