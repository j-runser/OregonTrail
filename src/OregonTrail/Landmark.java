package OregonTrail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Supplies the overrides and extra methods as required to specify that this is
 * a landmark and not just a general location.
 * 
 * @author Joe Runser - 21 March 2017
 * @author Team Scarlet
 * 
 * @version 1.0
 */
public class Landmark extends Location {
    
    String person;
    String speech;
    
    
    /**
     * This will set the name and location of the river based on the rules given by 
     * the superclass 'Location'.
     * 
     * @param name The name of the landmark.
     * @param thisLoc Integer value telling the software which location this is.
     * @param nextLocOne The location of the next point.
     * @param nextLocTwo The location of the next point, if there is split in the 
     * trail. It will be null if not.
     * @param person
     * @param speech
     */
    public Landmark(String name, int thisLoc, int[] nextLocOne, int[] nextLocTwo,
            String person, String speech) {
        
        super(name, thisLoc, nextLocOne, nextLocTwo);
        this.person = person;
        this.speech = speech;
        
        TYPE = 'l';
        
    }
    
    /**
     * This will allow the user to speak with people they encounter along the at
     * the current landmark
     * 
     * @return String speakWithPeople Is the string of text from the person who
     * is speaking with the user along the trail.
     */
    public String speakWithPeople(){
        
        String speak = person + " says to you: ''" + speech + "''";
        return speak;
        
    }// done probs
    
    public int[] getNextLocOne() {
        
        return nextLocOne;
        
    }
    
}
