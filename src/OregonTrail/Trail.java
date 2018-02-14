package OregonTrail;
import java.util.Random;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Supplies the overrides and extra methods as required to specify that this is
 * a point on the trail and not just a general location.
 * 
 * @author Alexandra Seda - 21 March 2017
 * @author Joe Runser - 20 March 2017
 * @author Team Scarlet
 * 
 * @version 1.0
 */

public class Trail extends Location {
    
    Random rand;
    
    /**
     * This will pull the name and location from the super class location.
     * @param name A name representing the location.
     * @param thisLoc A location associated with the name.
     * @param nextLoc The next location for the wagon.
     */
    
    public Trail(String name, int thisLoc, int[] nextLoc) {
        
        super(name, thisLoc, nextLoc);
        
        TYPE = 't';
        int [] arr = new int [19];
        
        rand = new Random();
        
        
       
    }
    
    /**
     * This will enable the hunting aspect of the game. 
     * @return food_gathered Random weight of food will be generated. 
     */
    public int hunt() {
       
       int foodGathered = 0;
       int[] arr = null;
       
       int randomNumber = rand.nextInt(20) + 1;
        
       /* Areas with large amounts of food, multiplied by a large constant of
          food gathered. */
       
       if(arr[1] <= 13){
            foodGathered = 20;
            foodGathered = (foodGathered)*(randomNumber); 
       }
       
       /* Areas with small amounts of food, multiplied by a small constant of
          food gathered. */
       
         if(arr[1] >= 13){
            foodGathered = 10;
            foodGathered = (foodGathered)*(randomNumber); 
       }
       
       // Random amount of food chosen based on location.
       return foodGathered;
       
    }
    
    public int[] getNextLocOne() {
        
        return nextLocOne;
        
    }
    
}
