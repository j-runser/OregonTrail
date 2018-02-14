/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OregonTrail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author Joe Runser - 25 March 2017
 * @author Team 
 */
public class GameMaster implements java.io.Serializable {
    
    private static final int CITY = 1;
    private static final int FORT = 2;
    private static final int LANDMARK = 3;
    private static final int RIVER = 4;
    
    private String input;
    private String outputGame;
    private String outputConsol;
    private String outputCommand;
    private String game;
    private String error;
    
    private int occupation;
    private int gameState;
    
    private final ArrayList<City> cities;
    private final ArrayList<Fort> forts;
    private final ArrayList<Landmark> landmarks;
    private final ArrayList<River> rivers;
    private final ArrayList<Person> people;
    private Wagon wagon;
    
    private int supplySelected = 1;
    private int quantityDesired = 1;
    
    private Calendar date = new GregorianCalendar(1846, 3, 20);
    private    int month;
    private    int day;
    private    int year;
    //SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
    
    private int[] targetLoc;
        
    public GameMaster() {
        
        cities = new ArrayList();
        forts = new ArrayList();
        landmarks = new ArrayList();
        rivers = new ArrayList();
        people = new ArrayList();
        
        targetLoc = new int[] {4, 1, 0};
        
        gameState = 0;
        occupation = -1;
        error = "Did not work.\n";
        
        buildLocationObjects();
        
    }
    
    public String runUserInput(String input) {
        
        outputConsol = "User: " + input;
        
        switch (gameState) {
            case 0: // stet occ
                // validate game state commands
                outputGame = set_occupation(input);
                gameState = 1;
                return outputConsol;
            case 1: // add people
                addPerson(input);
                if(people.size() == 5){ //have to have 5 in wagon
                    gameState = 2;
                    setupWagon(people);
                }
                return outputConsol;
            case 2: // buying things in independence
                if(input != null && wagon != null){
                    try{supplySelected = Integer.parseInt(input);}
                    catch(NumberFormatException e){
                        System.out.println(e);
                    }
                    if(supplySelected == 1 || supplySelected == 2 || supplySelected == 3 ||
                            supplySelected == 4||supplySelected == 5) {
                        gameState = 3;
                    } 
                    else {
                        gameState = 4;
                    }}  
                break;
            case 3: // buying things in independence
                if(input!= null && wagon != null){
                    quantityDesired = Integer.parseInt(input);
                    if(!cities.isEmpty()) {
                        wagon.setRations(3);
                        double leftOverCash = wagon.getNumMoney() - 
                                cities.get(0).buySupplies(supplySelected, quantityDesired);
                        if(leftOverCash > 0.0){
                            switch (supplySelected) {
                                case 1:
                                    wagon.setNumOxen(quantityDesired);
                                    break;
                                case 2:
                                    wagon.setNumFood(quantityDesired);
                                    break;
                                case 3:
                                    wagon.setNumClothes(quantityDesired);
                                    break;
                                case 4:
                                    wagon.setNumBullet(quantityDesired);
                                    break;
                                case 5:
                                    wagon.setNumSpareParts(quantityDesired);
                                    break;
                                default:
                                    break;
                            }
                            wagon.setMoney(leftOverCash);
                        }
                        gameState = 2;
                        
                        return "\nYou have $" + leftOverCash + " left. \n";
                    }}  break;
            case 4: // It is needed
                gameState = 5;
                wagon.setNextLocation(rivers.get(0).getNextLocOne());
                return "success!";
            case 5: // on the trail
                return isAtLocation();
                // if location changes, gameState = respective number
            case 6: // adjusting travel settings
                gameState = 5;
                return "";
            case 7: //fort
                return "";
            case 8: // river
                return atRiver();
            case 9: // landmark
                outputGame = atLandmark(input);
                //landmark
                return "";
            case 10: // Oregon, City
            default:
                break;
        }
        return null;
        
    }
    
    // Return gameFrameText based on current game state
    public String gameFrameText() {
        
        switch (gameState) {
            case 0:
                
                return "What is your occupation?\n"
                        + "1. Banker\n"
                        + "2. Carpender\n"
                        + "3. Farmer\n";
            case 1:
                int peopleToAdd = 5 - people.size();
                return "Add " + peopleToAdd + " people to your wagon. \n";
            case 2:
                if(wagon != null){
                    return "You are starting here in Independence, Missouri. Go ahead and \n" +
                            "buy some things from this store for your trip.\n" +
                            "You have $" + wagon.getNumMoney() +"\n \n" +
                            "Which type of supply do you want? \n" +
                            " 1. Oxen - $10\n"
                            + " 2. Food - $0.20/lb \n"
                            + " 3. Clothing - $10/set\n"
                            + " 4. Ammunition - $2/20 bullets \n"
                            + " 5. Spare parts - $10 \n"
                            + " 6. to leave store. \n";
                }
                break;
            case 3:
                return "How many do you want? \n";
            case 4:
                wagon.setLocation(targetLoc);
                return "Time to start your trip. Good luck. \n";
            case 5:
                //on the trail.
                try{ Thread.sleep(100);}
                catch(InterruptedException e){
                    //whatever
                }

                wagon.consumeFood();
                date.add(Calendar.DATE, 1);
                month = date.get(Calendar.MONTH);
                day = date.get(Calendar.DAY_OF_MONTH);
                year = date.get(Calendar.YEAR);
                
                return "Type pause to adjust travel settings. \n" +
                        "Date: " + month + "-" + day + "-" + year + "\n"+
                        "Health: " + wagon.getAverageHealth() + "\n" +
                        "Food: " + wagon.getNumFood() + "\n" +
                        "Miles to next place: " + wagon.getLocation()[2] + "\n\n";
            case 6:
                //adjusting travel settings
                return "not yet sorry\n";
            case 7:
                //fort
                wagon.consumeFood();
                date.add(Calendar.DATE, 1);
                return "You have arrived at Fort  \n";
            case 8:
                //river
                wagon.consumeFood();
                date.add(Calendar.DATE, 1);
                return atRiver();
            case 9:
                //landmark
                wagon.consumeFood(); 
                date.add(Calendar.DATE, 1);
                return "You have arrived at a landmark \n";
            default:
                break;
        }
        
        return error;
        
    }
    
    private String set_occupation(String occupation) {
        
        switch (occupation) {
            case "1":
                this.occupation = Integer.parseInt(occupation);
                return "Consol: You are the Banker!\n"
                        + "Consol: You have $2000 to spend. \n";
            case "2":
                this.occupation = Integer.parseInt(occupation);
                return "Consol: You are the Carpender!\n"
                        + "Consol: You have $1000 to spend. \n";
            case "3":
                this.occupation = Integer.parseInt(occupation);
                return "Consol: You are the Farmer!\n"
                        + "Consol: You have $500 to spend. \n";
            default:
                this.occupation = -1;
                return "Error: Please select a valid occupation. \n";
        }
        
    }
    
    // Checks for legal commands
    private boolean isLegalCommand(String cmd) {
        
        boolean isLegal = false;
        
        // For if the current location is a city.
        if(cities.get(targetLoc[1]).getType() == 'c') {
            for(int i = 1; i <= 9; i++) {
                if(cmd.equals(Integer.toString(i))) { return true; }
            }
            return false;
        } else if(forts.get(targetLoc[1]).getType() == 'f') {
            
        }
        
        return false;
        
    }
    
    // Builds the entire datastructure for containing the location objects. They
    // are being stored in an array list in accordance with thier numbers.
    private boolean buildLocationObjects() {
        
        File inputFile;
        FileReader fileReader;
        BufferedReader fileBuffer;
        
        // Used to hold each individual line from the text file.
        ArrayList<String> lines = new ArrayList();
        
        // Set up the file and the scanner
        try { 
            // open file
            inputFile = new File("files/textNew.txt");
            fileReader = new FileReader(inputFile);
            fileBuffer = new BufferedReader(fileReader);
            String inputLine = null;
            
            // Reads in the file line by line
            while(true) {
                inputLine = fileBuffer.readLine();
                if(inputLine == null) { break; }
                
                // puts each line of the inputFile into a String stored in an arrayList
                lines.add(inputLine);
            }
        } 
        catch(FileNotFoundException e) {
            System.out.println(e);
            return false;
        }
        catch(IOException e) {
            System.out.print(e);
            return false; 
        }
        
        // Look at the locations class to see how these should be stored.
        int[] nextLocOne = new int[3];
        int[] nextLocTwo = new int[3];
        
        for(int i = 0; i < lines.size(); i++) {
            
            // This will split the commands into thier respective lines strings.
            String[] command = lines.get(i).split(";");
            /*
                This will be used to hold each individual command, the commands are
                explained below.
                command[0] - type
                command[1] - location number
                command[2] - name
                command[3] - distance(s)
                command[4] - person
                command[5] - speech
                command[6] - width/depth ratio || price modifier
            */
            
            // This will split the points and them make them if there is a split
            // in the path.
            String tmpStr = command[3];
            String[] nextLoc = tmpStr.split(":");
            
            // This will divide those point into thier respective parts and then
            // reassemble them as necessary.
            String[] nextPoint = nextLoc[0].split(",");
            
            nextLocOne[0] = Integer.parseInt(nextPoint[0]);
            nextLocOne[1] = Integer.parseInt(nextPoint[1]);
            nextLocOne[2] = Integer.parseInt(nextPoint[2]);
            // Only required if the location has two next points
            if(nextLoc.length == 2) { 
                nextPoint = nextLoc[1].split(",");
                nextLocTwo[0] = Integer.parseInt(nextPoint[0]);
                nextLocTwo[1] = Integer.parseInt(nextPoint[1]);
                nextLocTwo[2] = Integer.parseInt(nextPoint[2]);
            }
            
            switch (command[0]) {
                case "c": // City(String name, int thisLoc, char[] nextLocOne, char[] nextLocTwo)
                    City city = new City(command[2], Integer.parseInt(command[1]),
                            nextLocOne, nextLocTwo);
                    cities.add(city);
                    break;
                case "r": // River(String name, int thisLoc, char[] nextLocOne, char[] nextLocTwo, 
                          //       double ratio, String person, String speech)
                    River river = new River(command[2], Integer.parseInt(command[1]),
                            nextLocOne, nextLocTwo, Double.parseDouble(command[6]),
                            command[4], command[5]);
                    rivers.add(river);
                    break;
                case "f": // Fort(String name, int thisLoc, int[] nextLocOne, int[] nextLocTwo, 
                          //      float priceMod, String person, String speech)
                    Fort fort = new Fort(command[2], Integer.parseInt(command[1]),
                            nextLocOne, nextLocTwo, Float.parseFloat(command[6]),
                            command[4], command[5]);
                    forts.add(fort);
                    break;
                case "l": // Landmark(String name, int thisLoc, int[] nextLocOne, 
                          //          int[] nextLocTwo, String person, String speech)
                    Landmark landmark = new Landmark(command[2], Integer.parseInt(command[1]),
                            nextLocOne, nextLocTwo, command[4], command[5]);
                    landmarks.add(landmark);
                    break;
                default:
                    System.out.println("Failed to find type." + command[0]);
                    return false;
            } // end switch
        } // end for i
        return true;
        
    } // end buildLocationObjects()
    
    // Will add a person to the people riding the wagon assuming that the user does
    // not try to add more than the maximum.
    public boolean addPerson(String name) {
        
        if(people.size() <= 5) {
            Person person = new Person(name);
            people.add(person);
            return true;
        }
        
        return false;
        
    }
    
    private boolean setupWagon(ArrayList<Person> people) {
        
        if(people.isEmpty()) {
            error = "Please put at least one person on the wagon.\n";
            return false;
        }
        else if(people.size() <= 5) {
            wagon = new Wagon(people, occupation);
            return true;
        }
        else if(people.size() > 5) {
            ArrayList<Person> peopleTmp = new ArrayList();
            for(int i = 0; i < 5; i++) {
                peopleTmp.add(people.get(i));
            }
            wagon = new Wagon(peopleTmp, occupation);
            return true;
        }
        
        return false;
        
    }
    
    private String atLandmark(String input) {
        
        int[] tmpLoc = wagon.getLocation();
        
        if(landmarks.get(0).number == tmpLoc[2]) {
            targetLoc = landmarks.get(0).getNextLocOne();
        } else if(landmarks.get(1).number == tmpLoc[2]) {
            targetLoc = landmarks.get(1).getNextLocOne();
        } else if(landmarks.get(2).number == tmpLoc[2]) {
            targetLoc = landmarks.get(2).getNextLocOne();
        } else if(landmarks.get(3).number == tmpLoc[2]) {
            targetLoc = landmarks.get(3).getNextLocOne();
        } else if(landmarks.get(4).number == tmpLoc[2]) {
            targetLoc = landmarks.get(4).getNextLocOne();
        } else if(landmarks.get(5).number == tmpLoc[2]) {
            targetLoc = landmarks.get(5).getNextLocOne();
        }
        
        return "";
        
    }

    
    private void setGameState(int place) {
        
        switch (place) {
            case 1://fort
                gameState = 7;
                break;
            case 2://river
                gameState = 8;
                break;
            case 3://landmark
                gameState = 9;
                break;
            case 4://trail
                gameState = 5;
                break;
            default:
                break;
        }
    }
    
    private String atRiver() {
        
        String tmpStr = "Error";
        
        if(rivers.get(0).number == 1) {
            tmpStr = rivers.get(0).name;
            wagon.setNextLocation(rivers.get(0).nextLocOne);
            targetLoc = rivers.get(0).nextLocOne;
        } else if(rivers.get(1).number == 2) {
            tmpStr = rivers.get(1).name;
            wagon.setNextLocation(rivers.get(1).nextLocOne);
            targetLoc = rivers.get(1).nextLocOne;
        } else if(rivers.get(2).number == 9) {
            tmpStr = rivers.get(2).name;
            wagon.setNextLocation(rivers.get(2).nextLocOne);
            targetLoc = rivers.get(2).nextLocOne;
        } else if(rivers.get(3).number == 12) {
            tmpStr = rivers.get(3).name;
            wagon.setNextLocation(rivers.get(3).nextLocOne);
            targetLoc = rivers.get(3).nextLocOne;
        } else if(rivers.get(4).number == 17) {
            tmpStr = rivers.get(4).name;
            wagon.setNextLocation(rivers.get(4).nextLocOne);
            targetLoc = rivers.get(4).nextLocOne;
        } else if(rivers.get(5).number == 18) {
            tmpStr = rivers.get(5).name;
            wagon.setNextLocation(rivers.get(5).nextLocOne);
            targetLoc = rivers.get(5).nextLocOne;
        }
        
        gameState = 5;
        return tmpStr;
        
    }
    private String atFort(String input) {
         String tmpStr = "Error";
        
        if(rivers.get(0).number == 1) {
            tmpStr = rivers.get(0).name;
            wagon.setNextLocation(rivers.get(0).nextLocOne);
            targetLoc = rivers.get(0).nextLocOne;
        } else if(rivers.get(1).number == 2) {
            tmpStr = rivers.get(1).name;
            wagon.setNextLocation(rivers.get(1).nextLocOne);
            targetLoc = rivers.get(1).nextLocOne;
        } else if(rivers.get(2).number == 9) {
            tmpStr = rivers.get(2).name;
            wagon.setNextLocation(rivers.get(2).nextLocOne);
            targetLoc = rivers.get(2).nextLocOne;
        } else if(rivers.get(3).number == 12) {
            tmpStr = rivers.get(3).name;
            wagon.setNextLocation(rivers.get(3).nextLocOne);
            targetLoc = rivers.get(3).nextLocOne;
        } else if(rivers.get(4).number == 17) {
            tmpStr = rivers.get(4).name;
            wagon.setNextLocation(rivers.get(4).nextLocOne);
            targetLoc = rivers.get(4).nextLocOne;
        } else if(rivers.get(5).number == 18) {
            tmpStr = rivers.get(5).name;
            wagon.setNextLocation(rivers.get(5).nextLocOne);
            targetLoc = rivers.get(5).nextLocOne;
        }
        
        gameState = 5;
        return tmpStr;
    }
    
    private String isAtLocation() {
        wagon.travel();
        int tmp = wagon.getLocation()[2];
        
                if(tmp > 288 && tmp < 320){
                    this.setGameState(1);
                return forts.get(0).name;
                }else if(tmp > 620 && tmp <= 660){
                    this.setGameState(1);
                return forts.get(1).name;
                }else if(tmp > 969 && tmp <= 1009){
                    this.setGameState(1);
                return forts.get(2).name;
                }else if(tmp > 1332 && tmp <= 1372){
                    this.setGameState(1);
                return forts.get(3).name;
                }else if(tmp > 1628 && tmp <= 1668){
                    this.setGameState(1);
                return forts.get(4).name;
                }else if(tmp > 1843 && tmp <= 1883){
                    this.setGameState(1);
                return forts.get(5).name;
                }
                
            else if(tmp > 82 && tmp <= 122){
                    this.setGameState(2);
                return rivers.get(0).name;
                }else if(tmp > 165 && tmp <= 205){
                    this.setGameState(2);
                return rivers.get(1).name;
                }else if(tmp > 1131 && tmp <= 1171){
                    this.setGameState(2);
                return rivers.get(2).name;
                }else if(tmp > 1523 && tmp <= 1163){
                    this.setGameState(2);
                return rivers.get(3).name;
                }else if(tmp > 1981 && tmp <= 2021){
                    this.setGameState(2);
                return rivers.get(4).name;
                }else if(tmp > 2081 && tmp <= 2121){
                    this.setGameState(2);
                return rivers.get(5).name;
                }
                
            else if(tmp > 534 && tmp <= 574){
                    this.setGameState(3);
                return landmarks.get(0).name;
                }else if(tmp > 810 && tmp <= 850){
                    this.setGameState(3);
                return landmarks.get(1).name;
                }else if(tmp > 912 && tmp <= 952){
                    this.setGameState(3);
                return landmarks.get(2).name;
                }else if(tmp > 1275 && tmp <= 1315){
                    this.setGameState(3);
                return landmarks.get(3).name;
                }else if(tmp > 1788 && tmp <= 1828){
                    this.setGameState(3);
                return landmarks.get(4).name;
                }else if(tmp > 1963 && tmp <= 1990){
                    this.setGameState(3);
                return landmarks.get(5).name;}
            else{
                 this.setGameState(4); 
                 return "On the trail";
                } 
            
        
        
        
    }
    
}
