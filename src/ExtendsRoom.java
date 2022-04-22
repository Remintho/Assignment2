
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author User
 */
public class ExtendsRoom extends CentralFile  {
    private int roomNumber;
    
    
    //get bookingPrice using roomNumber and return it 
    public int BookingPrice(int roomNumber) throws IOException {
        Map<Integer, Room> roommap  = new HashMap<>();
        readRoomFile(roommap);
        int bookingPrice = (int) roommap.get(roomNumber).getBookingPrice();
        return bookingPrice;
    }
            
    
    // print all rooms List 
    public void printRoomsList() throws IOException {
        Map<Integer, Room> map = new HashMap<>();
        readRoomFile(map);
        map.entrySet().forEach(entry -> {
            System.out.println("RoomNumber = "+entry.getKey()+
                    ", BedType = "+entry.getValue().getBedType()+
                    ", BookingPrice = $"+entry.getValue().getBookingPrice());
        });
    }
    
    // addRoom 
    public void addRoom(int roomNumber, BedType type, int bookingPrice) throws IOException{
        Map<Integer, Room> map = new HashMap<>();
        readRoomFile(map);
        room = new Room(type, bookingPrice);
        map.put(roomNumber, room);
        overWriteRoomFile(map);
    }
    
    //romveRoom 
    public void romveRoom(int roomNumber) throws IOException{
        Map<Integer, Room> map = new HashMap<>();
        readRoomFile(map);
        map.remove(roomNumber);
        overWriteRoomFile(map);
    }
    
    //find room by roomNumber 
    public boolean findRoomBy(int roomNumber) throws IOException{
        Map<Integer, Room> map  = new HashMap<>();
        readRoomFile(map);
        if(map.containsKey(roomNumber)){
            System.out.println("Bed-Type: "+map.get(roomNumber).getBedType()+", Booking Price: $"+map.get(roomNumber).getBookingPrice());
            return true;
        }
        else{
            System.out.println("The room number is not valid or it is out of range");
            return false;
        }
    }
    
    //find keys that matches given BedType then return list of the keys  
    public List<Integer> findRoomBy(BedType type) throws IOException{
        Map<Integer, Room> map  = new HashMap<>();
        readRoomFile(map);
        List<Integer> keysList = new ArrayList<>();
        for ( Map.Entry<Integer, Room> entry: map.entrySet()) {
            if (entry.getValue().getBedType().equals(type)){
                keysList.add(entry.getKey());
            }   
        }
        if(keysList.isEmpty()){
            System.out.println("Sorry the room bedtype is not in our file");
            return keysList;
        }
        else {
            return keysList;
        }
    }
    
      /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }
   
    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    //end of this class
}
