package cst135.groupprojectpwrc;

import java.util.Scanner;

public class VendingMachine {
	//Class data
	private static int numRows;
	private static int numCols;
	private int rowSelect;
	private int colSelect;
	private Item[][] items;
	private static final int ITEMS_PER_TUBE = 2;
	private String machineID;
	private double machineLatitude;
	private double machineLongitude;
	private Payment payment;
	public static Scanner sc = new Scanner(System.in);
	
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
		 loadMachine();
	}

	//Creates the items for the machine
	private void loadMachine() {
		//double cost, String description, double salesPrice, int minStockLevel, int currentInventory
		items[0][0] = new Item(0.25, "Fritos", 0.75, 3, ITEMS_PER_TUBE, 250);
		items[0][1] = new Item(0.25, "Doritos", 0.75, 3, ITEMS_PER_TUBE, 250);
		items[0][2] = new Item(0.25, "Cheetos", 0.75, 3, ITEMS_PER_TUBE, 250);
		items[1][0] = new Item(0.25, "Pop Tarts", 1.00, 3, ITEMS_PER_TUBE, 300);
		items[1][1] = new Item(0.25, "Red Vines", 1.25, 3, ITEMS_PER_TUBE, 150);
		items[1][2] = new Item(0.25, "Twizzlers", 1.25, 3, ITEMS_PER_TUBE, 150);
		items[2][0] = new Item(0.25, "Pretzels", 1.50, 3, ITEMS_PER_TUBE, 200);
		items[2][1] = new Item(0.25, "Beef Jerky", 1.75, 3, ITEMS_PER_TUBE, 500);
		items[2][2] = new Item(0.25, "M & Ms", 0.50, 3, ITEMS_PER_TUBE, 300);
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
	 * @return the rowSelect
	 */
	public int getRowSelect() {
		return rowSelect;
	}

	/**
	 * @param rowSelect the rowSelect to set
	 */
	public void setRowSelect(int rowSelect) {
		this.rowSelect = rowSelect;
	}

	/**
	 * @return the colSelect
	 */
	public int getColSelect() {
		return colSelect;
	}

	/**
	 * @param colSelect the colSelect to set
	 */
	public void setColSelect(int colSelect) {
		this.colSelect = colSelect;
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	//Class methods
	
	public void runMachine() {
		do {
			//Show machine interface
			displayMachineInterface();
			//Purchase items
			purchaseItem();
		} while(true);
	}
	
	public void purchaseItem() {
		//Get the selection from the user (e.g. "B3")
		processSelection(getSelection());
		
		//Check availability of item: if available, get payment and dispense item; if not, alert user
		if(items[getRowSelect()][getColSelect()].getCurrentInventory() > 0 ) {
			System.out.println("\nPurchasing " + items[getRowSelect()][getColSelect()].getDescription());
			
			//Get cash payment from the user
			payment = new Payment(items[getRowSelect()][getColSelect()].getSalesPrice());
			payment.doCashPayment();
			
			//Dispense item
			dispenseItem();
			
			//Update inventory of the item
			updateInventory();
		}
		else {
			System.out.println("\n** Sorry, " + items[getRowSelect()][getColSelect()].getDescription() + " is unavailable."
				+ " Make another selection.");
		}
	}
	
	private void dispenseItem() {
		//Dispense the item to the user
		System.out.println("\n" + items[getRowSelect()][getColSelect()].getConsumerMessage());		
	}
	
	private void updateInventory() {
		items[getRowSelect()][getColSelect()].setCurrentInventory(items[getRowSelect()][getColSelect()].getCurrentInventory() - 1);
	}
	
	public void recordTransaction() {
		System.out.println("Recording transaction for item[" + (rowSelect + 1) + "][" + (colSelect + 1) + "]");
	}
	
	//Move to Item class????
	public void alertLowStock() {
		System.out.println("Low stock for item[" + (rowSelect + 1) + "][" + (colSelect + 1) + "]");
	}
	
	public void restockItem() {
		System.out.println("Restocking item[" + (rowSelect + 1) + "][" + (colSelect + 1) + "]");
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
	private String getSelection() {
		String selection;
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
		return selection;
	}
	
	/**
	 * Sets the row and column indexes of the items array based on the item selection string (e.g. B3 becomes 2,1)
	 * @param selection
	 */
	private void processSelection(String selection) {
		//Set the item row
		switch(selection.charAt(0)) {
			case 'A':
				setColSelect(0);
				break;
			case 'B':
				setColSelect(1);
				break;
			case 'C':
				setColSelect(2);
				break;
		}
		
		//Set the item column 
		switch(selection.charAt(1)) {
			case '1':
				setRowSelect(0);
				break;
			case '2':
				setRowSelect(1);
				break;
			case '3':
				setRowSelect(2);
				break;
		}		
	}
}
