public class CreditCard{

	private String accountName;
	private double creditLimit;
	private int accountNumber = 999;
	private static int NumberOfAccts = 999;
	private double balance;
	
	public CreditCard(String accountName, double creditLimit){
		this.accountName = accountName;
		this.creditLimit = creditLimit;
		NumberOfAccts += 1;
		accountNumber = NumberOfAccts;
	}
	
	public String toString(){
		return accountName + " (#" + accountNumber + "): Current Balance = $" + balance + " / $" + creditLimit;
	}
	
	
	public String getName(){
		return accountName;
	}
	
	public double getLimit(){
		return creditLimit;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public int getAccountNumber(){
		return accountNumber;
	}
	
	public boolean applyCharge(double amount){
		if (amount < 0){
			return false;
		}
		if ((amount + balance) > creditLimit){
			return false;
		}
		else{
			balance += amount;
			return true;
		}
	}
	public boolean makePayment(double amount){
		if (amount < 0){
			return false;
		}
		if ((balance - amount) < (creditLimit * -1)){
			return false;
		}
		else{
			balance = balance - amount;
		return true;
		}
	}
	
	public boolean transferBalance(CreditCard other, double amount){
		if (amount < 0){
			return false;
		}
		if (balance-amount < 0){
			return false;
		}
		if (other.creditLimit < other.balance + amount){
				return false;
		}
		else{
			balance -= amount;
			other.balance += amount;
			return true;
		}
	}
}

	
		
		
		