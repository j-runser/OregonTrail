package OregonTrail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is meant to create a person object. The methods in this class will
 * make sure that the person eats, is wearing the proper clothing, and keep track
 * of the persons health, along with anything else that may effect someone on the
 * Oregon Trail.
 * 
 * @author Joe Runser - 21 March 2017
 * @author Team Scarlet
 * 
 * @version 1.0
 */
public class Person {
    
    private final static int HEALTH_GOOD = 4;
    private final static int HEALTH_FAIR = 3;
    private final static int HEALTH_POOR = 2;
    private final static int HEALTH_VPOOR = 1;
    
    private final String name;
    private float health;
    private int[] conditions;
    private boolean hasEaten;
    private boolean clothesDry;
    private boolean wearingClothes;
    
    /**
     * This will create a person with a name, good health, fed, wearing dry clothing.
     * 
     * @param name The name of the person to be added.
     */
    public Person(String name) {
        
        this.name = name;
        this.health = HEALTH_GOOD;
        this.hasEaten = true; // Assuming they do not forget about breakfast.
        this.wearingClothes = true;
        this.clothesDry = true;
        
    }
    
    /**
     * This will have a person eat, if there is not sufficient food for that person
     * they eat what remains and if that is enough to meet at least meager, they 
     * will have eaten. If there is no food left then they will be set to false.
     */
    public void eat() {
        
        // consume a rationed amount of food
        
    }
    
    /**
     * Will look at the current state of the person's clothing and if it sufficient
     * for the current weather the health should stay the same and if they are not
     * then the persons health will be effected.
     */
    public void sufficientClothes() {
        
        // will check the clothesDry and wearingClothes conditions to see if they
        // are sufficient for the current situation. It will get the weather from
        // the respective location class.
        // Use either the health bar or the health modifier to change the state
        
    }
    
    /**
     * Getter for the persons name.
     * 
     * @return This will return a String containing the name of the person.
     */
    public String getName() {
        
        return name;
        
    }
    
    /**
     * Getter for the persons health modifier (good, fair, poor, very poor).
     * 
     * @return This will return an integer representing what the current health
     * modifier is (4 for good, 3 for fair, 2 for poor, and 1 for very poor).
     */
    public float getHealth() {
        
        return health;
        
    }
    public void setHealth(float health){
        this.health = health;
    }
    
    /**
     * Getter for the persons health conditions.
     * 
     * @return This will return an integer array containing the current conditions
     * of the person. These are conditions such as cholera or broken arm.
     */
    public int[] getConditions() {
        
        return conditions;
        
    }
    
}

