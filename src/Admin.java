
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
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
public class Admin extends CentralFile implements ScanInterface {
    
    Scanner scan = new Scanner(System.in);
    ExtendsRoom roomExtend = new ExtendsRoom();
    LoginPage loginPage = new LoginPage();
    Reservation reservation = new Reservation();
    
    //admin menu 
    public void adminMenu() throws IOException {
        
        int input = 0;
        do{
            System.out.println("\nEnter \"X\" to Exit & \"Y\" to go back to the MainMenu");
            System.out.println("\n1. Reservation");
            System.out.println("2. Rooms");
            System.out.println("3. Registration");
            System.out.println("4. Customers Confidential");
            System.out.println("5. Logout");
            try{
                input = Integer.parseInt(scanInput(scan).trim());
                if(!(input>=1&&input<=5)){
                    System.out.println("Please enter numbers between 1-5");
                }
                }catch(NumberFormatException ex){System.out.println("Invalid input please enter numbers between 1-5");}     
            }while(!(input>=1&&input<=5));
        
        switch(input){
            case 1:
                int case1input = 0;
                do{
                    System.out.println("\n1. Reserve a room for a customer");
                    System.out.println("2. Find a reservation by ID");
                    System.out.println("3. Find a reservation by customer ID");
                    System.out.println("4. Show all reservation");
                    System.out.println("5. Return to the main page");
                    try{
                        case1input = Integer.parseInt(scanInput(scan).trim());
                        if(!(case1input>=1&&case1input<=5)){
                            System.out.println("Please enter numbers between 1-5");
                        }
                        }catch(NumberFormatException ex){System.out.println("Invalid input please enter numbers between 1-5");}     
                    }while(!(case1input>=1&&case1input<=5));
                reservationMenu(case1input);
                break;


            case 2:
                int case2input = 0;
                do{
                    System.out.println("\n1. Add/Remove room");
                    System.out.println("2. Find a room by room number");
                    System.out.println("3. Show all rooms");
                    System.out.println("4. Return to the main page");
                    try{
                        case2input = Integer.parseInt(scanInput(scan).trim());
                        if(!(case2input>=1&&case2input<=4)){
                            System.out.println("Please enter numbers between 1-4");
                        }
                        }catch(NumberFormatException ex){System.out.println("Invalid input please enter numbers between 1-4");}
                    }while(!(case2input>=1&&case2input<=4));
                roomMenu(case2input);
                break;
            case 3:
                int case3input = 0;
                do{
                    System.out.println("\n1. Register a customer");
                    System.out.println("2. Find a customer by first name and last Name");
                    System.out.println("3. Find a customer by ID");
                    System.out.println("4. Show all registration");
                    System.out.println("5. Return to the main page");
                    try{
                        case3input = Integer.parseInt(scanInput(scan).trim());
                        if(!(case3input>=1&&case3input<=5)){
                            System.out.println("Please enter numbers between 1-5");
                        }
                        }catch(NumberFormatException ex){System.out.println("Invalid input please enter numbers between 1-5");}
                    }while(!(case3input>=1&&case3input<=5));
                registrationMenu(case3input);
                break;
            case 4:
                System.out.println("\nThis is a customer confidential inforamtion, only admin can open it");
                System.out.println("Enter (N) to continue");
                String choice = scanInput(scan).trim();
                loginMenu(choice);
                break;
            case 5:
                System.out.println("\nLogging out.....");
                System.out.println("See You Again");
                System.exit(0);
                
          //end of switch       
        }
        
       //end of adminMenu
    }
    
    //checks Id and returns the value 
    public boolean checkID(int ID) throws IOException { 
        Map<Integer, Customer> map = new HashMap<>();
        readRegFile(map);
        return map.containsKey(ID);
    }
    
    //admin reservation menu 
    public void reservationMenu(int choice) throws IOException{        
        int ID = 0;
        switch (choice) {
            case 1:
                do{
                    System.out.println("\nEnter customer ID number");
                    try{
                        ID = Integer.parseInt(scanInput(scan).trim());
                        if(!checkID(ID)){
                            System.out.println("There is no such ID: "+ID);
                        }
                        }catch(NumberFormatException ex){System.out.println("invalid input please enter number ID");}
                    }while(!checkID(ID));
                    reservation.RoomChoice(ID);
                adminMenu();
                break;
            case 2:
                do{
                    System.out.println("\nEnter reservation ID");
                    try{
                        ID = Integer.parseInt(scanInput(scan).trim());
                        }catch(NumberFormatException ex){System.out.println("Invalid input please enter Reservation number ID");}
                    }while(!reservation.findReserveByID(ID));
                adminMenu();
                break;
            case 3:
                do{
                        System.out.println("\nEnter customer ID");
                        try{
                            ID = Integer.parseInt(scanInput(scan).trim());
                            if(!checkID(ID)){
                            System.out.println("There is no such ID: "+ID);
                            }                         
                            }catch(NumberFormatException ex){System.out.println("Invalid input please enter customer number ID");}
                    }while(!reservation.findReserveByUserID(ID));
                adminMenu();    
                break;
            case 4:
                reservation.printResvervation();
                adminMenu();
                break;
            case 5:
                adminMenu();
                break;
            default:
                break;
                
         //end of switch
        }
     
        //end of reservationMenu
    }
    
    
    // room admin menu
    public void roomMenu(int input) throws IOException{
        roomExtend= new ExtendsRoom();
        Map<Integer, Room> map = new HashMap<>();
        readRoomFile(map);
        int roomNumber = 0;
        int choiceInput = 0;
        int bedTypeInput = 0;
        int bookingPrice = 0;
        switch(input){
            case 1:
                do{
                    System.out.println("\n1. Add room");
                    System.out.println("2. Remove room");
                    try{
                        choiceInput = Integer.parseInt(scanInput(scan).trim());
                        if(!(choiceInput==1 || choiceInput==2)){
                            System.out.println("Please enter numbers between 1 - 2");
                        }
                        }catch(NumberFormatException ex){System.out.println("Invalid input please enter numbers between 1 - 2");}
                    }while(!(choiceInput==1 || choiceInput==2));
                if(choiceInput == 1){
                    System.out.println("\nList of all rooms");
                    roomExtend.printRoomsList();
                    do{
                        System.out.println("\nEnter New room number");
                        try{
                            roomNumber = Integer.parseInt(scanInput(scan).trim());
                            if(map.containsKey(roomNumber)){
                                    System.out.println("please enter number thats new and not in the list");
                                }
                            }catch(NumberFormatException ex){System.out.println("Invalid input please enter a number");} 
                    }while(map.containsKey(roomNumber) || roomNumber==0);
                    List<BedType> list = new ArrayList<>();
                    list.addAll(Arrays.asList(BedType.values()));
                    for(int i=0; i< list.size(); i++){
                        System.out.println(i+1+". "+list.get(i)+" Bed Room");
                    }
                    
                    do{
                    System.out.println("\nEnter BedType number according  to the list");
                    try{
                        bedTypeInput = Integer.parseInt(scanInput(scan).trim());
                        if(!(bedTypeInput>=1&&bedTypeInput<=6)){
                            System.out.println("please enter number thats in the list");
                        }
                        }catch(NumberFormatException ex){
                            System.out.println("Invalid input please enter a number");}
                    }while(!(bedTypeInput>=1&&bedTypeInput<=6) || bedTypeInput==0);
                    do{
                    System.out.println("\nEnter Booking price");
                    try{
                        bookingPrice = Integer.parseInt(scanInput(scan).trim());
                        }catch(NumberFormatException ex){
                            System.out.println("Invalid input please enter a number");}
                    }while(!(bookingPrice>0));
                    switch(choiceInput){
                        case 1:
                            roomExtend.addRoom(roomNumber, BedType.KING, bookingPrice);
                            break;
                        case 2:
                            roomExtend.addRoom(roomNumber, BedType.QUEEN, bookingPrice);
                            break;
                        case 3:
                            roomExtend.addRoom(roomNumber, BedType.SINGLE, bookingPrice);
                            break;
                        case 4:
                            roomExtend.addRoom(roomNumber, BedType.DOUBLE, bookingPrice);
                            break;
                        case 5:
                            roomExtend.addRoom(roomNumber, BedType.TRIPLE, bookingPrice);
                            break;
                        case 6:
                            roomExtend.addRoom(roomNumber, BedType.QUAD, bookingPrice);
                            break;
                    }
                    System.out.println("\nupdating file...");
                    System.out.println("updated");
                }
                else if(choiceInput == 2){
                    System.out.println("\nList of all rooms");
                    roomExtend.printRoomsList();
                    do{  
                        System.out.println("\nEnter existing room number");
                        try{
                            roomNumber = Integer.parseInt(scanInput(scan).trim());
                            if(!map.containsKey(roomNumber)){
                                System.out.println("Please enter a room number from the list above");  
                            }
                            }catch(NumberFormatException ex){
                                System.out.println("Invalid input please enter a number");}
                        }while(!map.containsKey(roomNumber));
                        roomExtend.romveRoom(roomNumber);
                        System.out.println("\nupdating file...");
                        System.out.println("updated");
                }
                break;
            case 2:
                do{
                    System.out.println("\nEnter room number");
                    try{
                        roomNumber = Integer.parseInt(scanInput(scan).trim());
                        }catch(NumberFormatException ex){System.out.println("\nInvalid input please enter room Number");}
                    }while(!(roomExtend.findRoomBy(roomNumber)));
                break;
            case 3:
                roomExtend.printRoomsList();
                break;
            case 4:
                adminMenu();
                break;
                
           //end of switch
         }
           adminMenu();
           
      //end of roomMenu
    }
    
    //admin registraion menu
    public void registrationMenu(int input) throws IOException{
        Registration registation = new Registration();
        int userID = 0;
        String fName = ""; 
        String lName = "";
               
        switch (input) {
            case 1:
                registation.Register();
                break;
            case 2:
                do{                    
                    System.out.println("\nEnter customer first name");
                    try{
                    fName = scanInput(scan).trim();
                    }catch(IllegalFormatException ex){System.out.println("invalid input");}
                    System.out.println("\nEnter customer last name");
                    try{
                    lName = scanInput(scan).trim();
                    }catch(IllegalFormatException ex){System.out.println("invalid input");}
                    }while(!registation.findUserBy(fName, lName));    
                break;
            case 3:
                do{                    
                    System.out.println("\nEnter customer ID");
                    try{
                    userID = Integer.parseInt(scanInput(scan).trim());
                    }catch(NumberFormatException ex){System.out.println("\nInvalid input please enter customer ID umber");}
                    }while(!registation.findUserBy(userID));
                break;
            case 4:
                registation.printRegistrationList();
                break;
            case 5:
                adminMenu();
                break;
            default:
                break;
          //end of witch
        }
        adminMenu();
        
        //end of registation menu
    }
    
    //admin login menu
    public void loginMenu(String choice) throws IOException{
        if(choice.equalsIgnoreCase("N")){
            System.out.println("\n");
            System.out.println("here");
            loginPage.printLoginList();
        }
        adminMenu();
    }
    
    @Override
    public String scanInput(Scanner Scan){
        String input = Scan.nextLine();
        if(input.equalsIgnoreCase("X")){
            System.out.println("\nThanks for using our program");
            System.exit(0);
        }
        else if(input.equalsIgnoreCase("Y")){
            try {
                adminMenu();
            } catch (IOException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return input;
    }

     //end of the class
}
