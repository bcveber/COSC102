import java.util.*;
public class Project{
	
	private int linesOfCode = 0;
	private Employee employees[];
	private int duration = 0; //Set Starter Variables
	private int maxEmployees = 0;
	private int currentEmployeeCount = 0;
	
	public Project(int linesOfCode, int maxEmployees, int duration){
		this.linesOfCode = linesOfCode;
		this.maxEmployees = maxEmployees; //Set Starter Variables
		this.duration = duration;
		this.employees = new Employee[maxEmployees];
	}
	
	public boolean addEmployee(Employee e){
		if (maxEmployees < currentEmployeeCount){ //will not add employees if exceeds max number of employees
			//throw new ArrayIndexOutOfBoundsException(); -- ignore this as it does not work with the return statement, but I wanted to make note that I tried this
			return false;
		}
		else{
			employees[currentEmployeeCount] = e; //adds employee into array
			currentEmployeeCount ++; //adds to the number of employees counted on the job
			return true;
		}
	}
		
	public boolean runProject(){
		int projectDays = 0;
		int i = 0;
		int writtenCount = 0; //set starter variables
		int testedCount = 0;
		
		while (testedCount < linesOfCode){ //checks if project is completed via tested code
			System.out.println("*** DAY " + (projectDays + 1) + " ***");
			i = 0;
			while (i < currentEmployeeCount){ //prints out each employee and their work for the day until all employees looped
				int individualLines = employees[i].work();
				if (employees[i] instanceof Programmer){ //prints out programmer info and counts to written lines if programmer
					System.out.println(employees[i] + " Completed " + individualLines + " lines");
					writtenCount += individualLines; //adds to the written count
				}
				else if (employees[i] instanceof Tester){ //printso out tester info and counts to tester lines if tester
					System.out.println(employees[i] + " Completed " + individualLines + " lines");
					testedCount += individualLines; //adds to the tested count of lines
				}
				i++; //adds to the number of employees that have been looped
			}
			System.out.println("Day " + (projectDays + 1) + "/" + duration + ": lines written = " + writtenCount + ", tested = " + testedCount + " (out of " + linesOfCode + ")");
			projectDays ++; //prints out the total work completed for the day
			
		if (projectDays+1 > duration){ //project fails if the number of days (projectDays) exceeds duration
			System.out.println("The project wasn't completed in time.");
			return false;
		}
	}
	System.out.println("After " + projectDays + " days, the project was completed."); //loop will quit and this success message will display if finished in time
	return true;
}

		public static void main(String[] args){
			/*
			//Project(int linesOfCode, int maxEmployees, int duration){
			Project firstproj = new Project(600, 5, 10);
			System.out.println(firstproj);
			Employee bob = new Programmer("bob", 100, 250);  //intial testing/trialing
			System.out.println(bob);
			System.out.println(bob.work());
			//Employee joe = new Programmer("joe", 100, 500);
			//System.out.println(joe.work());
			*/
			
			Project firstproj = new Project(500, 5, 10); //first regular test
			//Project firstproj = new Project(100, 5, 10); //test to make sure day counts, displayed and not displayed, are accurate even with small amount of lines
			//Project firstproj = new Project(300, 2, 10); // //add edge case for exceeding employees - succesfully displays the number of employees that could not be added
			//Project firstproj = new Project(1000, 5, 1); //add edge case for when they are obviously not able to finish in time; after a tweak to days, it successfully cuts out and fails after one day as the duration
			//Project firstproj = new Project(7000, 5, 10); //finally, an edge case testing for failure after a prolonged number of days to make sure everything displays correctly
			Employee jack = new Tester("jack", 150); 
			Employee joey = new Programmer("joey", 50, 150);
			Employee bob = new Programmer("bob", 100, 250); //intialize the employees
			Employee ron = new Tester("Ron", 300); //we know all of these employees work from the edge testing in Programmer and Tester files
			firstproj.addEmployee(jack);
			firstproj.addEmployee(bob); //add the employees
			firstproj.addEmployee(ron);
			firstproj.addEmployee(joey);
			System.out.println(firstproj.runProject());
		
	}
}