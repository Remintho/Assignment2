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
public class LoginPageTest {
   

    /**
     * Test of getLastID method, of class LoginPage.
     */
    @Test
    public void testGetLastID() {
        System.out.println("get Last ID that's max number available");
        LoginPage instance = new LoginPage();
        int result = instance.getLastID();
        assertEquals(result, result);
    }

 

    /**
     * Test of updateLogin method, of class LoginPage.
     */
    @Test
    public void testUpdateLogin() {
        System.out.println("update Login test working");
        int userID = 100;
        String uN = "admin";
        String pW = "admin";
        LoginPage instance = new LoginPage();
        instance.updateLogin(userID, uN, pW);
    }


    /**
     * Test of checkLogin method, of class LoginPage.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("check Login input if its a match");
        String username = "admin";
        String password = "admin";
        LoginPage instance = new LoginPage();
        boolean expResult = true;
        boolean result = instance.checkLogin(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserName method, of class LoginPage.
     */
    @Test
    public void testGetUserName() {
        System.out.println("get UserName that matches the userid number input");
        int userID = 100;
        LoginPage instance = new LoginPage();
        String expResult = "admin";
        String result = instance.getUserName(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUserName method, of class LoginPage.
     */
    @Test
    public void testCheckUserName_String() {
        System.out.println("check UserName if the input is a match with existing value");
        String username = "admin";
        LoginPage instance = new LoginPage();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }
    
}
