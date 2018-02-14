/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OregonTrail;

/**
 * Supplies the overrides and extra methods as required to specify that this is
 * a river and not just a general location.
 * 
 * @author Skye VanAtta - March 25 2017
 * @author Team Scarlet
 * 
 * @version 2.0
 */
public final class River extends Location {
    
    /**
     * Each subclass of Location has an character identifier. This one is 'r'.
     */
    
    
    String name;
    int width;
    int depth;
    String person;
    String speech;
    
    private final int DEPTH_RANGE = 20;
    
    private double widthDepthRatio;
    
    private final java.util.Random rndGenerator = new java.util.Random();
    
    /**
     * This will retrieve the name and location of the river from the super 
     * class location.
     * 
     * @param name The name of the river.
     * @param thisLoc The point location of the river.
     * @param nextLocOne The location of the next point.
     * @param nextLocTwo The location of the next point, if there is split in the 
     * trail. It will be null if not.
     * @param ratio The width/depth ratio of given river.
     * @param person The name of the person that player encounters at this river.
     * @param speech The speech that the person will give you. 
     */
    /*
    Edited: Joe -  I had to add some more things to pass in as to store information 
    about the next location(s).
    */
    public River(String name, int thisLoc, int[] nextLocOne, int[] nextLocTwo,
                 double ratio, String person, String speech) {
    
        super(name, thisLoc, nextLocOne, nextLocTwo);
        this.person = person;
        this.speech = speech;
        this.name = name;
        
        river_dimensions(ratio);
        
        TYPE = 'r';
        
        
    } //done probably
    
    /**
     * This will generate river dimensions. The ratio based on ratio calculated 
     * by playing 1990 version of 
     * Oregon Trail once through. 
     * 
     * @param ratio - the ratio that is supplied when object is 
     * created from database
     */
    public void river_dimensions(double ratio){
        
        this.widthDepthRatio = ratio;
        int river_depth = rndGenerator.nextInt(DEPTH_RANGE);
        //A random river depth value in the range of 1-20ft will be generated.
        this.depth = river_depth;
        this.width = (int) widthDepthRatio * depth;//w/d * d = w
        
        if(name.equals("Columbia River")){
            this.depth = 20;
            this.width = 5280*83; //83 miles to float down, ensure it takes 
            //several days to get down this river. 
        }
        
        if(name.equals("Barlow Toll Road")){
            this.depth = 0;
            this.width = 0;
        }
        
    } //done probs
    
    /**
     * This will pass in the user integer choice of deciding what action to take
     * based upon the river depth.
     * 
     * @param choice The integer choice of the action the user wishes to take.
     * @return an integer representing the outcome of their choice. 0 = successful
     * river crossing, 1 = crossed river but at a loss, 2 = waited for conditions 
     * to improve.
     */
    public int continue_on_trail(int choice){
        boolean success = true;
        int chances = 0; 
        
        if(name.equals("Barlow Toll Road")){
            if(choice == 1){ //yes they would like to pay toll
                return 0;
            } else { //no they do not want to pay toll
                return 2;
            }
        }
        
        switch (choice) {
            case 1:
                //ford river
                if(depth <= 2){
                    chances = rndGenerator.nextInt(20);
                } else if(depth > 2 && depth <= 5){
                    chances = rndGenerator.nextInt(10);
                } else if(depth > 5 && depth <= 10){
                    chances = rndGenerator.nextInt(5);
                } else if(depth > 10 && depth <= 20){
                    chances = rndGenerator.nextInt(2);
                }   if(chances == 1){
                    success = false;
                }   break;
            case 2:
                //caulk wagon and float across
                if(depth <= 2){
                    chances = rndGenerator.nextInt(5);//floating in 2 feet? doubt it
                } else if(depth > 2 && depth <= 8){
                    chances = rndGenerator.nextInt(30);
                } else if(depth > 8 && depth <= 17){
                    chances = rndGenerator.nextInt(20);
                } else if(depth > 17 && depth <= 20){
                    chances = rndGenerator.nextInt(5);
                }   if(chances == 1){
                    success = false;
                }   break;
            case 3:
                //take a ferry
                if(depth <= 2){
                    chances = rndGenerator.nextInt(30);
                } else if(depth > 2 && depth <= 8){
                    chances = rndGenerator.nextInt(100);
                } else if(depth > 8 && depth <= 17){
                    chances = rndGenerator.nextInt(100);
                } else if(depth > 17 && depth <= 20){
                    chances = rndGenerator.nextInt(70);
                }   if(chances == 1){
                    success = false;
                }   break;
            case 4:
                //wait for conditions to be improved
                this.river_dimensions(widthDepthRatio);  
                return 2;
            default:
                return -1; // Used to show illegal command.
        }
        
        if(success){ 
            return 0;
        } else {
            return 1;
        }
        
        /*
        Case 1: ford river
        Case 2: caulk wagon and float across
        Case 3: Take the ferry
        Case 4: Wait to see if conditions improve
        Maybe Case 5: Get more information
        */
    } //done probs
    
    /**
     * This will allow the user to speak with people she encounters along the
     * trail. 
     * @return String SpeakWithPeople Is the string of text that contains the 
     * and speech from the person encountered.
     */
    public String speakWithPeople(){
        
        String speak = person + " says to you: ''" + speech + "''";
        return speak;
        
    }// done probs
    
    /**
     * This method calculates the number of days it took to cross the river. 
     * @return the number of days taken to cross river. 
     */
    public int days_cost() {
        
        int daysCost = width/100; 
        return daysCost;
        
    }//done
    
     public int get_width() {
        
        return width;
        
    } //done
     
    public int get_depth() {
        
        return depth;
        
    }//done
    
    public int[] getNextLocOne() {
        
        return nextLocOne;
        
    }
}
