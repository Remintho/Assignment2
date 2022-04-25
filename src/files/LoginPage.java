package files;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class LoginPage extends CentralFile implements ScanInterface {       

    public void page() throws ParseException, IOException {
        Reservation reservation = new Reservation();
        Scanner scan = new Scanner(System.in);
        int input = 0;
        String userName;
        String password;  
        int Answer = 0;
        LoginPage loginpage = new LoginPage();
        Registration register = new Registration();
        
       do{
        System.out.println("\n1.Customer Login");
        System.out.println("2.Admin Login");
        try{
        input = Integer.parseInt(loginpage.scanInput(scan).trim());
        if(!(Answer == 1 || Answer == 2)){
            System.out.println("Please input numbers between 1 - 2");
        }
        }catch (NumberFormatException ex) {
            System.out.println("Invalid input, please use numbers between 1 - 2");
        }
            switch (input) {
                case 1:
                    do{
                        System.out.println("\n1. New Customer");
                        System.out.println("2. Existing Customer");
                        try{
                            Answer = Integer.parseInt(loginpage.scanInput(scan).trim());
                            if(!(Answer == 1 || Answer == 2)){
                                System.out.println("Please input numbers between 1 - 2");
                            }
                            }catch (NumberFormatException ex) {
                                System.out.println("Invalid input, please use numbers between 1 - 2");
                             }
                        }while(!(Answer == 1 || Answer == 2));
                        switch (Answer) {
                            case 1:
                                    try {
                                        register.Register();
                                        }catch (IOException ex) {
                                            Logger.getLogger(HotelSystem.class.getName()).log(Level.SEVERE, null, ex);}
                                    break;
                            case 2:
                                while(true){
                                        System.out.println("\nUserName, X to exit");
                                        userName = loginpage.scanInput(scan).trim();
                                        System.out.println("Password, X to exit");
                                        password = loginpage.scanInput(scan).trim();
                                        if(verify(userName, password) == true) {
                                            reservation.reserve((retriveId(userName)));

                                            break;
                                        }
                                    }
                                break;
                        }
                        break;       
                case 2:
                    while(true){
                            System.out.println("\nUsername, X to exit");
                            userName = loginpage.scanInput(scan).trim();
                            System.out.println("Password, X to exit");
                            password = loginpage.scanInput(scan).trim();
                            if(userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                                System.out.println("you've logged in");
                                System.out.println("\n\nWelcome Back\n\n");
                                Admin admin = new Admin();
                                admin.adminMenu();                             
                                break;
                            }
                            else {
                                System.out.println("\nIncorrect password or username, please try again\n");
                            }
                    }
                    break;
            }
        }while(!(input == 1 || input == 2));
        
    
    }

    
    //this verify method checks if the username is on existing and matches with password 
    public boolean verify(String userName, String password) throws IOException {
        Map<String, LoginInfo> LoginMap = new HashMap<>();
        readLoginFile(LoginMap);
        
        if (LoginMap.containsKey(userName)                                    
            && LoginMap.get(userName).getPassword().equals(password)) {
            System.out.println("You've logged in.");
            return true;
            }
        
        else{
            System.out.println("\nIncorrect password or username, please try again\n");
            return false;
            }

    }
   
   
    //this method gets a login ID and returns it
    public int retriveId(String userName) throws IOException, ParseException {
        
        Map<String, LoginInfo> LoginMap = new HashMap<>();
        readLoginFile(LoginMap);
        
        if(LoginMap.isEmpty()) {
            System.out.println("Our Database is empty");
            page();
        }
        LoginInfo login = LoginMap.get(userName);
        return login.getID();    

    }
   
   
    //this adds a customer to a File database 
    public boolean addCustomer(String userName, String password, int ID) throws IOException {   
        
        Map<String, LoginInfo> LoginMap = new HashMap<>();
        readLoginFile(LoginMap);
        if(LoginMap.isEmpty() || !LoginMap.containsKey(userName)) {
              
            Map<String, LoginInfo> map = new HashMap<>();
            LoginInfo login = new LoginInfo(password, ID);
            map.put(userName, login);
            writeLoginToFile(map);
            System.out.println("You have successfuly registered with Hotel Intercontinental. yay!");
            return true;
        }
        else{
        System.out.println("please use different username");
        return false;
        }
    }
   
     //this prints all Login details that are in a file
     public void printLoginList() throws IOException{
        Map<String, LoginInfo> map = new HashMap<>();
        readLoginFile(map);
        for (Map.Entry<String, LoginInfo> entry : map.entrySet())
               System.out.println("UserName = "+entry.getKey()+
                                ", Password = "+entry.getValue().getPassword()+
                                ", UserID = "+entry.getValue().getID());
        
    }
    
     
    @Override
    public String scanInput(Scanner Scan) { 
        String input = Scan.nextLine();
        if(input.equalsIgnoreCase("X")) {
            System.out.println("Thanks for using our program");
            System.exit(0);
        }
        else if(input.equalsIgnoreCase("Y")){
            try {
                page();
            } catch (ParseException | IOException ex) {
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return input;
    }
    
    //end of the class
}
