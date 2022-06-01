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
public class AdminPageTest {
    

    /**
     * Test of todaysInreport method, of class AdminPage.
     */
    @Test
    public void testTodaysInreport() {
        System.out.println("todays checkIn report");
        AdminPage instance = new AdminPage();
        instance.todaysInreport();
    }

    /**
     * Test of todaysOutreport method, of class AdminPage.
     */
    @Test
    public void testTodaysOutreport() {
        System.out.println("todays checkOut report");
        AdminPage instance = new AdminPage();
        instance.todaysOutreport();
    }

    /**
     * Test of reportTable method, of class AdminPage.
     */
    @Test
    public void testReportTable() {
        System.out.println("report Table");
        AdminPage instance = new AdminPage();
        instance.reportTable();
    }

    /**
     * Test of custTable method, of class AdminPage.
     */
    @Test
    public void testCustTable() {
        System.out.println("customer Table");
        AdminPage instance = new AdminPage();
        instance.custTable();
    }

    /**
     * Test of reserveTable method, of class AdminPage.
     */
    @Test
    public void testReserveTable() {
        System.out.println("reservation Table");
        AdminPage instance = new AdminPage();
        instance.reserveTable();
    }

    /**
     * Test of roomTable method, of class AdminPage.
     */
    @Test
    public void testRoomTable() {
        System.out.println("room Table");
        AdminPage instance = new AdminPage();
        instance.roomTable();
    }

    
}
