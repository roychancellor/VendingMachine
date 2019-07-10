package cst135.groupprojectpwrc;

public class VendingMachine {
	//Class data
	private static int numRows;
	private static int numCols;
	private Item[][] items;
	private static final int itemsPerTube = 10;
	private String machineID;
	private double machineLatitude;
	private double machineLongitude;
	
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
	
	//Class methods
	public void restockItem(int row, int col) {
		System.out.println("Restocking item[" + (row + 1) + "][" + (col + 1) + "]");
	}
	
	public void purchaseItem(int row, int col) {
		System.out.println("Purchasing item[" + (row + 1) + "][" + (col + 1) + "]");
	}
	
	public void recordTransaction(int row, int col) {
		System.out.println("Recording transaction for item[" + (row + 1) + "][" + (col + 1) + "]");
	}
	
	public void alertLowStock(int row, int col) {
		System.out.println("Low stock for item[" + (row + 1) + "][" + (col + 1) + "]");
	}
	
	public void displayMachineInterface() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("            Paul and Roy's Snack Box");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("|  Fritos\t|  Doritos\t|  Cheetos  |");
		System.out.println("|  $0.75\t|  $0.75\t|  $0.75  |");
	}
}
