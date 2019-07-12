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
		if(this.balanceOwed < 0) {
			dispenseChange();
		}
	}
	
	private void showCashMenu() {
		System.out.println("-------------------------");
		System.out.println("CASH PAYMENT: SELECT ONE");
		System.out.println("-------------------------");
		System.out.println("1. $0.25");
		System.out.println("2. $1.00");
		System.out.println("3. $5.00");
		System.out.println("-------------------------");
		System.out.println("Selection:");
	}
	
	private void dispenseChange( ) {
		System.out.println("Dispensing " + this.balanceOwed + " change in the dispenser.");
	}
	
	public double getPaymentAmount() {
		boolean invalidSelection;
		int selection = 0;
		do {
			invalidSelection = false;
			showCashMenu();
			try {
				selection = VendingMachine.sc.nextInt();
				if(selection !=1 && selection != 2 && selection != 3) {
					showErrorMessage();
					invalidSelection = true;
				}
			}
			catch(InputMismatchException e) {
				showErrorMessage();
				invalidSelection = true;
				VendingMachine.sc.nextLine();
			}
			
		} while(invalidSelection);
		
		//CONVERT VALIDATED SELECTION INTO DOLLAR AMOUNT AND RETURN IT
		return 1;
	}
	
	private void showErrorMessage() {
		System.out.println("Please enter 1, 2, or 3 only");		
	}
}
