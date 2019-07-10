package cst135.groupprojectpwrc;

public class VendingMachine {
	//Class data
	private static final int numRows = 3;
	private static final int numCols = 3;
	private Items[][] items;
	private static final int itemsPerTube = 10;
	private String machineID;
	private double machineLatitude;
	private double machineLongitude;
	
	//Constructor
	public VendingMachine(int numRows, int numCols) {
		 items = new Items[numRows][numCols];
	}
}
