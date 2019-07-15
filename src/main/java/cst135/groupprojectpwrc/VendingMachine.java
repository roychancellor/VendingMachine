package cst135.groupprojectpwrc;

import java.util.Scanner;

public class VendingMachine {
	//Class data
	private static int numRows;
	private static int numCols;
	private int rowSelect;
	private int colSelect;
	private Item[][] items;
	private static final int itemsPerTube = 10;
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
	public void restockItem() {
		System.out.println("Restocking item[" + (rowSelect + 1) + "][" + (colSelect + 1) + "]");
	}
	
	public void purchaseItem() {
		processSelection(getSelection());
		System.out.println("Purchasing " + items[getRowSelect()][getColSelect()].getDescription());
		//GET CASH PAYMENT FROM USER
		payment = new Payment(items[getRowSelect()][getColSelect()].getSalesPrice());
		payment.doCashPayment();
		//Print items[row][col].snideRemark;
	}
	
	public void recordTransaction() {
		System.out.println("Recording transaction for item[" + (rowSelect + 1) + "][" + (colSelect + 1) + "]");
	}
	
	//Move to Item class????
	public void alertLowStock() {
		System.out.println("Low stock for item[" + (rowSelect + 1) + "][" + (colSelect + 1) + "]");
	}
	
	//Machine user interface
	public void displayMachineInterface() {
		final String PRICES = "|  $%.2f\t|  $%.2f\t|  $%.2f\t|\n";
		//TODO Add a format constant for the item descriptions
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("            Paul and Roy's Snack Box");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("|  " + items[0][0].getDescription() + "\t|  " + items[0][1].getDescription() + "\t|  " + items[0][2].getDescription() + "\t|");
		//System.out.println("|  " + items[0][0].getSalesPrice() + "\t\t|  " + items[0][1].getSalesPrice() + "\t\t|  " + items[0][2].getSalesPrice() + "\t\t|");
		System.out.format(PRICES, items[0][0].getSalesPrice(), items[0][1].getSalesPrice(), items[0][2].getSalesPrice());
		System.out.println("|  A1\t\t|  B1\t\t|  C1\t\t|");
		System.out.println("-------------------------------------------------");
		System.out.println("|  " + items[1][0].getDescription() + "\t|  " + items[1][1].getDescription() + "\t|  " + items[1][2].getDescription() + "\t|");
		//System.out.println("|  " + items[1][0].getSalesPrice() + "\t\t|  " + items[1][1].getSalesPrice() + "\t\t|  " + items[1][2].getSalesPrice() + "\t\t|");
		System.out.format(PRICES, items[1][0].getSalesPrice(), items[1][1].getSalesPrice(), items[1][2].getSalesPrice());
		System.out.println("|  A2\t\t|  B2\t\t|  C2\t\t|");
		System.out.println("-------------------------------------------------");
		System.out.println("|  " + items[2][0].getDescription() + "\t|  " + items[2][1].getDescription() + "\t|  " + items[2][2].getDescription() + "\t|");
		//System.out.println("|  " + items[2][0].getSalesPrice() + "\t\t|  " + items[2][1].getSalesPrice() + "\t\t|  " + items[2][2].getSalesPrice() + "\t\t|");
		System.out.format(PRICES, items[2][0].getSalesPrice(), items[2][1].getSalesPrice(), items[2][2].getSalesPrice());
		System.out.println("|  A3\t\t|  B3\t\t|  C3\t\t|");
		System.out.println("-------------------------------------------------");
	}
	
	//Creates the items for the machine
	private void loadMachine() {
		//double cost, String description, double salesPrice, int minStockLevel, int currentInventory
		items[0][0] = new Item(0.25, "Fritos", 0.75, 3, itemsPerTube);
		items[0][1] = new Item(0.25, "Doritos", 0.75, 3, itemsPerTube);
		items[0][2] = new Item(0.25, "Cheetos", 0.75, 3, itemsPerTube);
		items[1][0] = new Item(0.25, "Pop Tarts", 1.00, 3, itemsPerTube);
		items[1][1] = new Item(0.25, "Red Vines", 1.25, 3, itemsPerTube);
		items[1][2] = new Item(0.25, "Twizzlers", 1.25, 3, itemsPerTube);
		items[2][0] = new Item(0.25, "Pretzels", 1.50, 3, itemsPerTube);
		items[2][1] = new Item(0.25, "Beef Jerky", 1.75, 3, itemsPerTube);
		items[2][2] = new Item(0.25, "M & Ms", 0.50, 3, itemsPerTube);
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
	 * Sets the row and column of the item to be dispensed
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
//		if(selection.charAt(0) == 'A')
//			setRowSelect(0);
//		else if(selection.charAt(0) == 'B')
//			setRowSelect(1);
//		else if(selection.charAt(0) == 'C')
//			setRowSelect(2);
		
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
		
		System.out.println("row, col is " + getRowSelect() + ", " + getColSelect());
//		if(selection.charAt(1) == '1')
//			setColSelect(0);
//		else if(selection.charAt(1) == '2')
//			setColSelect(1);
//		else if(selection.charAt(1) == '3')
//			setColSelect(2);
	}
}
