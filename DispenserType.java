public class DispenserType {
	private int numberOfItems;
	private double cost;
	
	public int getNoOfItems() {
		return numberOfItems;
	}

	public double getCost() {
		return cost;
	}

	public void setNoOfItems(int numItem) {
		 numberOfItems = numItem;
	}

	public void setCost(double costInput) {
		cost = costInput;
	}

	public void makeSale(int count) {
		numberOfItems = numberOfItems - count;
	}

	public boolean verifyStock() {
		boolean validInput = false;
		
		if (numberOfItems > 0) {
			validInput = true;
		} else {
			validInput = false;
		}
		
		return validInput;
	}
	
	public DispenserType () {
		setNoOfItems(50);
		setCost(70.00);
	}
	
	public DispenserType (double newCost) {
		setNoOfItems(50);
		setCost(newCost);
	}
	
	public DispenserType (int newNo, double newCost) {
		setNoOfItems(newNo);
		setCost(newCost);
	}

}
