package OregonTrail;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Supplies the overrides and extra methods as required to specify that this is
 * a city and not just a general location.
 * 
 * @author Joe Runser - 21 March 2017
 * @author Skye VanAtta - 27 March 2017
 * @author Team Scarlet
 * 
 * @version 1.0
 */
public class City extends Location {
    
    /**
     * This will set the name and location of the city based on the rules given by 
     * the superclass 'Location'.
     * 
     * @param name The name of the city.
     * @param thisLoc The integer location of the city. 
     * @param nextLocOne Refers to the next possible location. 
     */
    public City(String name, int thisLoc, int[] nextLocOne) {
        
        super(name, thisLoc, nextLocOne);
        
        TYPE = 'c';
        
    }
    
    /**
     * This will set the name and location of the city based on the rules given by 
     * the superclass 'Location'.
     * 
     * @param name The name of the city.
     * @param thisLoc Numerical placeholder for the number of the current city. 
     * @param nextLocOne Refers to the next possible location.
     * @param nextLocTwo Refers to the next possible location. This is only necessary
     * when there is a choice of two locations. 
     */
    public City(String name, int thisLoc, int[] nextLocOne, int[] nextLocTwo) {
        
        super(name, thisLoc, nextLocOne, nextLocTwo);
        
        TYPE = 'c';
        
    }
    
    /**
     * A method that will override the markup vales set in the Location class.
     */
       /**
     * This method allows the wagon class to add supplies.
     * @param supply is the choice of supply the player wants 1 for oxen, 2 for
     * food, 3 for clothing, 4 for ammunition, and 5 for spare parts
     * @param quantity is how much of each supply the user wants
     * @return the purchase cost
     */
    public double buySupplies(int supply, int quantity) {
      //this array list is probs useful

      
      double[] supplyPrices = {0,20,0.2,10,2,10};
      if(supply <= supplyPrices.length){
      double price = supplyPrices[supply]*quantity;
      return price;
      }
      return 0;
    }
    
    public String nameSupplies(int supply){
      ArrayList<String> supplyStore = new ArrayList<String>();
      supplyStore.add("buffer");
      supplyStore.add("Oxen");//20
      supplyStore.add("Food");//.2
      supplyStore.add("Clothing");//10
      supplyStore.add("Ammunition");//2 for 20
      supplyStore.add("Spare parts");//10 each
      if(supply < supplyStore.size()){
      
      return supplyStore.get(supply);
      }
      return "failed";
    }
    
    
    public String independenceMissouri(){
        return "You are starting here in Independence, Missouri. Go ahead and "
                + "buy some things from this store for your trip.";
    }
    
    
    public String oregonCity(){
        return "Youv'e reached Oregon City! Nice!";
    }
    
}
