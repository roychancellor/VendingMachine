package cst135.groupprojectpwrc;

public class Payment {
	//Money menu
	//Track deposit / remainder
	//Dispense change
	private double balanceOwed;
	
	/**
	 * constructor for Payment objects
	 * @param balanceOwed the amount the customer owes before receiving the selected item
	 */
	public Payment(double balanceOwed) {
		this.balanceOwed = balanceOwed;
	}
	
	/**
	 * processes the cash payment from the user by keeping track of how much is owed and
	 * dispensing change
	 */
	public void doCashPayment() {
		//loop until cash paid >= balance owed, updating balance owed each time
		while(this.balanceOwed > 0) {
			this.balanceOwed -= getPaymentAmount();
		}
		
		//if cash paid > purchase price, then dispense change; otherwise, loop
		System.out.print("\nThank you!");
		if(this.balanceOwed < 0) {
			FrontEnd.dispenseChange(this.balanceOwed);
		}
	}
	
	/**
	 * 
	 * @return the dollar amount the user "input"
	 */
	public double getPaymentAmount() {
		//Show the cash entry interface
		FrontEnd.showCashMenu(this.balanceOwed);

		//Convert validated selection into a dollar amount and return it
		return selectionToDollar(FrontEnd.getIntFromUser(
			FrontEnd.getCashMenuMin(),
			FrontEnd.getCashMenuMax(),
			"** Please insert quarters, dollar bills, or five-dollar bills only")
		);
	}
	
	/**
	 * helper method that converts a menu selection into a dollar amount
	 * @param selection
	 * @return dollar amount corresponding to the selection
	 */
	private double selectionToDollar(int selection) {
		switch(selection) {
		case 1:
			return 0.25;
		case 2:
			return 1.0;
		case 3:
			return 5.0;
		default:
			return 0;
		}
	}
}
