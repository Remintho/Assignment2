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
public class RoomManagTest {
    

    /**
     * Test of searchBy method, of class RoomManag.
     */
    @Test
    public void testSearchBy() {
        System.out.println("search By");
        int choice = 0;
        String input = "";
        RoomManag instance = new RoomManag();
        ResultSet expResult = null;
        ResultSet result = instance.searchBy(choice, input);
        assertEquals(expResult, result);
    }

    /**
     * Test of dataChange method, of class RoomManag.
     */
    @Test
    public void testDataChange() {
        System.out.println("data Change/alter");
        String bed_Type = "";
        String price = "";
        RoomManag instance = new RoomManag();
        instance.dataChange(bed_Type, price);
    }

    /**
     * Test of checkRoomNumber method, of class RoomManag.
     */
    @Test
    public void testCheckRoomNumber() {
        System.out.println("check  Room Number");
        String roomNumber = "1";
        RoomManag instance = new RoomManag();
        boolean expResult = true;
        boolean result = instance.checkRoomNumber(roomNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteRecord method, of class RoomManag.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("delete Record");
        String roomNumber = "";
        RoomManag instance = new RoomManag();
        instance.deleteRecord(roomNumber);
    }

    /**
     * Test of addRecord method, of class RoomManag.
     */
    @Test
    public void testAddRecord() {
        System.out.println("add Record");
        String roomNumber = "";
        String bedType = "";
        RoomManag instance = new RoomManag();
        instance.addRecord(roomNumber, bedType);
    }
    
}
