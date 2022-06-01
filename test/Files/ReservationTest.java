/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class ReservationTest {
      

    /**
     * Test of getuserName method, of class Reservation.
     */
    @Test
    public void testGetuserName() {
        System.out.println("get user Name");
        String userName = "admin";
        Reservation instance = new Reservation();
        instance.getuserName(userName);
    }


    /**
     * Test of getPrice method, of class Reservation.
     */
    @Test
    public void testGetPrice() {
        System.out.println("get Price");
        int roomnumber = 1;
        Reservation instance = new Reservation();
        int expResult = 400;
        int result = instance.getPrice(roomnumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAmount method, of class Reservation.
     */
    @Test
    public void testGetAmount() {
        System.out.println("get Amount");
        Date cin = new Date(2022, 6, 3);
        Date cout = new Date(2022, 6, 10);
        Date checkIn = cin;
        Date checkOut = cout;
        Reservation instance = new Reservation();
        float expResult = 0.0F;
        float result = instance.getAmount(checkIn, checkOut);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getIDLogin method, of class Reservation.
     */
    @Test
    public void testGetIDLogin() {
        System.out.println("get ID Login");
        String userName = "admin";
        Reservation instance = new Reservation();
        int expResult = 100;
        int result = instance.getIDLogin(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of addReserve method, of class Reservation.
     */
    @Test
    public void testAddReserve() {
        System.out.println("add new Reservation");
        int userID = 100;
        String ROOM_NUMBER = "1";
        String CHECKIN_DATE = "2022-10-10";
        String CHECKOUT_DATE = "2022-10-12";
        String PAYMENT_STATUES = "payed";
        Reservation instance = new Reservation();

    }


    /**
     * Test of convertCheckIn method, of class Reservation.
     */
    @Test
    public void testConvertCheckIn() {
        System.out.println("convert CheckIn");
        String checkIn = "2022-10-10";
        Reservation instance = new Reservation();
        boolean expResult = true;
        boolean result = instance.convertCheckIn(checkIn);
        assertEquals(expResult, result);
    }

    
}
