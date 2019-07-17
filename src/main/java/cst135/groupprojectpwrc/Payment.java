package cst135.groupprojectpwrc;

import java.util.InputMismatchException;

public class Payment {
	//Money menu
	//Track deposit / remainder
	//Dispense change
	private double balanceOwed;
	
	public Payment(double balanceOwed) {
		this.balanceOwed = balanceOwed;
	}
	
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
	
	public double getPaymentAmount() {
		boolean invalidSelection;
		int selection = 0;
		do {
			invalidSelection = false;
			FrontEnd.showCashMenu(this.balanceOwed);
			try {
				selection = FrontEnd.sc.nextInt();
				if(selection !=1 && selection != 2 && selection != 3) {
					FrontEnd.showCashErrorMessage();
					invalidSelection = true;
				}
			}
			catch(InputMismatchException e) {
				FrontEnd.showCashErrorMessage();
				invalidSelection = true;
				FrontEnd.sc.nextLine();
			}
			
		} while(invalidSelection);
		
		//scan the next line to clear out the newline character before returning
		FrontEnd.sc.nextLine();
		
		//Convert validated selection into a dollar amount and return it
		return selectionToDollar(selection);
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
