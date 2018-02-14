package OregonTrail;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class will create a wagon with people and supplies on it. It will keep track
 * of the supplies, speed, rationing, and the player occupation.
 * 
 * @author Joe Runser - 21 March 2017
 * @author Team Scarlet
 * 
 * @version 1.0
 */
public class Wagon {
    
    // Pace vales
    /** Integer value of the stopped pace. */
    public final static int PACE_STOPPED     = 0;
    /** Integer value of the steady pace. */
    public final static int PACE_STEADY      = 1;
    /** Integer value of the strenuous pace. */
    public final static int PACE_STRENUOUS   = 2;
    /** Integer value of the grueling pace. */
    public final static int PACE_GRUELING    = 3;
    
    // Ration values
    /** Integer value of the filling ration. */
    public final static int RATION_FILLING   = 3;
    /** Integer value of the meager ration. */
    public final static int RATION_MEAGER    = 2;
    /** Integer value of the bare bones ration. */
    public final static int RATION_BAREBONE  = 1;
    
    // Ocupation values
    /** Integer value of the banker occupation. */
    public final static int OCC_BANKER       = 1;
    /** Integer value of the carpenter occupation. */
    public final static int OCC_CARPENTER    = 2;
    /** Integer value of the farmer occupation. */
    public final static int OCC_FARMER       = 3;
    
    // Start money for each occupation
    private final static int MONEY_BANKER    = 1600;
    private final static int MONEY_CARPENTER = 800;
    private final static int MONEY_FARMER    = 400;
    
    // Maximum number of people
    private final static int MAX_PEOPLE      = 5;
    
    private int[] currentLocation; // Current location of the wagon.
    private int[] nextLocation;
    private ArrayList<Person> people; // People riding the wagon.
    
    // Hold the amount of each respective item.
    private double money;
    private int oxen;
    //private int oxTongues;
    private int bullets;
    //private int axles;
    //private int wheels;
    private int clothes;
    private int food;
    private int spareparts;
    
    private int pace; // Hold the current pace.
    private int rationing; // Hold the current rationing.
    private final int occupation; // Holds the occupation.
    private boolean wagon;
    
    /**
     * Constructor for the wagon class. In this initializer it will make sure that 
     * the player has a wagon, put the people onto the wagon, give the wagon people
     * a occupation (and money), and set the start location.
     * 
     * @param people An array list containing the people that will be on the wagon.
     * @param occupation An integer representing the occupation of the owner of the
     * wagon.
     */
    public Wagon(ArrayList<Person> people, int occupation) {
        
        currentLocation = new int[3];
        
        this.wagon = true; // Will remain true as long as there is a wagon.
        this.people = people;
        this.occupation = occupation;
        currentLocation[0] = 0; // Idependence
        currentLocation[1] = 0;
        currentLocation[2] = 0;
        
        // Set the money based on the selected occupation.
        switch(occupation) {
            case(OCC_BANKER): this.money = (double) MONEY_BANKER; break;
            case(OCC_CARPENTER): this.money = (double) MONEY_CARPENTER; break;
            case(OCC_FARMER): this.money = (double) MONEY_FARMER; break;
        }
        
    }
    
    /**
     * This will allow a reset of the people on the wagon, this may not be a necessary
     * method in the finished product. It will only allow a maximum of five people
     * on the wagon at any one time.
     * 
     * @param people An array list containing the people that should be added to 
     * the wagon. It will ignore anyone over the fifth element.
     */
    public void setPeople(ArrayList<Person> people) {
        
        if(people.size() <= 5) {
            this.people = people;
        } else if(people.size() > 5) {
            for(int i = 0; i < 5; i++) {
                this.people.add(people.get(i));
            }
        }
        
    }
    /**
     * Sets current money
     * @param newmoney 
     */
    public void setMoney(double newmoney){
        this.money = newmoney;
    }
    /**
     * This will set the current location of the wagon.
     * 
     * @param location A Point representing the location of the wagon.
     */
    public void setLocation(int[] location) {
        
        currentLocation = location;
        
    }
    
    public void setNextLocation(int[] location){
        
        this.nextLocation = location;
        
    }
    
    public int[] getLocation(){
        
        return currentLocation;
        
    }
    
    /**
     * Setter for the number of oxen.
     * 
     * @param oxen represents an integer number of oxen.
     */
    public void setNumOxen(int oxen) {
        
        this.oxen = oxen;
        
    }
    
    /**
     * Setter for the number of bullets.
     * 
     * @param bullets represents an integer number of bullets.
     */
    public void setNumBullet(int bullets) {
        
        this.bullets = bullets;
        
    }
    
    /**
     * Setter for the number of spare parts.
     * 
     * @param spareparts represents an integer number of spare parts.
     */
    public void setNumSpareParts(int spareparts) {
        
        this.spareparts = spareparts;
        
    }
    
    /**
     * Setter for the number of oxTongues.
     * 
     * @param oxTongues represents an integer number of oxTongues.
     */
/*    public void setNumOxTongue(int oxTongues) {
        
        this.oxTongues = oxTongues;
        
    }*/
    
    /**
     * Setter for the number of axles.
     * 
     * @param axles represents an integer number of axles.
     */
/*   public void setNumAxles(int axles) {
        
        this.axles = axles;
        
   } */
    
    /**
     * Setter for the number of wheels.
     * 
     * @param wheels represents an integer number of wheels.
     */
/*    public void setNumWheels(int wheels) {
        
        this.wheels = wheels;
   } */
    
    /**
     * Setter for the number of clothes.
     * 
     * @param clothes represents an integer number of clothes.
     */
    public void setNumClothes(int clothes) {
        
        this.clothes = clothes;
        
    }
    
    /**
     * Setter for the number of food.
     * 
     * @param food represents an integer number of food.
     */
    public void setNumFood(int food) {
        
        this.food = food;
        
    }
    
    /**
     * Setter for the pace.
     * 
     * @param pace represents an integer for the pace. 
     */
    public void setPace(int pace) {
        
        if(pace >= PACE_STEADY && pace <= PACE_GRUELING){
            if(oxen >= 6) { 
            
                this.pace = pace; 
            } else if(oxen < 6 && oxen >2){
                if(pace == PACE_GRUELING){   
                    this.pace = PACE_STRENUOUS;
                }
                if(pace <= PACE_STRENUOUS){
                    this.pace = pace;
                }
            }else if(oxen <= 2 && oxen > 0){
                this.pace = PACE_STEADY;
            }else{
                this.pace = PACE_STOPPED;
            }
            
            } 
    }
    
    /**
     * Setter for the rationing.
     * 
     * @param ration represents an integer representing how much each person eats.
     */
    public void setRations(int ration) {
        
        if(ration >= RATION_BAREBONE && ration <= RATION_FILLING) {
            this.rationing = ration;
        }
        
    }
    
    /**
     * This is called if the wagon breaks completely. If this happens there is no
     * more wagon.
     */
    public void setWagonFalse() {
        
        this.wagon = false;
        
    }
    
    /**
     * Will removed the specified person from the wagon.
     * 
     * @param person is the person to be removed from the people array list.
     */
    public void removePerson(Person person) {
        
        if(food == 0){
            
        }
        // remove person
        
    }
    
    /**
     * Will feed the remaining people on the wagon based on the the rationing and 
     * if there is enough food left.
     */
    public void consumeFood() {
        
        int eaters = this.getNumPeople();
        int consumedDaily = eaters*rationing;
        if(food>0){
            this.food = food - consumedDaily;
        }
        this.setNumFood(food);
                
    }
    
    /**
     * Getter for the number of oxen.
     * 
     * @return an integer containing the number of remaining oxen.
     */
    public int getNumOxen() {
        
        return this.oxen;
        
    }
    
    /**
     * Getter for the number of bullets.
     * 
     * @return an integer containing the number of remaining bullets.
     */
    public int getNumBullet() {
        
        return this.bullets;
        
    }
    
    /**
     * Getter for the number of spare parts.
     * 
     * @return an integer containing the number of spare parts
     */
    public int getNumSpareParts() {
        
        return this.spareparts;
        
    }
    
    
    /**
     * Getter for the number of ox tongues.
     * 
     * @return an integer containing the number of remaining ox tongues.
     */
  /*  public int getNumOxTongue() {
        
        return this.oxTongues;
        
    } */
    
    /**
     * Getter for the number of axles.
     * 
     * @return an integer containing the number of remaining axles.
     */
  /*  public int getNumAxles() {
        
        return this.axles;
        
    }*/
    
    /**
     * Getter for the number of wheels.
     * 
     * @return an integer containing the number of remaining wheels.
     */
   /* public int getNumWheels() {
        
        return this.wheels;
    }*/
    
    /**
     * Getter for the number of clothes.
     * 
     * @return an integer containing the number of remaining clothes.
     */
    public int getNumClothes() {
        
        return this.clothes;
        
    }
    
    /**
     * Getter for the amount of food.
     * 
     * @return an integer containing the weight of the remaining food.
     */
    public int getNumFood() {
        
        return this.food;
        
    }
    
    /**
     * Getter for if there is a wagon or not.
     * 
     * @return an boolean value that returns true if the wagon is still usable and
     * false otherwise.
     */
    public boolean getWagon() {
        
        return this.wagon;
        
    }
    
    /**
     * Getter for the number of people.
     * 
     * @return an integer containing the number of remaining people.
     */
    public int getNumPeople() {
        
        return people.size();
        
    }
    
    /**
     * Getter for the average health of the people on the wagon.
     * 
     * @return An integer value between 1 and 4 representing the average health.
     * The number 4 is the best and 1 is the worst, anything below that is death.
     */
    public float getAverageHealth() {
        
        float avg = 0;
        
        // Sum of everyones health
        for (Person person : people) {
            avg += person.getHealth();
        }
        
        // Divide by the number of people to get the average
        avg = avg / people.size();
        
        return avg;
        
    }
    
    /*public void setAVGHealth(){
       if(pace == PACE_GRUELING){
           float healthTemp =  (float) (this.getAverageHealth()*.8); 
           person.setHealth(healthTemp); 
        }
        
    }*/
    
    /**
     * Getter for the amount of money.
     * 
     * @return an integer containing the amount of remaining money.
     */
    public double getNumMoney() {
        
        return money;
        
    }
    
    /**
     * Getter for the current rationing scheme.
     * 
     * @return an integer containing representing the current rationing scheme. A
     * 1 represents the lowest form of rationing (bare bones) and a 3 represents
     * the highest form of rationing (filling).
     */
    public int getRationing() {
        
        return rationing;
        
    }
    
    /**
     * Getter for the current pace.
     * 
     * @return An integer containing representing the current rationing scheme. A
     * 0 represents stopped and a 3 represents the fastest pace (grueling).
     */
    public int getPace() {
        
        return pace;
        
    }
    
    
    
    /**
     * Getter for the occupation of the adventurer.
     * 
     * @return This will return an integer that represents the adventurers occupation.
     * It will 1 for Farmer, 2 for carpenter, and 3 for banker.
     */
    public int getOccupation() {
        
        return occupation;
         
    }
    
    public int travel() {
        
        switch(pace) {
            case PACE_GRUELING:
                currentLocation[2] += 15;
                break;
            case PACE_STRENUOUS:
                currentLocation[2] += 10;
                break;
            case PACE_STEADY:
                currentLocation[2] += 5;
                break;
            case PACE_STOPPED:
                currentLocation[2] += 0;
                break;
            default:
                break;
        }
        
        return currentLocation[2];
    }

}

            
       