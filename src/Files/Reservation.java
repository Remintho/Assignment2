/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
    LocalDate todaysDate = LocalDate.now();
    int roomNumber;
    String userName;
    LocalDate  bookIn ;
    LocalDate  bookOut;
    DateTimeFormatter format;    
    private String checkIn;
    private String checkOut;
    SimpleDateFormat formattter;

    
    public Reservation(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    
    public void getuserName(String userName){
        this.userName = userName;
    }
  
    //get last reserve ID then add 1 to get new reserve ID
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
        
        //the end
    }
    
    //get price of a specific room number
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
        
        //the end
    }
    
    //checks the difference between two dates then returns amount number
    public float getAmount(Date checkIn, Date checkOut) {
        
        format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookIn = LocalDate.parse(checkIn.toString(), format);
        bookOut = LocalDate.parse(checkOut.toString(), format);
        long differ = ChronoUnit.DAYS.between(bookIn, bookOut);
        float price = differ * getPrice();
        return price;
        
        //the end
    }
    
    //get userID that matches the username
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
        
        //the end
    }
    
    //add new reservation 
    public void addReserve(int userID, String ROOM_NUMBER, String CHECKIN_DATE, String CHECKOUT_DATE, String PAYMENT_STATUES){
        Date checkin = Date.valueOf(CHECKIN_DATE);
        Date checkOUT = Date.valueOf(CHECKOUT_DATE);
        int room_Number = Integer.parseInt(ROOM_NUMBER);
        this.roomNumber = room_Number;
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("INSERT INTO RESERVATION (RESERVEID, USERID, ROOM_NUMBER, CHECKIN_DATE, CHECKOUT_DATE, AMOUNT, PAYMENT_STATUES) "
                    + "VALUES("+getLastReserveID()+",(SELECT USERID FROM CUSTOMER WHERE USERID ="+userID+"), "
                            + ""+room_Number+","
                                    + " '"+checkin+"',"
                                            + " '"+checkOUT+"',"
                                                    + " "+getAmount(checkin, checkOUT)+","
                                                            + " '"+PAYMENT_STATUES+"')");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
        
        //the end
    }
    
  
    //get all rooms number of specific bedtype
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
        
        //the end
    }
    
     //convertes string date to Localdate format also check if its before or after checkIn date
    public boolean convertCheckIn(String checkIn) {
        
        this.checkIn = checkIn;
         format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookIn = LocalDate.parse(checkIn, format);
        return compareTodaysDate(todaysDate,bookIn) == true;

        //the end
    }
    
    
    //convertes string date to Localdate format also check if its before or after checkIn date
    public boolean convertCheckOut(String checkOut) {   
        
        this.checkOut = checkOut;
        format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookOut = LocalDate.parse(checkOut, format);
        return compareDate(bookIn,bookOut) == true;

        //the end
    }
    
    //compares checkIn with checkout  date
    public boolean compareDate(LocalDate  bookIn, LocalDate  bookOut) {
        
        if(bookIn.compareTo(bookOut) > 0) {
            return false;
        } else if(bookIn.compareTo(bookOut) < 0) {
            return true;
        } else if(bookIn.compareTo(bookOut) == 0){
            return false;
        }
        return false; 
        
        //the end
    }
    
    //compares checkin date with todays date 
    public boolean compareTodaysDate(LocalDate  todaysDate, LocalDate  bookIn) {
        
        this.bookIn = bookIn;
        this.todaysDate =todaysDate;
        if(todaysDate.compareTo(bookIn) > 0) {
            return false;
        } else if(todaysDate.compareTo(bookIn) < 0) {
            return true;
        } else if(todaysDate.compareTo(bookIn) == 0){
            return true;
        }
        return false;
        
        //the end
    }
    
    //check if input date is a match with input
    public boolean checkInDates(JDateChooser checkInDate){
        formattter = new SimpleDateFormat("yyyy-MM-dd");
        String check_In = formattter.format(checkInDate.getDate());
        return convertCheckIn(check_In);
        
        //the end
    }
    
    //check if input date is a match with the input
    public boolean checkoutDates(JDateChooser checkOutDate){
        formattter = new SimpleDateFormat("yyyy-MM-dd");
        String check_Out = formattter.format(checkOutDate.getDate());
        return convertCheckOut(check_Out);
        
        //the end
    }

    
    //the end of the class
}
