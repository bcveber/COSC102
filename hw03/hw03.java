
public class homework03{

public static void waterState(int temp, char metric){
	//Takes a celsius or farenheit degrees value and returns its current state.
	
	if (metric == 'c' || metric == 'C'){ //celsius situations
		if (temp <= 0){
			System.out.println("Solid");
		}
		else if (temp > 0 || temp < 100){
			System.out.println("Liquid");
		}
		else if (temp >= 100){
			System.out.println("Gas");
		}
	}	

	else if (metric == 'F' || metric == 'f'){ //farenheit situations
		if (temp <= 32){
			System.out.println("Solid");
			}
		else if (temp > 32 || temp < 212){
			System.out.println("Liquid");
			}
		else if (temp >= 212){
			System.out.println("Gas");
			}
			}	
	else{
		System.out.println("Invalid Metric");
	}
}
		

public static void printBox(int height, int width){
	//Takes dimensions and creates an outline of a box with stars
	if (height > 2 || width > 2){	//runs if greater than 2 for both
		for (int i = 0; i < width; i++){
			System.out.print('*'); //print the first set of stars
		}
		System.out.println();
		for (int i = 0; i < height-2; i++){ //print the first line
			System.out.print('*');
			//System.out.println(); (tester)
			for (int x = 0; x < width-2; x++){
				System.out.print(' '); //print the spaces
			}
			System.out.println('*'); //print the far line
			//System.out.println(); (tester)
			//System.out.print('*'); (tester)
	}
		for (int i = 0; i < width; i++){
			System.out.print('*'); //print the bottom stars
		}
}

	else{
		System.out.println("Invalid Dimensions");
	}
}

public static boolean palindromeNumber(int num){
	//Determines if a number is a palindrome number (same backwards and forwards)
	int number = num;
	int reversenum = 0; //set variables
	int remainder = 0;
	while (num > 0){
		remainder = num%10; //get the end number
		reversenum = (reversenum*10)+remainder; //create the reverse number
		num = num/10;
	}
	if (number == reversenum){ //test if reverse and regular are the same
		return true;
	}
	else{
		return false;
	}
}
	



















public static void main(String[] args){

		//waterState(0, 'c');
		//waterState(0, 'F');
		//waterState(0, 'x');
		//printBox(1, 1);
		//printBox(6, 3);
		//printBox(6, -14);
		//System.out.println(palindromeNumber(12345));
		//System.out.println(palindromeNumber(12321));
}
}