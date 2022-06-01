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
public class PaymentPageTest {

    /**
     * Test of getReserveID method, of class PaymentPage.
     */
    @Test
    public void testGetReserveID() {
        System.out.println("get ReserveID");
        int reserveID = 1;
        PaymentPage instance = new PaymentPage();
        instance.getReserveID(reserveID);
    }

    /**
     * Test of main method, of class PaymentPage.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PaymentPage.main(args);

    }
    
}
