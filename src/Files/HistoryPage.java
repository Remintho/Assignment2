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
public class HistoryPage {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    
    public HistoryPage(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    
    public ResultSet userData(int userID){
        ResultSet rs = null;
        try {
            String dataQuery = "SELECT RESERVEID, CHECKIN_DATE, CHECKOUT_DATE, AMOUNT, PAYMENT_STATUES FROM RESERVATION WHERE USERID = "+userID+"";
            this.statement = conn.createStatement();
            rs = this.statement.executeQuery(dataQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
}
