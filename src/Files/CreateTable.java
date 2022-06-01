/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author User
 */
public class CreateTable {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public CreateTable(){
       dbManager = new DBManager();
       conn = dbManager.getConnection();
    }
    
    //create sutomer table
    public void custTable(){
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("CREATE TABLE CUSTOMER ("
                    + "USERID INT not null, "
                    + "FNAME VARCHAR(20), "
                    + "LNAME VARCHAR(20), "
                    + "AGE INT, "
                    + "EMAIL VARCHAR(50), "
                    + "PHONE VARCHAR(20), "
                    + "PRIMARY KEY (USERID))");
            this.statement.addBatch("INSERT INTO CUSTOMER VALUES( 100, 'admin ', 'admin ', 100,'amdin@hotel.com', '0923234234'), \n"
                    + "(101 , 'logan ', 'dacey ', 32,'logandacey@armspy.com', '0219737437'), \n"
                    + "( 102 , 'jonathan ', 'monsoor ', 61,'jonathanmonsoor@teleworm.us', '0221949418'), \n"
                    + "( 103 , 'zachary ', 'timms ', 20,'zacharytimms@telewarms.us', '0277472236'), \n"
                    + "( 104 , 'molly ', 'kennion ', 41,'mollykennion@yahoo.com', '0267555019'), \n"
                    + "( 105 , 'hudson ', 'wertheim ', 64,'hudsonwertheim@jourpadie.com', '0208181951'), \n"
                    + "( 106 , 'jeremy ', 'gabriel ', 54,'jermeyGabriel@dayrep.com', '0278958000'), \n"
                    + "( 107 , 'lola ', 'lewers ', 55,'lolalewers@jourpaide.com', '0287412304'), \n"
                    + "( 108 , 'finn ', 'hartnett ', 26,'finnhartnett@dayreo.com', '0274177506')");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
        
        // the end
    }
    
    //create user/guest table 
    public void userTable(){
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("CREATE TABLE GUEST ("
                    + "USERID INT, "
                    + "USERNAME VARCHAR(20), "
                    + "PASSWORD VARCHAR(20), "
                    + "FOREIGN KEY (USERID) REFERENCES CUSTOMER(USERID))");
            this.statement.addBatch("INSERT INTO GUEST VALUES(100, 'admin', 'admin'), \n"
                    + "(101,'home1989','dacey321'), \n"
                    + "(102,'thatera1960 ','monsoor1960 '), \n"
                    + "(103,'panduch', 'timms2001'), \n"
                    + "(104,'pook1980', 'molly321'), \n"
                    + "(105,'womic1951', 'hudson987'), \n"
                    + "(106,'ellostaid', 'jermey5987'), \n"
                    + "(107,'hicend', 'loltext'), \n"
                    + "(108,'racb1995', 'finnhart342')");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
        
        //the end
    }
    
    //create room table
    public void roomTable(){
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("CREATE TABLE ROOM ("
                    + "ROOM_NUMBER INT not null, "
                    + "BED_TYPE VARCHAR(20), "
                    + "PRICE FLOAT, "
                    + "PRIMARY KEY (ROOM_NUMBER))");
            this.statement.addBatch("INSERT INTO ROOM VALUES(1, 'KING', 400), \n"
                    + "(2,'KING', 400), \n"
                    + "(3,'KING', 400), \n"
                    + "(4,'QUEEN', 300), \n"
                    + "(5,'QUEEN', 300), \n"
                    + "(6,'QUEEN', 300), \n"
                    + "(7,'SINGLE', 100), \n"
                    + "(8,'SINGLE', 100), \n"
                    + "(9,'SINGLE', 100), \n"
                    + "(10,'DOUBLE', 200), \n"
                    + "(11,'DOUBLE', 200), \n"
                    + "(12,'DOUBLE', 200), \n"
                    + "(13,'TRIPLE', 300), \n"
                    + "(14,'TRIPLE', 300), \n"
                    + "(15,'TRIPLE', 300), \n"
                    + "(16,'QUAD', 400), \n"
                    + "(17,'QUAD', 400), \n"
                    + "(18,'QUAD', 400)");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
        
        //the end
    }
    
    //create reservation table
    public void reservationTable(){
        try {
            this.statement = conn.createStatement();
            this.statement.addBatch("CREATE TABLE RESERVATION ("
                    + "RESERVEID INT not null, "
                    + "USERID INT, "
                    + "ROOM_NUMBER INT, "
                    + "CHECKIN_DATE DATE, "
                    + "CHECKOUT_DATE DATE, "
                    + "AMOUNT FLOAT, "
                    + "PAYMENT_STATUES VARCHAR(20), "
                    + "PRIMARY KEY (RESERVEID), "
                    + "FOREIGN KEY (ROOM_NUMBER) REFERENCES ROOM(ROOM_NUMBER), "
                    + "FOREIGN KEY (USERID) REFERENCES CUSTOMER(USERID))");
            this.statement.addBatch("INSERT INTO RESERVATION VALUES(1, 102, 4, '2022-04-15', '2022-04-16', 400, 'payed'), \n"
                    + "(2, 103, 7, '2022-05-15', '2022-05-16', 400, 'payed'), \n"
                    + "(3, 104, 13, '2022-05-15', '2022-05-17', 800, 'payed'), \n"
                    + "(4, 105, 15, '2022-05-15', '2022-05-19', 1600, 'payed'), \n"
                    + "(5, 106, 11, '2022-05-15', '2022-05-30', 6000, 'payed'), \n"
                    + "(6, 107, 4, '2022-05-15', '2022-06-17', 800, 'payed'), \n"
                    + "(7, 108, 16, '2022-05-15', '2022-05-20', 1500, 'payed')");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
        }
        
        //the end 
    }
    
    //main method
    public static void main(String[] args) {
        CreateTable table = new CreateTable();
        table.custTable();
        table.userTable();
        table.roomTable();
        table.reservationTable();
    }
    
    
    //the end of the class
}
