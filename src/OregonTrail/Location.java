/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OregonTrail;

/**
 * This is a superclass for all of the different types of locations. These types
 * are City, Fort, Landmark, River, and Trail. Each one of these subclasses will 
 * be overridden as to add the required functionality.
 * 
 * @author Joe Runser - 21 March 2017
 * @author Team Scarlet
 * 
 * @version 1.0
 */
public class Location {
    
    public final String name;
    
    public final int number; // the location number
    
    /*
        Each of these will store the next avalable location. If there is only one location
        nextLocTwo will be initialized to null.
    
        nextLoc*[0] - The type of the next location
        nextLoc*[1] - The number of the next location
        nextLoc*[2] - The distance to the next location
    */
    protected final int[] nextLocOne;
    protected final int[] nextLocTwo;
    
    public static char TYPE;
    
    /* All of the following are still undecited on how they will be implimented */
    // animal types
    // animal numbers(concentration)
    // custom object map;
    // custom object calendar;
    // weather
    
    /**
     * The constructor for the location class will give each location a name and 
     * an x,y coordinate. Anything else that is required can be added in the subclasses.
     * 
     * @param name A String containing the name of the location.
     * @param number A numerical placeholder for this location.
     * @param nextLocOne Refers to the next possible location.
     */
    public Location(String name, int number, int[] nextLocOne) {
        
        this.name = name;
        this.number = number;
        this.nextLocOne = nextLocOne;
        this.nextLocTwo = null;
    
    }
    
    /**
     * The constructor for the location class will give each location a name and 
     * an x,y coordinate. Anything else that is required can be added in the subclasses.
     * 
     * @param name A String containing the name of the location.
     * @param number A numerical placeholder for this location.
     * @param nextLocOne Refers to the next possible location.
     * @param nextLocTwo Refers to the next possible location. This is only necessary
     * when there is a choice of two locations.
     */
    public Location(String name, int number, int[] nextLocOne, int[] nextLocTwo) {
        
        this.name = name;
        this.number = number;
        this.nextLocOne = nextLocOne;
        this.nextLocTwo = nextLocTwo;
    
    }
    
    /**
     * This will have the wagon continue on the trail. The distance traveled will be
     * based on the weather conditions, random events, pace, number of oxen, and 
     * pace setting.
     */
    public void continueOnTrail() {
        
    }
    
    
    /** 
     * Will show the map of the Oregon Trail and the current location of the wagon.
     */
    // This should probobly only be in the user infterface or in the wagon class. 
    public void getMap() {
        
    }
    
    /**
     * Allows the player to stop the adventure for a specified number of days.
     * 
     * @param days An integer value representing the number of days the player would
     * like to stop.
     */
    public void stopToRest(int days) {
        
    }
    
    /**
     * Give the player a change to attempt to trade with the surrounding people.
     * This will gather more details based on where the player is when they try to
     * trade.
     */
    public void attemptToTrade() {
        
    }
    
    /**
     * Set they type of animals based on the current location and weather.
     */
    public void setAnimals() {
        
    }
    
    /**
     * Set they type of weather based on the current location and date.
     */
    public void setWeather() {
        
    }
    
    /**
     * Getter for the animals at the current location.
     */
    public void getAnimals() {
        
    }
    
    /* 
    Might be able to make these helper fucntions and mix them into a nice big
    update everything fucntion.
    */
    
    /**
     * Getter for the weather at the current location.
     */
    public void getWeather() {
        
    }
    
    /**
     * Update the map based on the wagons current location.
     */
    public void updateMap() {
        
    }
    
    /**
     * Update the date on the game calendar.
     */
    public void updateCalendar() {
        
    }
    
    public int getNumber() {
        
        return number;
        
    }
    
    public char getType() {
        
        return TYPE;
        
    }
    
}
