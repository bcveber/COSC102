public class CreditCardTester{
	
	public static void main(String[] args){
		CreditCard firstone = new CreditCard("Bobby", 1500); //Bobby (#1000): Current Balance = $0.0 / $1500.0
		System.out.println(firstone); 
		
		CreditCard secondone = new CreditCard("Johhny", 500); //Johhny (#1001): Current Balance = $100.0 / $500.0
		System.out.println(secondone);
		
		System.out.println(firstone.getName()); //bobby
		System.out.println(firstone.getLimit()); //1500
		System.out.println(firstone.getAccountNumber()); //1000
		System.out.println(firstone.getBalance()); //0.0
		
		System.out.println(secondone.getName()); //johnnny
		System.out.println(secondone.getLimit()); //500
		System.out.println(secondone.getAccountNumber()); //1001
		System.out.println(secondone.getBalance()); //0.0
		
		System.out.println(firstone.applyCharge(200)); //true
		System.out.println(firstone.applyCharge(-500)); //false
		System.out.println(firstone.applyCharge(2000)); //false
		
		System.out.println(secondone.applyCharge(-1)); //false
		System.out.println(secondone.applyCharge(-500)); //false
		System.out.println(secondone.applyCharge(6000)); //false
		System.out.println(secondone.applyCharge(200)); //true
		
		System.out.println(firstone.toString()); //Bobby (#1000): Current Balance = $200.0 / $1500.0
		System.out.println(secondone.toString()); //Johhny (#1001): Current Balance = $200.0 / $500.0
		//This works -- there were only 2 successful charges to their account for 200 each, which show successfully
		System.out.println(secondone.makePayment(-3)); //false
		System.out.println(secondone.makePayment(-200)); //false
		System.out.println(secondone.makePayment(5000)); //false
		System.out.println(secondone.makePayment(100)); //true
		
		System.out.println(firstone.makePayment(100)); //true
		System.out.println(firstone.makePayment(-400)); //false
		System.out.println(firstone.makePayment(1500)); //true
		
		System.out.println(firstone.toString()); //Bobby (#1000): Current Balance = $-1400.0 / $1500.0
		System.out.println(secondone.toString()); //Johhny (#1001): Current Balance = $100.0 / $500.0
		//This also works -- Bobby had 200, paid off 100, and then paid off 1500, leaving him with a neg balance of 1400
		//Johnny had 200, paid off 100, leaving him with a balance of 100.
		System.out.println(secondone.transferBalance(-1)); //false
		System.out.println(secondone.transferBalance(firstone, 50)); //true
		System.out.println(firstone.transferBalance(secondone, 200)); //false
		System.out.println(secondone.transferBalance(secondone, 75)); //false
		
		System.out.println(firstone.toString()); //Bobby (#1000): Current Balance = $-1350.0 / $1500.0
		System.out.println(secondone.toString()); //Johhny (#1001): Current Balance = $50.0 / $500.0
		// This works -- Johnny transferred $50 to Bobby, and their balances changed accordingly when compared with the last toString print. 

	}
}