/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class RegistrationTest {
   


    /**
     * Test of updateReg method, of class Registration.
     */
    @Test
    public void testUpdateReg() {
        System.out.println("updateReg");
        int userID = 100;
        String fName = "admin";
        String lName = "admin";
        String age = "100";
        String email = "admin@hotel.com";
        String phNumber = "0932432432";
        Registration instance = new Registration();
        instance.updateReg(userID, fName, lName, age, email, phNumber);
    }


    /**
     * Test of add user method, of class Registration.
     */
    @Test
    public void testAdduser() {
        System.out.println("adduser");
        String fName = "admin";
        String lName = "admin";
        String age = "100";
        String phNumber = "0243343423";
        String email = "admin@hotel.com";
        Registration instance = new Registration();
        instance.adduser(fName, lName, age, phNumber, email);
    }

    /**
     * Test of checkInfo method, of class Registration.
     */
    @Test
    public void testCheckInfo() {
        System.out.println("checkInfo");
        String fName = "admin";
        String lName = "admin";
        String age = "100";
        String phNumber = "0234324324";
        String email = "admin@gmail.com";
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.checkInfo(fName, lName, age, phNumber, email);
        assertEquals(expResult, result);
    }

    /**
     * Test of check method, of class Registration.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
        String fName = "admin";
        String lName = "admin";
        String age = "100";
        String phNumber = "023423424";
        String email = "admin@hotel.com";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.check(fName, lName, age, phNumber, email);
        assertEquals(expResult, result);
    }
    
}
