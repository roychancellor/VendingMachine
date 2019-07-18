package cst135.groupprojectpwrc;

public class Electronic extends Payment {

	//private double balanceOwed;
	
	public Electronic(double balanceOwed) {
		super(balanceOwed);
	}
	
	/**
	 * processes the phone payment from the user by keeping track of how much is owed and
	 * dispensing change
	 */
	public void doPayment() {
		getPaymentAmount();
	}
	
	/**
	 * 
	 * @return the dollar amount the user "input"
	 */
	public double getPaymentAmount() {
		System.out.println("\nPlace your phone near the sensor");
		System.out.println("<< Processing >>");
		System.out.print("\nThank you!");
		
		return 0;
	}	
}
