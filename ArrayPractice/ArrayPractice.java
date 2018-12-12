public class ArrayPractice{

public static int[] reverseArray(int[] ogarray){ // take parameters
	int[] newarray = new int[ogarray.length]; //set up a new array
	for (int i=0; i<ogarray.length; i++){ //run through the old array
		newarray [i] = ogarray[ogarray.length-1-i]; //enter in the old array values into the new array
	}
	return newarray; //return reversed array
}

public static int[] merge(int[] onearray, int[] twoarray){ //take parameters
	int[] mergearray = new int[onearray.length + twoarray.length]; //set up the new array
	int i=0;
	int x=0; //set loop variables
	int merge=0;
	while (i < onearray.length && x < twoarray.length){ //set while loop
		if (onearray[i] < twoarray[x]){ //compare array values higher/lower
			mergearray[merge] = onearray[i]; 
			i++; //add to onearray if number entered into new array
		}								//sort into appropriate array based on strength of [i] or [x]
		else{
			mergearray[merge] = twoarray[x];
			x++; //add to twoarray if number entered into new array
		}
		merge++; //add to new array to prepare for next number entry 
	}
	while (x < twoarray.length){ //run until array length exhausted
		mergearray[merge] = twoarray[x]; //add [x] values into new array
		merge++;
		x++;
	}
	while (i < onearray.length){ //run until array length exhausted
		mergearray[merge] = onearray[i]; //add [i] values into new array
		merge++;
		i++;
	}
	return mergearray; //return the new merged array


}

public static int[] findBetween(int[] anarray, int bound1, int bound2){
	int count = 0; //set first while loop variables
	int y = 0;
	while (y < anarray.length){ //run the while loop until arraylength hits
		if (anarray[y] >= bound1 && anarray[y] <= bound2){ //check to see if within bound
			count++; //add to the counter to find the new array length
		}
		y++;
	}
	
	int[] finalarray = new int[count]; //take value from counter above to get final length
	int i = 0; //set 2nd while loop variables
	int x = 0;
	while (i < anarray.length){ //run the while loop until arraylength hits
		if (anarray[i] >= bound1 && anarray[i] <= bound2){ //filter through the numbers to see if within bound
			finalarray[x] = anarray[i]; //if within bound, add to the new array
			i++; //add to counter variables
			x++;
		}
		else{
			i++;
		}
	} 
	return finalarray; //return the new filtered array
}
	

public static void main(String[] args){
	
	int[] array1 = {7, 4, -12, 8};
	int[] array1reversed = reverseArray(array1);
	for (int i:array1reversed){	//test to make sure the output is reversed
		System.out.println(i);
	}
	int[] array2 = {3};
	int[] array2reversed = reverseArray(array2);
	for (int i:array2reversed){	//test to make sure the output is reversed
		System.out.println(i);
	}

	System.out.println(" ");	
	int[] onearray = {3, 5, 6};
	int[] twoarray = {1, 2, 4};
	int[] mergedarray = merge(onearray, twoarray); //test and print out the merged array
	for (int i:mergedarray){
		System.out.println(i);
	}
	
	System.out.println(" ");
	int[] onearrayone = {1,7};
	int[] twoarraytwo = {2,4,5,8};
	int[] mergedarray1 = merge(onearrayone, twoarraytwo); //test and print out the merged array
	for (int i:mergedarray1){
		System.out.println(i);
	}
	
	int[] array1b = {4, 12, 7, 8, 2, 9, 5};
	int bound1 = 4;
	int bound2 = 10;
	int[] array1filter = findBetween(array1b, bound1, bound2);
	for (int i:array1filter){	//test to see if the array has been filtered
		System.out.println(i);
	}
	
	int[] array2b = {8, 8, 10, 10, 8};
	int bound11 = 7;
	int bound22 = 9;
	int[] array1filter1 = findBetween(array2b, bound11, bound22);
	for (int i:array1filter1){	//test to see if the array has been filtered
		System.out.println(i);
	}
	
	int[] array3 = {1, 2, 3, 4, 5};
	int bound111 = 9728;
	int bound222 = 9921;
	int[] array1filter2 = findBetween(array3, bound111, bound222);
	for (int i:array1filter2){	//test to see if the array has been filtered
		System.out.println(i);
	}
}
}