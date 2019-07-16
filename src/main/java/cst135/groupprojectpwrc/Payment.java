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
			dispenseChange();
		}
	}
	
	private void showCashMenu() {
		System.out.println("\n-------------------------");
		System.out.println("      CASH PAYMENT");
		System.out.format("      Insert $%.2f\n", this.balanceOwed);
		System.out.println("-------------------------");
		System.out.println(" 1. $0.25");
		System.out.println(" 2. $1.00");
		System.out.println(" 3. $5.00");
		System.out.println("-------------------------");
		System.out.println(" Selection:");
	}
	
	private void dispenseChange() {
		System.out.format(" Dispensing $%.2f change in the dispenser.\n", -this.balanceOwed);
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
		
		//scan the next line to clear out the newline character before returning
		VendingMachine.sc.nextLine();
		
		//Convert validated selection into a dollar amount and return it
		return selectionToDollar(selection);
	}
	
	private void showErrorMessage() {
		System.out.println("\n** Please insert quarters, dollar bills, or five-dollar bills only");		
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
