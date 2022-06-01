/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class AdminPageTest {
    

    /**
     * Test of searchBy method, of class AdminPage.
     */
    @Test
    public void testSearchBy() {
        System.out.println("search By");
        int choice = 0;
        String input = "";
        AdminPage instance = new AdminPage();
        ResultSet expResult = null;
        ResultSet result = instance.searchBy(choice, input);
        assertEquals(expResult, result);
    }

    /**
     * Test of dataChange method, of class AdminPage.
     */
    @Test
    public void testDataChange() {
        System.out.println("data Change/alter");
        String userId = "admin";
        String fName = "admin";
        String lName = "admin";
        String age = "23";
        String email = "admin@hotel.com";
        String phNumber = "090342343";
        AdminPage instance = new AdminPage();
        instance.dataChange(userId, fName, lName, age, email, phNumber);
    }

    /**
     * Test of deleteRecord method, of class AdminPage.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("delete Record");
        String userId = "1";
        AdminPage instance = new AdminPage();
        instance.deleteRecord(userId);
    }
    
}
