package cst135.groupprojectpwrc;

public class Administrator {
	//This class will allow an administrator to perform a variety
	//of functions on a vending machine, such as restocking,
	//reconfiguring, computing profit, etc.
	
	private String passCode;
	private String phoneNumber;
	
	public Administrator(String passCode, String phoneNumber) {
		this.passCode = passCode;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the passCode
	 */
	public String getPassCode() {
		return passCode;
	}

	/**
	 * @param passCode the passCode to set
	 */
	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	//Class methods
	/**
	 * Creates the items for the machine
	 * @param vm a vending machine object for loading items into
	 */
	public void loadMachine(VendingMachine vm) {
		//double cost, String description, double salesPrice, int minStockLevel, int currentInventory
		vm.getItems()[0][0] = new Item(0.25, "Fritos", 0.75, 1, vm.getItemsPerTube(), 250);
		vm.getItems()[0][1] = new Item(0.25, "Doritos", 0.75, 1, vm.getItemsPerTube(), 250);
		vm.getItems()[0][2] = new Item(0.25, "Cheetos", 0.75, 1, vm.getItemsPerTube(), 250);
		vm.getItems()[1][0] = new Item(0.25, "Pop Tarts", 1.00, 1, vm.getItemsPerTube(), 300);
		vm.getItems()[1][1] = new Item(0.25, "Red Vines", 1.25, 1, vm.getItemsPerTube(), 150);
		vm.getItems()[1][2] = new Item(0.25, "Twizzlers", 1.25, 1, vm.getItemsPerTube(), 150);
		vm.getItems()[2][0] = new Item(0.25, "Pretzels", 1.50, 1, vm.getItemsPerTube(), 200);
		vm.getItems()[2][1] = new Item(0.25, "Beef Jerky", 1.75, 1, vm.getItemsPerTube(), 500);
		vm.getItems()[2][2] = new Item(0.25, "M & Ms", 0.50, 1, vm.getItemsPerTube(), 300);
	}
	
	/**
	 * replenishes inventory for an item
	 * @param vm the vending machine being restocked
	 * @param row the row reference of the item being replenished
	 * @param col the col reference of the item being replenished
	 */
	public void restockItem(VendingMachine vm, int row, int col) {
		System.out.println("\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println("Replenishing stock for machine "
			+ vm.toString()
			+ ", item "
			+ vm.getItems()[vm.getRow()][vm.getCol()].getDescription());
		System.out.println("\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

		//Update the inventory
		vm.getItems()[vm.getRow()][vm.getCol()].setCurrentInventory(vm.getItemsPerTube());
		
		//Reset the alert flag so a new message can be sent about the item
		vm.getItems()[row][col].setAdminAlerted(false);
	}	
	
	/**
	 * prints a list of transactions
	 * @param vm the vending machine object for printing transactions
	 */
	public void printTransactions(VendingMachine vm) {
		if(vm.getTransactions().size() > 0) {
			for(Transaction t : vm.getTransactions())
				t.printTransaction();
		}
		else {
			System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("\n There are no transactions to print\n");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");			
		}
	}
}
