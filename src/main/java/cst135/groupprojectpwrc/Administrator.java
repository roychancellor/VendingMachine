package cst135.groupprojectpwrc;

public class Administrator {
	//This class will allow an administrator to perform a variety
	//of functions on a vending machine, such as restocking,
	//reconfiguring, computing profit, etc.
	
	private String passCode;
	private String phoneNumber;
	private boolean machineRunning;
	private VendingMachine vm;
	
	public Administrator(String passCode, String phoneNumber) {
		this.passCode = passCode;
		this.phoneNumber = phoneNumber;
		this.machineRunning = true;
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

	/**
	 * @return the runMachine
	 */
	public boolean isMachineRunning() {
		return machineRunning;
	}

	//Class methods
	/**
	 * shows the cash menu
	 * @param balanceOwed the remaining balance to be paid for the item
	 */
	private void showAdminMenu() {
		System.out.println("\nrootrootrootrootrootrootrootroot");
		System.out.println("       ADMINISTRATOR MENU");
		System.out.println("rootrootrootrootrootrootrootroot");
		System.out.println(" 1. Restock Items");
		System.out.println(" 2. Reconfigure Items");
		System.out.println(" 3. View Transactions");
		System.out.println(" 4. Shutdown Machine");
		System.out.println("-------------------------");
		System.out.println(" 0. Exit to Machine");
		System.out.println("-------------------------");
		System.out.println(" Selection:");
	}
	
	/**
	 * primary method for performing machine administration functions
	 * @param vm a VendingMachine item for the machine
	 */
	public void runAdmin(VendingMachine vm) {
		this.vm = vm;
		boolean adminLoggedIn = true;
		final int EXIT_MENU = 0;
		final int MIN_MENU = 1;
		final int MAX_MENU = 4;
		do {
			adminLoggedIn = true;
			showAdminMenu();
			switch(FrontEnd.getIntFromUser(EXIT_MENU, MAX_MENU,
				"Oops, enter a value from " + MIN_MENU + " to " + MAX_MENU + " or " + EXIT_MENU + " to exit admin mode")) {
				case 1:
					System.out.println("  Restocking items");
					doRestockItems();
					break;
				case 2:
					System.out.println("  Reconfiguring items");
					break;
				case 3:
					System.out.println("  Viewing transactions");
					this.printTransactions();
					break;
				case 4:
					System.out.println("\n  ***Shutting down machine...");
					this.machineRunning = false;
					adminLoggedIn = false;
					break;
				case 0:
					adminLoggedIn = false;
					break;
			}
		} while(adminLoggedIn);
	}
	
	/**
	 * checks inventory of each item in the machine and replenishes any
	 * tubes that are not full
	 */
	private void doRestockItems() {
		for(int r = 0; r < vm.getNumRows(); r++) {
			for(int c = 0; c < vm.getNumCols(); c++) {
				if(vm.getItems()[r][c].getCurrentInventory() < vm.getItemsPerTube()) {
					System.out.println("\n  Restocking " + vm.getItems()[r][c].getDescription());
					vm.getItems()[r][c].setCurrentInventory(vm.getItemsPerTube());
				}
			}
		}
	}
	
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
	public void printTransactions() {
		System.out.println("\nMachine\tDate-Time\t\tPos\tDescription\tCost\tSale");
		System.out.println("---------------------------------------------------------------------");
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
