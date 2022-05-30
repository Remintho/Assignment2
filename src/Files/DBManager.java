/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author User
 */
public final class DBManager {
    private static final String USER_NAME = "hotel"; 
    private static final String PASSWORD = "hotel"; 
    private static final String URL = "jdbc:derby:HotelDB;create=true";  
    
    
    Connection conn;
    
    public DBManager(){
        establishConn();
    }
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public void establishConn(){
        if(conn == null){
            try {
//                derby.drda.startNetworkServer=true;
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL+" connection successfull....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }
    
    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeConn() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
    }
}
