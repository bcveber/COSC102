  /*
  Attempts to read the mind of the user by learning user patterns and guessing the user entered color (red or blue).
  First to max_score, or 25, wins!
  */

import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class MindReader{
	
  private static final int MAX_SCORE = 25;  
  private static final int MEMORY_SIZE = 3;
  private static final String[] RED_BLUE = {"r", "b"};
  private static final String[] YES_NO = {"y", "n"};
  private static final boolean DEBUG = true;
  private int playerScore, cpuScore;
  private String cpuGuess;
  
  HashMap<String, int[]> myMap;
  String keyStorage = "";
  String mapValue = "";
  String mapValuefordebug = "";

  private void init(){

    playerScore = 0;
    cpuScore = 0;
    myMap = new HashMap<String, int[]>();
    
  }

  private static boolean validate(String input, String[] validValues){
    for (int i = 0; i < validValues.length; i++){
      if (validValues[i].equals(input))
        return true;
    }
    return false;
    
  }

  private static String promptUser(String prompt, String[] validValues)
  {
    Scanner s = new Scanner(System.in);
    System.out.println(prompt);
    while (true){
      String input = s.nextLine().toLowerCase();
      if (input != null && validate(input, validValues))
        return input;
      System.err.print("Invalid Input!  Enter: ");
      for (int i = 0; i < validValues.length - 1; i++)
        System.err.print(validValues[i] + ", ");
      System.err.println("or " + validValues[validValues.length-1] +"!");
    }
  }

  private void printScores(){
    System.out.println("\nPlayer:  " + playerScore + "\tCPU:  " + cpuScore);
    System.out.println("========================================");
    
    if (DEBUG){
    	System.out.println("Current history = " + mapValue + ", data = " + Arrays.toString(myMap.get(mapValue)));
    }
    	
    if (!DEBUG){
      System.err.println("DEBUG OUTPUT NOT IMPLEMENTED!  Example: \"rbr\" : [3, 2]");    
    }
  }
  
 public void pointAward(String userGuess, String cpuGuess){
 	 System.out.println("The CPU predicted: " + cpuGuess);
 	 if (userGuess.equals(cpuGuess)){
 	 	 System.out.println("Point goes to CPU!");
 	 	 cpuScore++;
 	 }
 	 else{
 	 	 System.out.println("Point goes to Player!");
 	 	 playerScore++;
 	 }
 }
  
  public String randomguess(){
  	  String computerGuess = "";
  	  Random rand = new Random();
  	  int numberConvert = 5;
  	  numberConvert = rand.nextInt(2);
  	  
  	  if (numberConvert == 0){
  	  	  computerGuess = "r";
  	  }
  	  
  	  if (numberConvert == 1){
  	  	  computerGuess = "b";
  	  }
  	  
  	  return computerGuess; 
  }
  
  public String cpuGuesser(HashMap<String, int[]> myMap, String mapValue){
  	  if (myMap.isEmpty()){
  	  	  return randomguess();
  	  }
  	  if (myMap.containsKey(mapValue)){
  	  		  if (myMap.get(mapValue)[0] == myMap.get(mapValue)[1]){
  	  		  	  return randomguess();
  	  		  }
  	  		  if (myMap.get(mapValue)[0] > myMap.get(mapValue)[1]){
  	  		  	  return "r";
  	  		  }
  	  		  if (myMap.get(mapValue)[0] < myMap.get(mapValue)[1]){
  	  		  	  return "b";
  	  		  }
  	  }
  	  if (!myMap.containsKey(mapValue)){
  	  		return randomguess();
  	  }
  	  return null;
}
  public void keyExistAdder(String myInput, HashMap<String, int[]> myMap, String mapValue){  			
  	  if (myInput.equals("b")){
   	   	  myMap.get(mapValue)[1]++;
   	  }
   	  if (myInput.equals("r")){
    	myMap.get(mapValue)[0]++;
  } 
  
  public void keyBeginner(HashMap<String, int[]> myMap, String mapValue, String myInput){
    			int[] redorblue = new int[2];
    			if (myInput.equals("r")){
    				redorblue[0] = 1;
    				redorblue[1] = 0;
    				myMap.put(mapValue, redorblue);
    			}
    			
    			if (myInput.equals("b")){
    				redorblue[0] = 0;
    				redorblue[1] = 1;
    				myMap.put(mapValue, redorblue);
    			}
    		}

  public void playGame(){
    init();
    int trailer = 0; 
    int last = MEMORY_SIZE;

    while (playerScore != MAX_SCORE && cpuScore != MAX_SCORE){

    	if (keyStorage.length() < (MEMORY_SIZE)){
    		String myInput = promptUser("Pick a color! Choose either (red) or (b)lue:" , RED_BLUE);
    		keyStorage = keyStorage + myInput;
    		cpuGuess = randomguess();
    		pointAward(myInput, cpuGuess);
    		printScores();
    	}
    	
    	else{
    		String myInput = promptUser("Pick a color! Choose either (red) or (b)lue:" , RED_BLUE);
    		keyStorage = keyStorage + myInput;
    		mapValue = keyStorage.substring(trailer,last);
    		System.out.println(mapValue);
    		cpuGuess = cpuGuesser(myMap, mapValue); 
	
    	if (!myMap.containsKey(mapValue) || myMap.isEmpty()){
    		keyBeginner(myMap, mapValue, myInput);
    	}

    	else{
    		keyExistAdder(myInput, myMap, mapValue);
    	}		
    		
    	pointAward(myInput, cpuGuess);
    	printScores();
    	trailer++;
    	last++;
    	}
    }
}

  public static void main(String[] args){
    String again = "y";
    MindReader mr = new MindReader();
    while (again.equals("y")){
      mr.playGame();
      again = promptUser("Would you like to play again? (y/n): ", YES_NO);
    }
    System.out.println("Thanks for playing!");
    
    
  }
  

}

