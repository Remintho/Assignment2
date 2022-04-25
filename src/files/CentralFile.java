package files;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */

public class CentralFile {
    private final String roomFilePath = "./resources/room.txt";
    private final String loginFilePath = "./resources/login.txt";
    private final String regFilePath = "./resources/customer.txt";
    private final String reservefilePath = "./resources/reservation.txt";
    Room room = new Room();
    
    
    
    
    //////////////////ExttendsRoom read and write//////////////////////////////////////
    
    
    //read file content and put it to a Map for ExttendsRoom
    public void readRoomFile(Map<Integer, Room> map) throws IOException {
       
        //checks if the file is created or not then it creates file if theres no file like the given name
        File File = new File(roomFilePath);
        File.createNewFile();
        Path path = Paths.get(roomFilePath);
        Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                String[] data = line.split(" ");
                int roomnumber = Integer.parseInt(data[0]);     
                BedType type = BedType.valueOf(data[1]);
                int bookingPrice = Integer.parseInt(data[2]);
                room = new Room(type, bookingPrice);
                map.put(roomnumber, room);
            });
      //end of readfile
    }
    
    //overWriteFile file contents for ExttendsRoom
    public void overWriteRoomFile(Map<Integer, Room> map) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(roomFilePath, false)))) {
        if (map.size() < 1)
            return;
        Set<Map.Entry<Integer, Room>> mapEntry = map.entrySet();
        mapEntry.forEach(mapEn -> {
            int RoomNumber = mapEn.getKey();
            room = mapEn.getValue();           
                pw.println(RoomNumber
                        + " " + room.getBedType()
                        + " " + room.getBookingPrice());
            });

         pw.flush();
        } 
        
       //end of over write file
    }
    
    //writes the map contents to the given file for ExttendsRoom
    public void writeRoomToFile(Map<Integer, Room> map) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(roomFilePath, true)))) {
        if (map.size() < 1)
            return;
        Set<Map.Entry<Integer, Room>> mapEntry = map.entrySet();
        mapEntry.forEach(mapEn -> {
            int roomNumber = mapEn.getKey();
            room = mapEn.getValue();
                pw.println(roomNumber
                        + " " + room.getBedType()
                        + " " + room.getBookingPrice());
            });

         pw.flush();
        }  
        //end of write to file
    }
    
    
    //////////////////Login read and write//////////////////////////////////////
    
    
    //read file content and put it to a Map for Login
    public void readLoginFile(Map<String, LoginInfo> map) throws IOException  {
        
        //checks if the file is created or not then it creates file if theres no file like the given name
        File File = new File(loginFilePath);
        File.createNewFile();
        
        Path path = Paths.get(loginFilePath);
         Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                String[] data = line.split(" ");
                String user_name = data[0];
                String pass_word = data[1];
                int Id = Integer.parseInt(data[2]);
                LoginInfo login = new LoginInfo(pass_word, Id);
                map.put(user_name, login);
            });
      //end of read file
    }  

    //writes the map contents to the given file for Login 
    public void writeLoginToFile(Map<String,LoginInfo> map) throws IOException {
    try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(loginFilePath, true)))) {
        if (map.size() < 1)
            return;
        Set<Map.Entry<String, LoginInfo>> mapEntry = map.entrySet();
        for (Map.Entry<String, LoginInfo> mapEn :
                mapEntry) {
            String userName = mapEn.getKey();
            LoginInfo login = mapEn.getValue();
                pw.println(userName
                                        + " " + login.getPassword()
                                        + " " + login.getID());

        }

         pw.flush();
        }     
      //end of write to file
    }
    
    
    //////////////////Registration read and write///////////////////////////////
    
    
    //writes the map contents to the given file for Registraion
    public void readRegFile(Map<Integer, Customer> map) throws IOException  {
        
        //checks if the file is created or not then it creates file if theres no file like the given name
        File File = new File(regFilePath);
        File.createNewFile();
        Path path = Paths.get(regFilePath);
         Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                String[] data = line.split(" ");
                int key = Integer.parseInt(data[0]);
                String fName = data[1];
                String lName = data[2];
                int age = Integer.parseInt(data[3]);
                String email = data[4];
                String phNumber = data[5];
                Customer cust = new Customer(fName, lName, age, email, phNumber);
                map.put(key, cust);
            });
       //end of read file
    }        
   
    //writes the map contents to the given file for Registraion 
    public void writeRegToFile(Map<Integer, Customer> map) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(regFilePath, true)))) {
        if (map.size() < 1)
            return;
        Set<Map.Entry<Integer, Customer>> mapEntry = map.entrySet();
        for (Map.Entry<Integer, Customer> mapEn :
                mapEntry) {
            int userID = mapEn.getKey();
            Customer cust = mapEn.getValue();
                pw.println(userID
                                        + " " + cust.getFName()
                                        + " " + cust.getLName()
                                        + " " + cust.getAge()
                                        + " " + cust.getEmail()
                                        + " " + cust.getPhNumber());
            
        }
        
         pw.flush();
         
        }   
       //end of write to file
    }
    
    
    //////////////////Reservation read and write///////////////////////////////
    
    
    //writes the map contents to the given file for Reservation
    public void readReserveFile(Map<Integer, Reservation> map) throws IOException  {
        //checks if the file is created or not then it creates file if theres no file like the given name
        File File = new File(reservefilePath);
        File.createNewFile();
        Path path = Paths.get(reservefilePath);
        Stream<String> lines = Files.lines(path);
            lines.forEach(line -> {
                String[] data = line.trim().split(" ");
                int reserveId = Integer.parseInt(data[0]);
                int userId = Integer.parseInt(data[1]);
                int roomnumber = Integer.parseInt(data[2]);
                String userCheckIn = data[3];
                String userCheckOut = data[4];
                int userTotal = Integer.parseInt(data[5]);
                String userPay = data[6];
                Reservation reserve = new Reservation(userId, roomnumber, userCheckIn, userCheckOut, userTotal, userPay);
                map.put(reserveId, reserve);
            });
       //end of read file
    }        

    //overWriteFile file contents for Reservation 
    public void overWriteReserveFile(Map<Integer, Reservation> map) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(reservefilePath, false)))) {
        if (map.size() < 1)
            return;
        Set<Map.Entry<Integer, Reservation>> mapEntry = map.entrySet();
        mapEntry.forEach(mapEn -> {
            int reserveId = mapEn.getKey();
            Reservation reserve = mapEn.getValue();           
                pw.println(reserveId
                                    + " " + reserve.getUserID()
                                    + " " + reserve.getRoomNumber()
                                    + " " + reserve.getCheckIn()
                                    + " " + reserve.getCheckOut()
                                    + " " + reserve.getTotal()
                                    + " " + reserve.getPay());
            });

         pw.flush();
        }  
        //end of over write file
    }
    
    //writes the map contents to the given file for Reservation 
    public void writeReserveToFile(Map<Integer, Reservation> map) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(reservefilePath, true)))) {
        if (map.size() < 1)
            return;
        Set<Map.Entry<Integer, Reservation>> mapEntry = map.entrySet();
        mapEntry.forEach(mapEn -> {
            int reserveId = mapEn.getKey();
            Reservation reserve = mapEn.getValue();
                pw.println(reserveId
                                    + " " + reserve.getUserID()
                                    + " " + reserve.getRoomNumber()
                                    + " " + reserve.getCheckIn()
                                    + " " + reserve.getCheckOut()
                                    + " " + reserve.getTotal()
                                    + " " + reserve.getPay());
            });

         pw.flush();
        } 
       //end of write file 
    }
    
    
   //end of the class 
}
