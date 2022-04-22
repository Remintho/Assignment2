
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
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
public class Registration extends CentralFile implements ScanInterface{
    LoginPage loginPage = new LoginPage();
    Reservation reservation = new Reservation();
    String userName;
    String password;
    private int userId;
 
    public  Registration() {}
    public  Registration(int userId) {
        this.userId =  userId;
    }

        
    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }
    
    // registration process 
    public void Register() throws IOException {
        String fName;
        String lName;
        int age = 0;
        String email;
        String phNumber;
        Scanner scan = new Scanner(System.in);
        Map<Integer, Customer> map = new HashMap<>();
        
        //iterate over HashMap entries
        map.entrySet().forEach(entry -> {
            System.out.println( entry.getKey() + " " + entry.getValue() );
        });
        
        
        //First Name
        do{
            System.out.println("\nCan you please Enter First Name");
            fName = scanInput(scan).trim();
            }while(fName.matches("^.*[^a-zA-Z ].*$")|| fName.equalsIgnoreCase(""));

        
        //Last Name
        do{
            System.out.println("\nCan you please Enter Last Name");
            lName = scanInput(scan).trim();
            }while(lName.matches("^.*[^a-zA-Z ].*$")|| lName.equalsIgnoreCase(""));
        
        
        //Age
        do{
            System.out.println("\nCan you please Enter Age");
            try{
                age = Integer.parseInt(scanInput(scan).trim());
                } catch (NumberFormatException ex) {}
            }while((age<18));
         
        
        //Email
        do{
            System.out.println("\nCan you please Enter Email Address");
            email = scanInput(scan).trim();
            }while(!(isValid(email)));
        
        
        //Phone Number   
        do{
            System.out.println("\nCan you please Enter Phone Number");
            phNumber = scanInput(scan).trim();
            }while((phNumber.matches("^.*[^0-9 ].*$")) || phNumber.equalsIgnoreCase(""));
        
        
        TreeMap<Integer, Customer> treeMap = new TreeMap<>();
        readRegFile(treeMap);
        
        if(treeMap.isEmpty()) {
            
            userId = 101;
        }
        else {
            
            userId = treeMap.lastKey() +1;
        }
        
        Customer cust = new Customer(fName, lName, age, email, phNumber);
        map.put(getUserId(), cust);
        writeRegToFile(map);
        
        do{
            System.out.println("\nCan you please Create new UserName");
            userName = scanInput(scan).trim();
            System.out.println("Can you please Create new Password");
            password = scanInput(scan).trim();
            System.out.println("\n");
            }while(loginPage.addCustomer(userName, password, userId)==false || userName.equalsIgnoreCase("") || password.equalsIgnoreCase(""));
        System.out.println("Continue to book a room? (N) to continue");
        String input = scanInput(scan);
        if(input.equalsIgnoreCase("N")){
            reservation.reserve(getUserId());
        }
        else {
            System.out.println("\nUpdating database.....");
            System.out.println("Finshed!");
            System.exit(0);
        }
       
        
       //end of register 
    }
    

    //checks if the email input is valid
    public boolean isValid(String email) {
        
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
           
    }

                        
    // prints all registration List
    public void printRegistrationList() throws IOException{
        Map<Integer, Customer> map = new HashMap<>();
        readRegFile(map);
        map.entrySet().forEach(entry -> {
            System.out.println("UserID: "+entry.getKey()+
                    ", First-Name: "+entry.getValue().getFName()+
                    ", Last-Name: "+entry.getValue().getLName()+
                    ", Age: "+entry.getValue().getAge()+
                    ", Email: "+entry.getValue().getEmail()+
                    ", Ph-Number: "+entry.getValue().getPhNumber());
                    
        });
    }
    
    //find user by UserID 
    public boolean findUserBy(int UserID) throws IOException{
        Map<Integer, Customer> map = new HashMap<>();
        readRegFile(map);
        if(map.containsKey(UserID)) {
            
            System.out.println( "First-Name: "+map.get(UserID).getFName()+
                                ", Last-Name: "+map.get(UserID).getLName()+
                                ", Age: "+map.get(UserID).getAge()+
                                ", Email: "+map.get(UserID).getEmail()+
                                ", Ph-Number: "+map.get(UserID).getPhNumber());
            return true;
        }
        else 
            System.out.println("This customer ID is not existent");
        return false;
    }
    
    //find user by First-Name OR Lasr-Name
    public boolean findUserBy(String fName, String lName) throws IOException {
        Map<Integer, Customer> map = new HashMap<>();
        readRegFile(map);
        List<Customer> valueList = new ArrayList<>();
        for ( Map.Entry<Integer, Customer> entry: map.entrySet() ) {
            if (entry.getValue().getFName().equalsIgnoreCase(fName) || entry.getValue().getLName().equalsIgnoreCase(lName)  )
                valueList.add(map.get(entry.getKey()));
        }
        if(valueList.isEmpty()){
            System.out.println("The given Name is not in our database");
            return false;
        }
        else{
            System.out.println("\n");
            for(Customer value : valueList){
            System.out.println(value);
            }
            return true;
        }
        
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
                LoginPage loginpage = new LoginPage();
                loginpage.page();
            } catch (ParseException | IOException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return input;
    }
    
   //end of the class
}
