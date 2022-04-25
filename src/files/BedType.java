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
public enum BedType {
  KING("King"),
  QUEEN("Queen"), 
  SINGLE("Single"),
  DOUBLE("Double"),
  TRIPLE("Triple"),
  QUAD("Quad");
  

  private final String literate;

  BedType(String literate) {
    this.literate = literate;
  }

  public String literate() {
    return this.literate;
  }
}