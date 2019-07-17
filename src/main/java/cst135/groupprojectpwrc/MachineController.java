package cst135.groupprojectpwrc;

public class MachineController {
	
	/**
	 * method that calls the runMachine method for the vending machine
	 * @param args
	 */
	public static void main(String[] args) {
		VendingMachine vm = new VendingMachine(3, 3);
		vm.runMachine();
	}
}
