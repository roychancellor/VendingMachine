package cst135.groupprojectpwrc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
	//Class data fields
	private String selection;
	private static int numRows;
	private static int numCols;
	private int row;
	private int col;
	private Item[][] items;
	private final int ITEMS_PER_TUBE = 3;
	private String machineID;
	private double machineLatitude;
	private double machineLongitude;
	private Payment payment;
	private List<Transaction> transactions;
	private Administrator root;
	
	//Scanner for use across the application
	public static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructor for a new VendingMachine object
	 * @param numRows
	 * @param numCols
	 */
	public VendingMachine(int numRows, int numCols) {
		 items = new Item[numRows][numCols];
		 VendingMachine.numRows = numRows;
		 VendingMachine.numCols = numCols;
		 this.machineID = "PWRC1";
		 this.machineLatitude = 33.512682;
		 this.machineLongitude = -112.113626;
		 this.root = new Administrator("abc", "4802426456");
		 this.transactions = new ArrayList<Transaction>();
		 root.loadMachine(this);
	}

	//Accessors and Mutators
	
	/**
	 * @return the machineID
	 */
	public String getMachineID() {
		return machineID;
	}

	/**
	 * @param machineID the machineID to set
	 */
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}

	/**
	 * @return the machineLatitude
	 */
	public double getMachineLatitude() {
		return machineLatitude;
	}

	/**
	 * @param machineLatitude the machineLatitude to set
	 */
	public void setMachineLatitude(double machineLatitude) {
		this.machineLatitude = machineLatitude;
	}

	/**
	 * @return the machineLongitude
	 */
	public double getMachineLongitude() {
		return machineLongitude;
	}

	/**
	 * @param machineLongitude the machineLongitude to set
	 */
	public void setMachineLongitude(double machineLongitude) {
		this.machineLongitude = machineLongitude;
	}

	/**
	 * @return the numRows
	 */
	public static int getNumRows() {
		return numRows;
	}

	/**
	 * @return the numCols
	 */
	public static int getNumCols() {
		return numCols;
	}

	/**
	 * @return the items
	 */
	public Item[][] getItems() {
		return items;
	}
	
	/**
	 * @return the itemsPerTube
	 */
	public int getItemsPerTube() {
		return ITEMS_PER_TUBE;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int rowSelect) {
		this.row = rowSelect;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int colSelect) {
		this.col = colSelect;
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	//Class methods
	
	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * @return the root
	 */
	public Administrator getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Administrator root) {
		this.root = root;
	}

	/**
	 * returns the machine ID with the latitude and longitude of its location
	 */
	public String toString() {
		return this.machineID + ": lat " + this.machineLatitude + ", long " + this.machineLongitude;
	}

	//Class methods
	/**
	 * method that perpetually runs the machine
	 */
	public void runMachine() {
		do {
			//Show machine interface
			displayMachineInterface();
			//Purchase items
			purchaseItem();
		} while(true);
	}
	
	/**
	 * the primary method for purchasing items from the machine
	 * gets a selection from the user, determines if the item is available,
	 * gets cash payment, dispenses item, updates inventory, and records
	 * the transaction for administrator analysis
	 */
	public void purchaseItem() {
		//Get the selection from the user (e.g. "B3")
		getSelection();
		//Convert the selection into a row-column reference for the items array
		selectionToRowCol();
		
		//Check availability of item: if available, get payment and dispense item; if not, alert user
		if(itemIsAvailable()) {
			System.out.println("\nPurchasing " + items[getRow()][getCol()].getDescription());
			
			//Get cash payment from the user
			payment = new Payment(items[getRow()][getCol()].getSalesPrice());
			payment.doCashPayment();
			
			//Dispense item
			dispenseItem();
			
			//Update inventory of the item
			decrementInventory();
			
			//Record the transaction
			recordTransaction();			
		}
		else {
			String stateVerb = "is";
			if(items[getRow()][getCol()].getDescription().charAt(items[getRow()][getCol()].getDescription().length() - 1) == 's')
				stateVerb = "are";
			
			System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("  Sorry, " + items[getRow()][getCol()].getDescription()
				+ " " + stateVerb + " unavailable"
				+ "\n  Make a different selection");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
	
	/**
	 * determines if an item is available
	 * @return the boolean value of item inventory > 0
	 */
	private boolean itemIsAvailable() {
		return items[getRow()][getCol()].getCurrentInventory() > 0;
	}
	
	/**
	 * dispenses an item to the user (simulated since no physical machine)
	 * outputs a message to the user
	 */
	private void dispenseItem() {
		//Dispense the item to the user
		System.out.println("<<<< whirring >>>>");
		System.out.println("<<<< clunk >>>>");
		System.out.println("\n" + items[getRow()][getCol()].getConsumerMessage());	
	}
	
	/**
	 * decrements the item inventory and alerts the administrator of low inventory
	 */
	private void decrementInventory() {
		//Decrement the item inventory
		items[getRow()][getCol()].setCurrentInventory(items[getRow()][getCol()].getCurrentInventory() - 1);
		
		//Alert administrator of low inventory
		if(items[getRow()][getCol()].getCurrentInventory() <= items[getRow()][getCol()].getMinStockLevel()) {
			this.alertLowStock();			
		}		
	}
	
	/**
	 * adds a transaction to the transaction list
	 */
	public void recordTransaction() {
		transactions.add(
			new Transaction(this.machineID,
			items[getRow()][getCol()].getDescription(),
			this.selection,
			items[getRow()][getCol()].getCost(),
			items[getRow()][getCol()].getSalesPrice())
		);
	}
	
	//Machine user interface
	public void displayMachineInterface() {
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
	
	/**
	 * Gets the user selection and validates that it is only two characters long
	 * and equals one of the available selection codes
	 * @return
	 */
	private void getSelection() {
		boolean invalidSelection;
		
		do {
			invalidSelection = false;
			System.out.println("\nMake a selection (ex. A1):");
			selection = sc.nextLine().toUpperCase();
			if(selection.length() != 2)
				invalidSelection = true;
			else if(selection.charAt(0) != 'A' && selection.charAt(0) != 'B' && selection.charAt(0) != 'C') {
				invalidSelection = true;
			}
			else if(selection.charAt(1) != '1' && selection.charAt(1) != '2' && selection.charAt(1) != '3') {
				invalidSelection = true;
			}
		} while(invalidSelection);
	}
	
	/**
	 * Sets the row and column indexes of the items array based on the item selection string (e.g. B3 becomes 2,1)
	 * @param selection
	 */
	private void selectionToRowCol() {
		//Set the item row
		switch(selection.charAt(0)) {
			case 'A':
				setCol(0);
				break;
			case 'B':
				setCol(1);
				break;
			case 'C':
				setCol(2);
				break;
		}
		
		//Set the item column 
		switch(selection.charAt(1)) {
			case '1':
				setRow(0);
				break;
			case '2':
				setRow(1);
				break;
			case '3':
				setRow(2);
				break;
		}		
	}
	
	/**
	 * Alerts the machine administrator of low inventory for an item
	 */
	private void alertLowStock() {
		if(!items[getRow()][getCol()].isAdminAlerted()) {
			System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(" Messaging administrator:");
			messageAdmin("Low stock of " + items[getRow()][getCol()].getDescription()
				+ " in machine: " + this.toString());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
			items[getRow()][getCol()].setAdminAlerted(true);
		}
	}	
	
	/**
	 * sends message to machine administrator
	 * @param message
	 */
	private void messageAdmin(String message) { 
		System.out.println(" Sending message to " + root.getPhoneNumber() + "\n \"" + message + "\"" );
		System.out.println("\n <<<< swoosh....message sent >>>>");
	}
}
