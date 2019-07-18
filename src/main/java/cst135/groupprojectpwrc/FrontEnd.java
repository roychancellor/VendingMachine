package cst135.groupprojectpwrc;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontEnd {
	//Scanner for use across the application
	public static final Scanner sc = new Scanner(System.in);
	private static String selection;
	private static final int CASH_MENU_MIN = 1;
	private static final int CASH_MENU_MAX = 3;
	
	/**
	 * @return the cashMenuMin
	 */
	public static int getCashMenuMin() {
		return CASH_MENU_MIN;
	}

	/**
	 * @return the cashMenuMax
	 */
	public static int getCashMenuMax() {
		return CASH_MENU_MAX;
	}

	/**
	 * Machine user interface
	 * @param items a two-dimensional array of item objects in a vending machine
	 */
	public static void displayMachineInterface(Item[][] items) {
		boolean allItemsExist = true;
		for(int r = 0; r < items.length; r++) {
			for(int c = 0; c < items[r].length; c++) {
				if(items[r][c] == null)
					allItemsExist = false;
			}
		}
		if(allItemsExist) {
			final String DESCRIPTIONS = "|  %s\t|  %s\t|  %s\t|\n";
			final String PRICES = "|  $%.2f\t|  $%.2f\t|  $%.2f\t|\n";
			final String CALORIES = "|  %d cal\t|  %d cal\t|  %d cal\t|\n";
			final String CODES = "|  A%d\t\t|  B%d\t\t|  C%d\t\t|\n";
			final String HORIZ_SEPARATOR = "-------------------------------------------------\n";
			//TODO Add a format constant for the item descriptions
			System.out.println("\n\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("            Paul and Roy's Snack Box");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.format("\n" + HORIZ_SEPARATOR);
			System.out.format(DESCRIPTIONS, items[0][0].getDescription(), items[0][1].getDescription(), items[0][2].getDescription());
			System.out.format(PRICES, items[0][0].getSalesPrice(), items[0][1].getSalesPrice(), items[0][2].getSalesPrice());
			System.out.format(CALORIES, items[0][0].getCalories(), items[0][1].getCalories(), items[0][2].getCalories());
			System.out.format(CODES, 1, 1, 1);
			System.out.format(HORIZ_SEPARATOR);
			System.out.format(DESCRIPTIONS, items[1][0].getDescription(), items[1][1].getDescription(), items[1][2].getDescription());
			System.out.format(PRICES, items[1][0].getSalesPrice(), items[1][1].getSalesPrice(), items[1][2].getSalesPrice());
			System.out.format(CALORIES, items[1][0].getCalories(), items[1][1].getCalories(), items[1][2].getCalories());
			System.out.format(CODES, 2, 2, 2);
			System.out.format(HORIZ_SEPARATOR);
			System.out.format(DESCRIPTIONS, items[2][0].getDescription(), items[2][1].getDescription(), items[2][2].getDescription());
			System.out.format(PRICES, items[2][0].getSalesPrice(), items[2][1].getSalesPrice(), items[2][2].getSalesPrice());
			System.out.format(CALORIES, items[2][0].getCalories(), items[2][1].getCalories(), items[2][2].getCalories());
			System.out.format(CODES, 3, 3, 3);
			System.out.format(HORIZ_SEPARATOR);
		}
	}
	
	/**
	 * gets an integer between minValue and maxValue from the user
	 * If the user enters anything other than an integer, catches the exception
	 * and prints the error message received from the method call
	 * @param minValue the minimum value of the menu
	 * @param maxValue the maximum value of the menu
	 * @param errorMessage the error message to display for an invalid entry
	 * @return the integer the user entered
	 */
	public static int getIntFromUser(int minValue, int maxValue, String errorMessage) {
		int selection = 0;
		boolean invalidSelection;
		
		//Loop until the user enters an integer between the given limits
		do {
			invalidSelection = false;
			try {
				selection = FrontEnd.sc.nextInt();
				if(selection < minValue || selection > maxValue) {
					FrontEnd.showErrorMessage(errorMessage);
					invalidSelection = true;
				}
			}
			catch(InputMismatchException e) {
				FrontEnd.showErrorMessage(errorMessage);
				invalidSelection = true;
				FrontEnd.sc.nextLine();
			}
		} while(invalidSelection);

		//scan the next line to clear out the newline character before returning
		FrontEnd.sc.nextLine();
		
		return selection;
	}
	
	/**
	 * shows the payment type menu
	 */
	public static void showPaymentMenu() {
		System.out.println("\n-------------------------");
		System.out.println("      PAYMENT METHOD");
		System.out.println("-------------------------");
		System.out.println(" 1. CASH");
		System.out.println(" 2. PAY BY PHONE");
		System.out.println("-------------------------");
		System.out.println(" Selection:");
	}

	/**
	 * shows the cash menu
	 * @param balanceOwed the remaining balance to be paid for the item
	 */
	public static void showCashMenu(double balanceOwed) {
		System.out.println("\n-------------------------");
		System.out.println("      CASH PAYMENT");
		System.out.format("      Insert $%.2f\n", balanceOwed);
		System.out.println("-------------------------");
		System.out.println(" 1. $0.25");
		System.out.println(" 2. $1.00");
		System.out.println(" 3. $5.00");
		System.out.println("-------------------------");
		System.out.println(" Selection:");
	}

	/**
	 * shows change being dispensed
	 * @param balanceOwed the remaining balance to be paid for the item
	 */
	public static void dispenseChange(double balanceOwed) {
		System.out.format(" Dispensing $%.2f change in the dispenser.\n", -balanceOwed);
	}
	
	/**
	 * shows the cash error message when user enters the wrong type of cash
	 * @param message the error message to display
	 */
	public static void showErrorMessage(String message) {
		System.out.println("\n" + message);		
	}
	
	/**
	 * message to the user when an item is not available
	 * @param itemDescription the String description of the item
	 */
	public static void showItemNotAvailable(String itemDescription) {
		String stateVerb = "is";
		if(itemDescription.charAt(itemDescription.length() - 1) == 's')
			stateVerb = "are";
		
		System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Sorry, " + itemDescription
			+ " " + stateVerb + " unavailable"
			+ "\n  Make a different selection");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
	
	/**
	 * Gets the user selection in UPPER case and validates that it is only two characters long
	 * and equals one of the available selection codes
	 * @param adminPassword the password to check when user enters a vending item selection
	 * @return selection the string value of the user entry
	 */
	public static String getItemSelection(String adminPassword) {
		boolean invalidSelection;
		
		do {
			invalidSelection = false;
			System.out.println("\nMake a selection (ex. A1):");
			selection = sc.nextLine().toUpperCase();
			
			//Check for admin password match
			if(selection.equals(adminPassword.toUpperCase())) {
				return selection;
			}
			else {
				if(selection.length() != 2)
					invalidSelection = true;
				else if(selection.charAt(0) != 'A' && selection.charAt(0) != 'B' && selection.charAt(0) != 'C') {
					invalidSelection = true;
				}
				else if(selection.charAt(1) != '1' && selection.charAt(1) != '2' && selection.charAt(1) != '3') {
					invalidSelection = true;
				}
			}
		} while(invalidSelection);
		
		return selection;
	}
	
	/**
	 * Sets the column index of the items array based on the item selection string
	 * @return the index
	 */
	public static int selectionToCol() {
		//Set the item row
		switch(selection.charAt(0)) {
			case 'A':
				return 0;
			case 'B':
				return 1;
			case 'C':
				return 2;
			default:
				return 0;
		}
	}
		
	/**
	 * Sets the column index of the items array based on the item selection string
	 * @return the index
	 */
	public static int selectionToRow() {
		//Set the item column 
		switch(selection.charAt(1)) {
			case '1':
				return 0;
			case '2':
				return 1;
			case '3':
				return 2;
			default:
				return 0;
		}	
	}
}
