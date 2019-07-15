package cst135.groupprojectpwrc;

public class MachineController {
	
	public static void main(String[] args) {
		VendingMachine vm = new VendingMachine(3, 3);
		vm.displayMachineInterface();
		vm.purchaseItem();
	}
}
