/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import org.junit.Test;

/**
 *
 * @author User
 */
public class ReservationPageTest {
   

    /**
     * Test of getuserName method, of class ReservationPage.
     */
    @Test
    public void testGetuserName() {
        System.out.println("get userName");
        String userName = "admin";
        ReservationPage instance = new ReservationPage();
        instance.getuserName(userName);
    }

    
}
