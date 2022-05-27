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
public class RoomManag {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public RoomManag(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    
    
    public ResultSet getPrice(String input){
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT PRICE FROM ROOM WHERE BED_TYPE = '"+input+"'";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;   
    }
    
    public ResultSet searchBy(int choice, String input){
        ResultSet rs = null;
        try {
            int inputINT = 0;
            try{
                inputINT = Integer.parseInt(input);
                }catch(NumberFormatException ex){}
            if(input.equalsIgnoreCase("")){
                rs = null;
            }
            else{
                switch (choice) {
                    case 0:
                        {
                            
                            try{
                            }catch(NumberFormatException ex){}
                            String dataQuery = "SELECT * FROM ROOM WHERE ROOM_NUMBER ="+inputINT+"";
                            this.statement = conn.createStatement();
                            rs = this.statement.executeQuery(dataQuery);
                            break;
                        }

                    case 1:
                        {
                            String dataQuery = "SELECT * FROM ROOM WHERE BED_TYPE ='"+input.toUpperCase()+"'";
                            this.statement = conn.createStatement();
                            rs = this.statement.executeQuery(dataQuery);
                            break;
                        }
                    case 2:
                        {
                            String dataQuery = "SELECT * FROM ROOM WHERE PRICE ="+inputINT+"";
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
    
    public void dataChange(String bed_Type, String price){
        int Price;
        try {
            Price = Integer.parseInt(price.trim());  
        }
        catch (NumberFormatException e) {
            Price = 0;
        }
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("UPDATE ROOM SET PRICE = "+Price+" WHERE BED_TYPE = '"+bed_Type+"'");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean checkRoomNumber(String roomNumber){
        ResultSet rs;
        int room_Number;
        try {
            room_Number = Integer.parseInt(roomNumber.trim());  
        }
        catch (NumberFormatException e) {
            room_Number = 0;
        }
        try {
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery("SELECT ROOM_NUMBER FROM ROOM WHERE ROOM_NUMBER ="+room_Number+"");
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
    
    public void deleteRecord(String roomNumber){
         int room_Number;
        try {
            room_Number = Integer.parseInt(roomNumber.trim());  
        }
        catch (NumberFormatException e) {
            room_Number = 0;
        }
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("DELETE FROM ROOM WHERE ROOM_NUMBER = "+room_Number+"");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addRecord(String roomNumber, String bedType){
         int room_Number;
         ResultSet rs  = getPrice(bedType);
         int price = 0;
        try {
            room_Number = Integer.parseInt(roomNumber.trim());  
        }
        catch (NumberFormatException e) {
            room_Number = 0;
        }
        try {
            while(rs.next()){
                price = rs.getInt("PRICE");
            }
            this.statement = conn.createStatement();
            this.statement.addBatch("INSERT INTO ROOM(ROOM_NUMBER, BED_TYPE, PRICE) VALUES("+room_Number+", '"+bedType+"', "+price+")");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

