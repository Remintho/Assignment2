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
public class LoginPageTest {
    

    /**
     * Test of getID method, of class LoginPage.
     */
    @Test
    public void testGetID() {
        System.out.println("get ID");
        int userID = 110;
        LoginPage instance = new LoginPage();
        instance.getID(userID);
    }
    
}
