  /*
  A radix sort program.
  */

import java.util.*;
import java.io.*;

public class RadixSort{
	
	
	public static void main(String[] args){
		
		try{
			Scanner sc = new Scanner(new File(args[0]));
			MyQueue myList = new MyQueue();
			int maxLength = 0;
	
			while (sc.hasNextLine()){
				
				String fileLine = sc.nextLine();
				myList.add(fileLine);
				if (fileLine.length() > maxLength){
					maxLength = fileLine.length();
				}
			}
			MyQueue[] myArray = new MyQueue[27];
			while(maxLength >= 0){
				while (myList.size() > 0){
					String listRemove = myList.remove();
					
					if (listRemove.length() <= maxLength){
							
						if (myArray[0] == null){
							myArray[0] = new MyQueue();
						}
							
						myArray[0].add(listRemove);
					}
					else{
						int index = (listRemove.charAt(maxLength) - 96);
							
						if (myArray[index] == null){
							myArray[index] = new MyQueue();
						}
							
						myArray[index].add(listRemove);
					}
				}
			
					for (int y = 0; y < 27; y++){
						myList.concat(myArray[y]);
					}
					maxLength--;
			}

			PrintStream ps = new PrintStream(new File(args[1]));
			
			while (myList.size() > 0){
				String printSort = myList.remove();
				ps.println(printSort);
			}
			
		ps.close();
		sc.close();	
		
			} catch(FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
	}
}
