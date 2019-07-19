package cst135.groupprojectpwrc;

public class MachineController {
	
	/**
	 * method that calls the runMachine method for the vending machine
	 * @param args the String args array (not implemented in this application)
	 */
	public static void main(String[] args) {
		//Make the two required objects to run the machine
		Administrator root = new Administrator("<<rootkey>>", "6026894457");
		VendingMachine vm = new VendingMachine(3, 3, root);
		
		//Initialize the machine (loads it with tasty items and sets to initialized)
		root.doInitializeMachine(vm);
		
		//Run the machine and rake in the money
		vm.runMachine();
	}
}
