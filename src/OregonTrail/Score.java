package OregonTrail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The purpose of this class is to tabulate the total score and the scores for each 
 * of the resources.
 * 
 * @author Joe Runser - 20 March 2017
 * @author Team Scarlet
 * 
 * @version 1.0
 */
public class Score {
    
    // Health multipliers
    private final static int MULT_GOOD      = 500;
    private final static int MULT_FAIR      = 400;
    private final static int MULT_POOR      = 300;
    private final static int MULT_VPOOR     = 200;
    
    // What the wagon is worth 
    private final static int MULT_WAGON     = 50;
    
    // Material multipliers
    private final static int MULT_OXEN      = 4;
    private final static int MULT_PART      = 2;
    private final static int MULT_CLOTHES   = 2;
    
    // Material divisors
    private final static int DIVS_BULLET    = 50;
    private final static int DIVS_FOOD      = 1;
    private final static double DIVS_MONEY     = 1;
    
    // Reads from the wagon class
    private Wagon wagon;
    
    // The score placeholders
    private int grossScore;
    private int totalScore;
    private int peopleScore;
    private int wagonScore;
    private int oxenScore;
    private int partsScore;
    private int clothesScore;
    private int bulletsScore;
    private int foodScore;
    private double moneyScore;
    
    /**
     * Constructor for the Score class. It will set all of the individual scores 
     * to 0. The wagon class also needs to be initialized but it will be overridden
     * when the final score is tallied.
     * 
     * @param wagon The wagon that will make it  to the end of the Oregon Trail.
     */
    public Score(Wagon wagon) {
        
        this.wagon = wagon;
                
        grossScore = 0;
        totalScore = 0;
        peopleScore = 0;
        wagonScore = 0;
        oxenScore = 0;
        partsScore = 0;
        clothesScore = 0;
        bulletsScore = 0;
        foodScore = 0;
        moneyScore = 0;
                        
    }
    
    /**
     * It takes the wagon and determines what the score is based on what materials 
     * remain. The materials that are tallied include people, wagon, oxen, parts, 
     * clothes, bullets, food, and money. All of these are tallied into individual
     * scores, and from those individual scores there are two aggregate scores, gross 
     * and total. The gross score is the sum of all the individual scores without 
     * the occupation modifier. The total score is the sum of all the individual 
     * scores with the occupation modifier.
     * 
     * @param wagon The wagon that has made it to the end of the Oregon Trail.
     */
    public void tallyScore(Wagon wagon) {
        
        this.wagon = wagon;
        
        setPeopleScore();
        setWagonScore();
        setOxenScore();
        setPartsScore();
        setClothesScore();
        setBulletScore();
        setFoodScore();
        setMoneyScore();
        
        grossScore = peopleScore;
        grossScore += wagonScore;
        grossScore += oxenScore;
        grossScore += partsScore;
        grossScore += clothesScore;
        grossScore += bulletsScore;
        grossScore += foodScore;
        grossScore += moneyScore;
        
        totalScore = grossScore * wagon.getOccupation();
        
    } 
    
    /**
     * Getter for the total score (after the occupation modifier).
     * 
     * @return The total game score, after the occupation modifier.
     */
    public int getTotalScore() {
        
        return totalScore;
        
    }
    
    /**
     * Getter for the gross score (before the occupation modifier).
     * 
     * @return The gross game score, before the occupation modifier.
     */
    public int getGrossScore() {
        
        return grossScore;
        
    }
    
    /**
     * Getter for the individual score, money.
     * 
     * @return The individual score for the money.
     */
    public double getMoneyScore() {
        
        return moneyScore;
        
    }
    
    /**
     * Getter for the individual score, food.
     * 
     * @return The individual score for the food.
     */
    public int getFoodScore() {
        
        return foodScore;
        
    }
    
    /**
     * Getter for the individual score, bullets.
     * 
     * @return The individual score for the bullets.
     */
    public int getBulletScore() {
        
        return bulletsScore;
        
    }
    
    /**
     * Getter for the individual score, clothes.
     * 
     * @return The individual score for the clothes.
     */
    public int getClothesScore() {
        
        return clothesScore;
        
    }
    
    /**
     * Getter for the individual score, wagon parts.
     * 
     * @return The individual score for the wagon parts.
     */
    public int getPartsScore() {
        
        return partsScore;
        
    }
    
    /**
     * Getter for the individual score, oxen.
     * 
     * @return The individual score for the oxen.
     */
    public int getOxenScore() {
        
        return oxenScore;
        
    }
    
    
    /**
     * Getter for the individual score, wagon itself.
     * 
     * @return The individual score for the wagon itself.
     */
    public int getWagonScore() {
        
        return wagonScore;
        
    }
    
    /**
     * Getter for the individual score, each living person.
     * 
     * @return The individual score for the each living person.
     */
    public int getPeopleScore() {
        
        return peopleScore;
        
    }
    
    // Return score from the clothes, 1 point for every 5.
    private void setMoneyScore() {
        
        this.moneyScore = wagon.getNumMoney() / DIVS_MONEY;
        
    }
    
    // Return score from the clothes, 1 points for every 25.
    private void setFoodScore() {
        
        this.foodScore = wagon.getNumFood() / DIVS_FOOD;
        
    }
    
    // Return score from the clothes, 1 points for every 50.
    private void setBulletScore() {
        
        this.bulletsScore = wagon.getNumBullet() / DIVS_BULLET;
        
    }
    
    // Return score from the clothes, 2 points each.
    private void setClothesScore() {
        
        this.clothesScore = MULT_CLOTHES * wagon.getNumClothes();
        
    }
    
    // Return the score from the extra parts, 2 points each.
    private void setPartsScore() {
        
        //int parts = 0;
        int parts = wagon.getNumSpareParts();
        //parts += wagon.getNumAxles();
        //parts += wagon.getNumOxTongue();
        //parts += wagon.getNumWheels();
        
        this.partsScore = parts * MULT_PART;
        
    }
    
    // Will return 4 points for each oxen
    private void setOxenScore() {
        
        this.oxenScore = MULT_OXEN * wagon.getNumOxen();
        
    }
    
    // Will return points if there is still a wagon
    private void setWagonScore() {
        
        if(wagon.getWagon()) { this.wagonScore = MULT_WAGON; }
        
    }
    
    // Get points based on the how many people survived and the health condition
    // of those people
    private void setPeopleScore() {
        
        int people = wagon.getNumPeople();
        float health = wagon.getAverageHealth();
        int healthInt = (int)health;
        switch (healthInt) {
            case 1: this.peopleScore = people * MULT_VPOOR; break;
            case 2: this.peopleScore = people * MULT_POOR;  break;
            case 3: this.peopleScore = people * MULT_FAIR;  break;
            case 4: this.peopleScore = people * MULT_GOOD;  break;
        }
        
    }
    
}