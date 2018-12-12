public class Tester extends Employee{
	
	private int averageLinesPerDay = 0; //Set starter variables
	
	public Tester(String name, int averageLinesPerDay){
		super(name); //Set starter variables, get name from employee via super()
		this.averageLinesPerDay = averageLinesPerDay;
	}
	
	public String toString(){ //Create return line for tester info to be dipsplayed
		return "[Tester] " + this.getName() + " - ID: " + this.getId() + ", avg lines: " + averageLinesPerDay; //add work() info in Project
	}
	public int work(){ //calculate a random value for testers work
		int worknumtester = (averageLinesPerDay * (3/4)) + rand.nextInt((averageLinesPerDay * (5/4)) - (averageLinesPerDay * (3/4)));
		return worknumtester;
	}
	/*
	public static void main(String args[]){
		Tester paul = new Tester("paul", 100);
		System.out.println(paul); //Test toString method
		System.out.println(paul.work()); //Test work() method and make sure random calculation works
		
		Tester ronny = new Tester("ronny", 500);
		System.out.println(ronny); //Test toString method
		System.out.println(ronny.work()); //Test work() method and make sure random calculation works
		
		Tester edgecase = new Tester("edgecase", 1);
		System.out.println(edgecase); //make sure I don't get an error back even with 1 line
		System.out.println(edgecase.work()); 
	}
*/
}
	