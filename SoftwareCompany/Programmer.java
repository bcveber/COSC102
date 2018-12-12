public class Programmer extends Employee{
	
	private int low = 0; //set starter variables
	private int high = 0;
	
	public Programmer(String name, int low, int high){
		super(name);
		this.low = low; //create Programmer
		this.high = high;
	}
	
	public String toString(){ //return Programmer info
		return "[Programmer] " + this.getName() + " - ID: " + this.getId() + ", line range: " + low + "-" + high; //add work in project function
	}
	
	public int work(){ //work calculation
		int worknum = low + rand.nextInt(high-low); //takes high-low but ensures low will be added back, resulting in a high still being tested
		return worknum;
	}
/*	
	public static void main(String args[]){
		Programmer jack = new Programmer("jack", 100, 300); //checking to see if toString() works properly
		System.out.println(jack);
		System.out.println(jack.work()); //checking to see if work() works properly with random
		Programmer bob = new Programmer("bob", 50, 500);
		System.out.println(bob);
		System.out.println(bob.work());
		Programmer edgecase = new Programmer("edgecase", 10, 12); //edgecase - making sure it stays within range
		System.out.println(edgecase);
		System.out.println(edgecase.work());
		Programmer edgecase2 = new Programmer("edgecase2", 10, 11); //edgecase - making sure it stays within range
		System.out.println(edgecase2);
		System.out.println(edgecase2.work());
	}
*/
}