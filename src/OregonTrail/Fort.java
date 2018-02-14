/*
 * Fort Class for Oregon Trail package
 */
package OregonTrail;

import java.util.ArrayList;

/**
 * This class makes fort objects for the Oregon Trail game. It is 
 * a subclass of the Location superclass. 
 * @author Skye VanAtta - 27 March 2017
 * @author Team Scarlet
 */
public class Fort extends Location {
    
    private final float priceMod;
    
    String name;
    String person;
    String speech;
           
    
    /**
     * Constructor for Fort Class that follows rules given in Location superclass.
     * 
     * @param name of Fort
     * @param thisLoc - a point object with location of fort
     * @param nextLocOne Refers to the next possible location.
     * @param priceMod The modifier for the prices as the wagon gets further from 
     * Independence.
     * @param person The name of the person that player encounters at this fort.
     * @param speech The speech that the person will give you.
     */
    public Fort(String name, int thisLoc, int[] nextLocOne, float priceMod, 
            String person, String speech) {
        
        super(name, thisLoc, nextLocOne);
        this.priceMod = priceMod;
        this.name = name;
        this.person = person;
        this.speech = speech;
        
        TYPE = 'f';
        
    }
    
    /**
     * Constructor for Fort Class that follows rules given in Location superclass.
     * 
     * @param name of Fort
     * @param thisLoc - a point object with location of fort
     * @param nextLocOne Refers to the next possible location.
     * @param nextLocTwo Refers to the next possible location. This is only necessary
     * when there is a choice of two locations.
     * @param priceMod The modifier for the prices as the wagon gets further from 
     * Independence.
     * @param person The name of the person that player encounters at this fort.
     * @param speech The speech that the person will give you.
     */
    public Fort(String name, int thisLoc, int[] nextLocOne, int[] nextLocTwo, 
            float priceMod, String person, String speech) {
        
        super(name, thisLoc, nextLocOne, nextLocTwo);
        this.priceMod = priceMod;
        this.name = name;
        this.person = person;
        this.speech = speech;
        
        TYPE = 'f';
        
        
    }
    
    /**
     * This method allows the wagon class to add supplies when player is at a Fort.
     * @param supply is the choice of supply the player wants 1 for oxen, 2 for
     * food, 3 for clothing, 4 for ammunition, and 5 for spare parts
     * @param quantity is how much of each supply the user wants
     * @return the purchase cost
     */
    public double buySupplies(int supply, int quantity) {
      
      ArrayList<String> supplyStore = new ArrayList<String>();
      supplyStore.add("buffer");
      supplyStore.add("Oxen");//20
      supplyStore.add("Food");//.2
      supplyStore.add("Clothing");//10
      supplyStore.add("Ammunition");//2 for 20
      supplyStore.add("Spare parts");//10 each
      
      double[] supplyPrices = {0,20,0.2,10,2,10};
      
      double price = supplyPrices[supply]*quantity*priceMod;
      return price;
              
      
    }
    
    /**
     * This will allow the user to speak with people she encounters along the
     * trail. 
     * @return String speakWithPeople Is the text coming from the person who
     * is speaking with the player, which will be based on the player's location.
     */
    public String speakWithPeople(){
        
        String speak = person + " says to you: ''" + speech + "''";
        return speak;
        
    }// done probs
    
    public int[] getNextLocOne() {
        
        return nextLocOne;
        
    }
    
}
