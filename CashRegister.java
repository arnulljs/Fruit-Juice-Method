public class CashRegister {
	private double cashOnHand;
	
	public double getCurrentBalance() {
		return cashOnHand;
	}
	
	public void setCurrentBalance(double newCash) {
		cashOnHand = newCash;
	}
	
	public void acceptAmount(double amountIn) {
		setCurrentBalance(cashOnHand + amountIn);
	}
	
	public CashRegister() {
		cashOnHand = 5000.00;
	}
	
	public CashRegister(double cashIn) {
		cashOnHand = cashIn;
	}
	
}
