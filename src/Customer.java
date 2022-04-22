
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Customer {
    
    private String FName;
    private String LName;
    private int Age;
    private String Email;
    private String PhNumber;
    
    /**
     * @param FName
     * @param LName
     * @param Age
     * @param Email
     * @param PhNumber
     */
    public Customer(String FName, String LName, int Age, String Email, String PhNumber)
    {
        this.FName = FName;
        this.LName = LName;
        this.Age = Age;
        this.Email = Email;
        this.PhNumber = PhNumber;
    }

    
    public String getFName() {
        return FName;
    }

    /**
     * @param FName the FName to set
     */
    public void setFName(String FName) {
        this.FName = FName;
    }

    /**
     * @return the LName
     */
    public String getLName() {
        return LName;
    }

    /**
     * @param LName the LName to set
     */
    public void setLName(String LName) {
        this.LName = LName;
    }

    /**
     * @return the Age
     */
    public int getAge() {
        return Age;
    }

    /**
     * @param Age the Age to set
     */
    public void setAge(int Age) {
        this.Age = Age;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the PhNumber
     */
    public String getPhNumber() {
        return PhNumber;
    }

    /**
     * @param PhNumber the PhNumber to set
     */
    public void setPhNumber(String PhNumber) {
        this.PhNumber = PhNumber;
    }
    
    
    @Override
    public String toString()
    {
        String output = getFName()+" "+getLName()+" "+getAge()+" "+getEmail()+" "+getPhNumber();
        return output;
    }
  
}
