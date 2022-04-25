package files;
import java.io.IOException;
import java.text.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class HotelSystem {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {

        System.out.println("\n=====================================");
        System.out.println("Welcome to Hotel Intercontinental");
        System.out.println("=====================================\n");
       
        System.out.println("Enter \"X\" to Exit & \"Y\" to go back to the MainMenu\n");
        LoginPage loginpage = new LoginPage();
        loginpage.page();
        
    }
    
}
        
