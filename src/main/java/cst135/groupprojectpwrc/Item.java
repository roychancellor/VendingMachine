package cst135.groupprojectpwrc;

public class Item {
	private double cost;
	private String description;
	private double salesPrice;
	private int minStockLevel;
	private int currentInventory;
	
	/**
	 * @param cost
	 * @param description
	 * @param salesPrice
	 * @param minStockLevel
	 * @param currentInventory
	 */
	public Item(double cost, String description, double salesPrice, int minStockLevel, int currentInventory) {
		super();
		this.cost = cost;
		this.description = description;
		this.salesPrice = salesPrice;
		this.minStockLevel = minStockLevel;
		this.currentInventory = currentInventory;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the salesPrice
	 */
	public double getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice the salesPrice to set
	 */
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * @return the minStockLevel
	 */
	public int getMinStockLevel() {
		return minStockLevel;
	}

	/**
	 * @param minStockLevel the minStockLevel to set
	 */
	public void setMinStockLevel(int minStockLevel) {
		this.minStockLevel = minStockLevel;
	}

	/**
	 * @return the currentInventory
	 */
	public int getCurrentInventory() {
		return currentInventory;
	}

	/**
	 * @param currentInventory the currentInventory to set
	 */
	public void setCurrentInventory(int currentInventory) {
		this.currentInventory = currentInventory;
	}
	
	//Class methods
	public String toString() {
		return description + "," + cost + "," + salesPrice;
	}
}
