import java.util.*;

public abstract class Employee {
  
  private static int idNum = 100; //set ID starter value

  private String name = "";
  private int id = 0; //continue setting starter values
  
  protected static Random rand = new Random(); //random calculator
  
  public Employee(String name){
  	  this.name = name;
  	  id = idNum; //set Employee class
  	  idNum ++;
  }
  
  	  

  public String toString() {
    return name + " (ID: " + id + ")";
  } 
  
  public boolean equals(Object other) {
    if (other instanceof Employee && other != null) 
      return (name.equals(((Employee)other).name) && id == ((Employee)other).id);
    
    return false;
  }
  
  public String getName(){
  	return name;
  }
  
  public int getId(){
  	return id;
  }

  public abstract int work();
}
