import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class DataSet{
	private String[] fieldNames;
	private ArrayList<String[]> data;
  
  
  public DataSet(String[] fieldNames, ArrayList<String[]> data){
  	  this.fieldNames = fieldNames;
  	  this.data = data;
    
  }
  
  //Gets the index of the field that is being sorted
  public int getFieldIndex(String field){
  	  for (int i = 0; i < fieldNames.length; i++){
  	  	  if (fieldNames[i] == field){ //searches until the field is found
  	  	  	  return i;
  	  	  }
  	  }
  	  return -1;
  }
  	  	  

  
  public ArrayList<String[]> applyFilters(ArrayList<Filter> filters){
    
    ArrayList<String[]> dataCopy = new ArrayList<String[]>(data); //create a copy of data to preseve orginal indexing of data
    
    for (int i = 0; i < filters.size(); i++){ //runs through each filter given
    	Filter myFilter = filters.get(i); //creates a new filter
		    	
    	Filter.FilterAction myAction = myFilter.getAction();
    	Filter.FilterType myType = myFilter.getFilterType();
    	String myValue = myFilter.getValue(); //gets the values out of the filter
    	String field = myFilter.getField();
    	
    	int fieldIndex = getFieldIndex(field);
    	
    	//Note: I actually found this method of dividing them via exclude/include, rather than adding booleans, about the same as booleans added a lot more if/else statements. 
    	
    	if (myAction == Filter.FilterAction.INCLUDE_IF){ //runs a loop for the include if
    		
    		for (int x = 0; x < data.size(); x++){ //runs through the entire data set
    			
				if (myType == Filter.FilterType.STARTS_WITH){
					if (!data.get(x)[fieldIndex].toUpperCase().startsWith(myValue)){ //pulls each individual string
						dataCopy.remove(data.get(x)); //removes
						
					}
				}
				
				if (myType == Filter.FilterType.ENDS_WITH){
					if (!data.get(x)[fieldIndex].toUpperCase().endsWith(myValue)){
						dataCopy.remove(data.get(x));
						
					}
				}
				
				if (myType == Filter.FilterType.CONTAINS){
					if (!data.get(x)[fieldIndex].toUpperCase().contains(myValue)){
						dataCopy.remove(data.get(x));
						
					}
				}	
				
				if (myType == Filter.FilterType.EQUALS){
					if (!data.get(x)[fieldIndex].toUpperCase().equals(myValue)){
						dataCopy.remove(data.get(x));
						
					}
				}
			}
    	}
    	
    	if (myAction == Filter.FilterAction.EXCLUDE_IF){ // runs a loop for the exclude if
    		
    		for (int x = 0; x < data.size(); x++){
    			
				if (myType == Filter.FilterType.STARTS_WITH){
					if (data.get(x)[fieldIndex].toUpperCase().startsWith(myValue)){
						dataCopy.remove(data.get(x));
						
					}
				}
				
				if (myType == Filter.FilterType.ENDS_WITH){
					if (data.get(x)[fieldIndex].toUpperCase().endsWith(myValue)){
						dataCopy.remove(data.get(x));
						
					}
				}
				
				if (myType == Filter.FilterType.CONTAINS){
					if (data.get(x)[fieldIndex].toUpperCase().contains(myValue)){
						dataCopy.remove(data.get(x));
						
					}
				}	
				
				if (myType == Filter.FilterType.EQUALS){
					if (data.get(x)[fieldIndex].toUpperCase().equals(myValue)){
						dataCopy.remove(data.get(x));
						
					}
				}
			}
		}  
  				
		if (myAction == Filter.FilterAction.SORT_BY_ASCENDING){
    			dataCopy.sort(new CompareSystem(fieldIndex, true)); //calls comparator
    	}
    	
    	if (myAction == Filter.FilterAction.SORT_BY_DESCENDING){
    			dataCopy.sort(new CompareSystem(fieldIndex, false));
    	}
   
   }
 
  return dataCopy;
  
  }
  
     	class CompareSystem implements Comparator<String[]> {
     		private int fieldIndex;
     		private boolean sortType;
     		
     		public CompareSystem(int fieldIndex, boolean sortType){
     			this.sortType = sortType;
     			this.fieldIndex = fieldIndex;
     		}
     		
     		public int compare(String[] a, String[] b) {
     			if (sortType){
     				return a[fieldIndex].compareToIgnoreCase(b[fieldIndex]); //sorts ascending if true
     			}
     			
     			else{
     				return b[fieldIndex].compareToIgnoreCase(a[fieldIndex]); //sorts descending if flase
     			}
     		}
     	}	 
}



