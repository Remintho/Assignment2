package files;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Room {
    
    private int bookingPrice;
    private BedType bedType;
     
    Room() {}
    public  Room(BedType type, int bookingPrice )
    {
        this.bedType = type;
        this.bookingPrice = bookingPrice;
    }
  
  

    /**
     * @return the bookingPrice
     */
    public int getBookingPrice() {
        return bookingPrice;
    }

    /**
     * @param bookingPrice the bookingPrice to set
     */
    public void setBookingPrice(int bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    /**
     * @return the bedType
     */
    public BedType getBedType() {
        return bedType;
    }

    /**
     * @param bedType the bedType to set
     */
    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }
    
    
    @Override
     public String toString()
    {
        String output = getBedType()+" "+getBookingPrice();
        return output;
    }

  //end of the class
}
