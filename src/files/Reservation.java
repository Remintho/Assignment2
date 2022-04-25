package files;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author User
 */
public class Reservation extends CentralFile implements ScanInterface {

    private int roomNumber;
    private String checkIn;
    private String checkOut;
    private int total;
    private int reserveID;
    private int userID;
    private String pay = "UnPaid";;
    LocalDate  bookIn ;
    LocalDate  bookOut;
    LocalDate todaysDate = LocalDate.now();
    DateTimeFormatter format;
    Scanner scan = new Scanner(System.in);
    Map<Integer, Customer> mapreserve;
    ExtendsRoom roomExtend = new ExtendsRoom();
    
 
    
    public  Reservation(){}
    public  Reservation(int userID, int roomNumber, String checkIn, String checkOut, int total, String pay) {
        
        this.userID = userID;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut= checkOut;
        this.total = total;
        this.pay = pay;
    }
    
    
    //reserve is called by other class as entry
    public void reserve(int userID) throws IOException {
         
        this.userID = userID;
        System.out.println("\n\n");
        System.out.println("you can check in now, you are member of hotel Intercontinental");
        System.out.println("");
        custMenu();
               
    }
    
    
    //customer Main page/menu 
    public void custMenu() throws IOException {
        
        int input= 0;
        do{
            System.out.println("\nEnter \"X\" to Exit & \"Y\" to go back to the MainMenu");
            System.out.println("\n1. Book a room with us");
            System.out.println("2. Check your booking histroy");
            System.out.println("3. Pay your bill");
            System.out.println("4. Logout"); 
            try{
                input = Integer.parseInt(scanInput(scan).trim());
                 if (!(input >= 1 && input <= 4)) {
                    System.out.println("Please enter number between 1 - 4\n");
                }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input please enter number between 1 - 4\n");
                }
            }while(!(input >= 1 && input <= 4));
            switch(input){
            case 1: 
                RoomChoice(userID);
                break;
            case 2: 
                
                checkHistroy(userID);
                break;
            case 3:
                payMehtod(userID);
                break;
            case 4:
                System.out.println("Logging out.....");
                System.out.println("Thanks for using our program");
                System.out.println("See You, Bye!");
                System.exit(0);
            }
       //end of cust menu
    }
    
    
    // prompts  user to choose room 
    public void RoomChoice(int userID) throws IOException {
        this.userID = userID;
        roomExtend = new ExtendsRoom();
        int roomType = 0;
        List<BedType> list = new ArrayList<>();
        list.addAll(Arrays.asList(BedType.values()));
        int listlength = list.size()+1;
        do{
            System.out.println("\n"); 
            System.out.println("Please choose a bed room type");
            for(int i=0; i< list.size(); i++){
                System.out.println(i+1+". "+list.get(i)+" Bed Room");       
            }
            System.out.println("7. return to the main page");
            try{
                roomType = Integer.parseInt(scanInput(scan).trim());
                if (!(roomType >= 1 && roomType <= listlength)) {
                    System.out.println("Please enter number between 1 - " + listlength + "\n");
                }
                }catch (NumberFormatException ex) {
                System.out.println("Invalid input please enter number between 1 - "+listlength+"\n");
                }
            }while(!(roomType >= 1 && roomType <= listlength));
            printRoomChoice(roomType);
    }
    
    
    //checks booking histry by userID
    public boolean checkHistroy(int userID) throws IOException {
        this.userID = userID;
        Map<Integer, Reservation> map = new HashMap<>(); 
        readReserveFile(map);
        List<Integer> keyList = new ArrayList<>();
        for ( Map.Entry<Integer, Reservation> entry: map.entrySet()) {
            if (entry.getValue().getUserID() == userID){
                keyList.add(entry.getKey());
            }
            
        }
        if(keyList.isEmpty()){
            System.out.println(userID);
            System.out.println("\nNo histroy\n");
            custMenu(); 
            return false;
        }
        else {
            for(Integer key: keyList){
            System.out.println("CheckIn date: "+map.get(key).getCheckIn()+
                            ",\nCheckOut date: " +map.get(key).getCheckOut()+
                            ",\ntotalAmount: $"+map.get(key).getTotal()+" "+map.get(key).getPay());
            }
            custMenu(); 
            return true;
        }
               
    }
    
    
    //this method is for payment 
    public void payMehtod(int userID) throws IOException {
        this.userID = userID;
        int input = 0;
        Map<Integer, Reservation> map = new HashMap<>();
        readReserveFile(map);
        do{
            System.out.println("\nHow do you wanna pay for it");
            System.out.println("1. Paypal");
            System.out.println("2. Cash");
            System.out.println("3. Return to the main page");
            try{
                input = Integer.parseInt(scanInput(scan).trim());
                 if (!(input >= 1 && input <= 3)) {
                    System.out.println("Please enter number between 1 - 3");
                }
                }catch(NumberFormatException e){
                    System.out.println("Invalid input please enter number between 1 - 3");}
            }while(!(input>=1&&input<=3));
        int totalAmount = 0;
        switch(input){
            case 1:
                for ( Entry<Integer, Reservation> entry: map.entrySet() ){
                    if (entry.getValue().getUserID() == userID){
                        if(map.get(entry.getKey()).getPay().equalsIgnoreCase("UnPaid")){
                            map.get(entry.getKey()).setPay("Payed");
                            totalAmount += map.get(entry.getKey()).getTotal();
                        }
                    }
                }
                if(totalAmount==0){
                System.out.println("yay, you have no due payment\n");
                }
                else{
                System.out.println("Total Amount Payed= $"+totalAmount);
                System.out.println("Your Bill is Payed\n");
                }
                overWriteReserveFile(map);
                break;
            case 2: 
                for ( Entry<Integer, Reservation> entry: map.entrySet() ){
                    if (entry.getValue().getUserID() == userID){
                        if(map.get(entry.getKey()).getPay().equalsIgnoreCase("UnPaid")){
                            map.get(entry.getKey()).setPay("Payed");
                            totalAmount += map.get(entry.getKey()).getTotal();
                        }
                    }
                }
                if(totalAmount==0){
                System.out.println("yay you have nothing to pay for");
                }
                else{
                System.out.println("Total Amount Payed= $"+totalAmount);
                System.out.println("Your Bill is Payed");
                }
                overWriteReserveFile(map);
                break;
            case 3:
                custMenu();
                break;
        }
        custMenu();
        
      //end of paymethod 
    }
    
    
    //checks room and print the result using the list of roomNumber Keys 
    public void checkForRoom(List<Integer> roomNumberKeys) throws IOException {
        Map<Integer, Reservation> map = new HashMap<>(); 
        readReserveFile(map);
        List<String> valueList = new ArrayList<>();
        for(Integer value: roomNumberKeys){
            System.out.println("Room-Number: "+value);     
        }
        do{
        System.out.println("Please enter room-number to continue");
        try{
        roomNumber = Integer.parseInt(scanInput(scan).trim());
        if(!roomNumberKeys.contains(roomNumber)){
            System.out.println("Please enter a number from this list "+roomNumberKeys+"\n");
        }
        }catch (NumberFormatException ex) {
                System.out.println("Invalid input please enter a number from this list "+roomNumberKeys+"\n");
                }
        }while(!roomNumberKeys.contains(roomNumber));
        for ( Map.Entry<Integer, Reservation> entry: map.entrySet()) {
                    if (entry.getValue().getRoomNumber()==roomNumber){
                    valueList.add("Room-Number :"+map.get(entry.getKey()).getRoomNumber()+
                                ", From ("+map.get(entry.getKey()).getCheckIn()+" )"+
                                ", till ("+map.get(entry.getKey()).getCheckOut()+" )");
                }
        }
        if(valueList.isEmpty()){
        }
        else{
            for(String value: valueList){
                System.out.println("\nThis room is booked during this dates");
                System.out.println(value);
            }
        }
        
        //end 
    }
    
    
    //prints users room bedtType choice 
    public void printRoomChoice(int input) throws IOException {  
        
       switch(input)
       {
            case 1:
                System.out.println("\nYou are booking a "+BedType.KING+" bed room");
                checkForRoom(roomExtend.findRoomBy(BedType.KING));
                break;
            case 2:
                System.out.println("\nYou are booking a "+BedType.QUEEN+" bed room");
                checkForRoom(roomExtend.findRoomBy(BedType.QUEEN));
                break;
            case 3:
                System.out.println("\nYou are booking a "+BedType.SINGLE+" bed room");
                checkForRoom(roomExtend.findRoomBy(BedType.SINGLE));
                break;
            case 4:
                System.out.println("\nYou are booking a "+BedType.DOUBLE+" bed room");
                checkForRoom(roomExtend.findRoomBy(BedType.DOUBLE));
                break;
            case 5:
                System.out.println("\nYou are booking a "+BedType.TRIPLE+" bed room");
                checkForRoom(roomExtend.findRoomBy(BedType.TRIPLE));
                break;
            case 6:
                System.out.println("\nYou are booking a "+BedType.QUAD+" bed room");
                checkForRoom(roomExtend.findRoomBy(BedType.QUAD));
                break;
            case 7:
                custMenu();
                break;        
        }  
       booking(input);
       
        //end
    }
    
    
     //checks availability using roomNumber and checkIn date 
    public boolean CheckAvail(int roomNumber, String checkIn) throws IOException {
         Map<Integer, Reservation> map  = new HashMap<>();
        readReserveFile(map);
        List<Integer> keyList = new ArrayList<>();
        for ( Map.Entry<Integer, Reservation> entry: map.entrySet()) {
            if (entry.getValue().getRoomNumber() == roomNumber){
                keyList.add(entry.getKey());
            }
            
        }

        for(Integer keys: keyList){
                format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                bookOut = LocalDate.parse(map.get(keys).getCheckOut(), format);
                bookIn = LocalDate.parse(checkIn, format);
                    if(compareDate(bookOut, bookIn)) {
                        return false;
                    } 
                    else {
                        System.out.println("\nSorry this room is reserved till this date: "+bookOut+" try again\n");
                        return true;  
                    }              
        } 
        return false;
        
        //end
    }
    
  
    //prompts user to input checkIn and CheckOut dates  
    public void booking(int input) throws IOException{
        
        Reservation reserve  = new Reservation();
        do{
            System.out.println("\nCan you please enter checkin date, use this format (dd/MM/yyyy) ");
            try{
                checkIn = scanInput(scan).trim(); 
                }catch(Exception e){}
            if(!isValid(checkIn)){
                System.out.println("Invalid input please enter checkin date, use this format (dd/MM/yyyy)");
            }
            else if(!reserve.convertCheckIn(getCheckIn())){
                System.out.println("Please Enter dates starting from todays date and forward");
            }
            }while(!isValid(checkIn) || !reserve.convertCheckIn(getCheckIn())|| CheckAvail(roomNumber,checkIn));    
            reserve.convertCheckIn(getCheckIn());
        
        do{
            System.out.println("Can you please enter checkout date, use this format (dd/MM/yyyy) ");
            try{  
                checkOut = scanInput(scan).trim();
                }catch(Exception e){}
             if(!isValid(checkOut)){
                System.out.println("Invalid input please enter checkin date, use this format (dd/MM/yyyy)");
            }
            else if(!reserve.convertCheckOut(getCheckOut())){
                System.out.println("Please Enter date of atleast a day after your checkIn date\n");
            }
            }while(!isValid(checkOut) || !reserve.convertCheckOut(getCheckOut()));
            reserve.convertCheckOut(getCheckOut());

        total = (int) (reserve.totalDays(getCheckIn(), getCheckOut())*roomExtend.BookingPrice(input));
        System.out.println("You have checked in your Total is = $"+total);
       
        
        //read file and put it to the map
        TreeMap<Integer, Reservation> mapID = new TreeMap<>();
        readReserveFile(mapID);
        
        
        //checks if the map is empty
        if(mapID.isEmpty()){
            reserveID = 1;  
        }
        //if its not add one to the last key OR reserve ID
        else{
            reserveID = mapID.lastKey()+1;
        }
        
        Reservation userreserve  = new Reservation(userID, roomNumber, checkIn, checkOut, total, pay);
        Map<Integer, Reservation> map = new HashMap<>();
        map.put(reserveID, userreserve);
        reserve.writeReserveToFile(map);
        
        System.out.println("Do you want to pay for it now? \"N\" to continue or any other word to exit");
        String paymentChoice = scanInput(scan).trim();
       
        if(paymentChoice.equalsIgnoreCase("N")) {
            reserve.payMehtod(userID);
        }
        custMenu();
        
        //end of booking       
    }

    
    //convertes string date to Localdate format also check if its before or after checkIn date
    public boolean convertCheckIn(String checkIn) {
        
        this.setCheckIn(checkIn);
         format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        bookIn = LocalDate.parse(checkIn, format);
        return compareTodaysDate(todaysDate,bookIn) == true;

    }
    
    
    //convertes string date to Localdate format also check if its before or after checkIn date
    public boolean convertCheckOut(String checkOut) {   
        
        this.checkOut = checkOut;
        format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        bookOut = LocalDate.parse(checkOut, format);
        if(compareDate(bookIn,bookOut) == true)
            return true;
        else {
            return false;
        }

    }
    
    
    //checks if input date is valid
    public boolean isValid(String dateStr) {
        
        String ePattern = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher match = pattern.matcher(dateStr);
        return match.matches();     
    }
    
    
    //compares checkIn with checkout  date
    public boolean compareDate(LocalDate  bookIn, LocalDate  bookOut) {
        
        if(bookIn.compareTo(bookOut) > 0) {
            return false;
        } else if(bookIn.compareTo(bookOut) < 0) {
            return true;
        } else if(bookIn.compareTo(bookOut) == 0){
            return false;
        }
        return false;  
    }
    
    
    //compares checkin date with todays date 
    public boolean compareTodaysDate(LocalDate  todaysDate, LocalDate  bookIn) {
        
        this.todaysDate =todaysDate;
        this.bookIn = bookIn;
          
        if(todaysDate.compareTo(bookIn) > 0) {
            return false;
        } else if(todaysDate.compareTo(bookIn) < 0) {
            return true;
        } else if(todaysDate.compareTo(bookIn) == 0){
            return true;
        }
        return false;
        
    }
    
    
    //checks the difference between two dates then returns amount number
    public long totalDays(String checkIn, String checkOut) {
        
        this.setCheckIn(checkIn);
        this.checkOut = checkOut;
        format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        long differ = ChronoUnit.DAYS.between(bookIn, bookOut);
        return differ;

    }
    
    
    //checks room reservation by reserveID 
    public boolean findReserveByID(int reserveID) throws IOException{
        Map<Integer, Reservation> map = new HashMap<>();
        readReserveFile(map);
        if(map.containsKey(reserveID)) {
            System.out.println(map.get(reserveID));
            return true;
        }
        else{
            System.out.println("there's no reservation by reserve ID: "+reserveID);
            return false;
        }
    }

    //checks booking  by userID
    public boolean findReserveByUserID(int userID) throws IOException {
        Map<Integer, Reservation> map = new HashMap<>(); 
        readReserveFile(map);
        List<Integer> keyList = new ArrayList<>();
        for ( Map.Entry<Integer, Reservation> entry: map.entrySet()) {
            if (entry.getValue().getUserID() == userID){
                keyList.add(entry.getKey());
            }
            
        }
        if(keyList.isEmpty()){           
            return false;
        }
        else {
            for(Integer key: keyList){
            System.out.println("CheckIn date: "+map.get(key).getCheckIn()+
                            ",CheckOut date: " +map.get(key).getCheckOut()+
                            ",totalAmount: $"+map.get(key).getTotal()+" "+map.get(key).getPay());
            }
            return true;
        }
               
    }
    //prints all reservation 
    public void printResvervation() throws IOException{
        Map<Integer, Reservation> map = new HashMap<>();
        readReserveFile(map);
        for (Map.Entry<Integer,Reservation> entry : map.entrySet())
               System.out.println("ReserveID = " + entry.getKey() +
                                ", UserID = " + entry.getValue().getUserID()+
                                ", RoomNumber = " + entry.getValue().roomNumber+
                                ", CheckIn = " + entry.getValue().getCheckIn()+
                                ", CheckOut = " + entry.getValue().getCheckOut()+
                                ", Total = " + entry.getValue().getTotal()+
                                ", PaymentStatus = " + entry.getValue().pay);
        
    }
 
    
   @Override
    public String scanInput(Scanner Scan) {
        String input = Scan.nextLine();
        if (input.equalsIgnoreCase("X")) {

            System.out.println("Thanks for using our program");
            System.exit(0);
        } else if (input.equalsIgnoreCase("Y")) {
            try {
                custMenu();
            } catch (IOException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        return input;
    }
    
    
    int getRoomNumber() {
        return roomNumber;
    }
    
       /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }
     /**
     * @return the pay
     */
    public String getPay() {
        return pay;
    }

    /**
     * @param pay the pay to set
     */
    public void setPay(String pay) {
        this.pay = pay;
    }
     /**
     * @return the checkIn
     */
    public String getCheckIn() {
        return checkIn;
    }

    
    /**
     * @param checkIn the checkIn to set
     */
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * @return the checkOut
     */
    public String getCheckOut() {
        return checkOut;
    }
    
     /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }
    
     /**
     * @return the ID
     */
    public int getreserveID() {
        return reserveID;
    }
    
    @Override
    public String toString() {
        
        String output = getUserID()+" "+getRoomNumber()+" "+getCheckIn()+" "+getCheckOut()+" "+getTotal()+" "+getPay();
        return output;
    } 
  


    //end of the class
}
