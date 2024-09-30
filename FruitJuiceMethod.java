import javax.swing.*;
import java.text.DecimalFormat;

public class FruitJuiceMethod {

    private static DispenserType apple = new DispenserType();
    private static DispenserType orange = new DispenserType(60.00);
    private static DispenserType mango = new DispenserType(75.00);
    private static DispenserType punch = new DispenserType(80.00);
    private static CashRegister vendor = new CashRegister();

    public static void main(String[] args) {
        boolean continuing;

        JOptionPane.showMessageDialog(null, "Fruit Juice Machine");

        do {
            showStock();
            selectProduct();
            continuing = programTerminator();
        } while (continuing);

        JOptionPane.showMessageDialog(null, "Thank you for using the Fruit Juice Machine!");
    }

    public static void showStock() {
        StringBuilder stockInfo = new StringBuilder();
        stockInfo.append("Select from the juices available:\n")
	        .append("ID - | - ITEM NAME - | - ITEM QTY\n")
	        .append(String.format("1    |  Apple Juice  | %d\n", apple.getNoOfItems()))
	        .append(String.format("2    |  Orange Juice | %d\n", orange.getNoOfItems()))
	        .append(String.format("3    |  Mango Juice  | %d\n", mango.getNoOfItems()))
	        .append(String.format("4    |  Punch Juice  | %d\n", punch.getNoOfItems()));

        JOptionPane.showMessageDialog(null, stockInfo.toString());
    }

    public static void selectProduct() {
    	String input = JOptionPane.showInputDialog( "Enter juice choice (input num):");
        int choice = Integer.parseInt(input);

        switch (choice) {
            case 1:
                processOrder(apple);
                break;
            case 2:
                processOrder(orange);
                break;
            case 3:
                processOrder(mango);
                break;
            case 4:
                processOrder(punch);
                break;
            default:
                JOptionPane.showMessageDialog(null, "The inputted choice is invalid. Please try again.");
                break;
        }
    }

    private static void processOrder(DispenserType juice) {
        if (juice.verifyStock()) {
            String countInput = JOptionPane.showInputDialog("How many items would you like to purchase?");
            int count = receiveCount(juice.getNoOfItems(), countInput);

            double actualCost = count * juice.getCost();
            DecimalFormat df = new DecimalFormat("0.00");

            String cashInput = JOptionPane.showInputDialog("Total cost to pay: Php. " + df.format(actualCost) + "\nEnter amount to pay: Php.");
            double cash = receiveCash(actualCost, cashInput);

            juice.makeSale(count);
            vendor.acceptAmount(actualCost);

            double change = returnChange(cash, actualCost);
            JOptionPane.showMessageDialog(null, "Your change is: Php. " + df.format(change));

            double currentBalance = vendor.getCurrentBalance();
            JOptionPane.showMessageDialog(null, "Current balance in register: Php. " + df.format(currentBalance));
        }
    }

    public static int receiveCount(int stock, String input) {
        int newValue;
        try {
            newValue = Integer.parseInt(input);
            if (newValue > 0 && newValue <= stock) {
                return newValue;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid quantity between 1 and " + stock + ".");
                return receiveCount(stock, JOptionPane.showInputDialog("Enter quantity (1 to " + stock + "):"));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            return receiveCount(stock, JOptionPane.showInputDialog("Enter quantity (1 to " + stock + "):"));
        }
    }

    public static double receiveCash(double actualCost, String input) {
        
    	DecimalFormat df = new DecimalFormat("0.00");
    	double newValue;
    	
        try {
            newValue = Double.parseDouble(input);
            if (verifyCashAmount(newValue, actualCost)) {
                return newValue;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter an amount greater than or equal to Php. " + df.format(actualCost));
                return receiveCash(actualCost, JOptionPane.showInputDialog("Enter amount to pay (at least Php. " + df.format(actualCost) + "): Php."));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid cash amount.");
            return receiveCash(actualCost, JOptionPane.showInputDialog("Enter amount to pay (at least Php. " + df.format(actualCost) + "): Php."));
        }
    }

    private static boolean verifyCashAmount(double newValue, double actualCost) {
        return newValue >= actualCost;
    }

    private static double returnChange(double newCash, double actualCost) {
        return newCash - actualCost;
    }

    private static boolean programTerminator() {
    	boolean validInput = true;
    	
    	String[] options = {"Yes", "No"};
        int choice = JOptionPane.showOptionDialog(null,
            "Do you like to purchase again? (Y/N) ",
            "Continuing Choice",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        switch (choice) {
	        case 0:
	        	break;
	        case 1:
	        	validInput = false;
	        	break;
        }
        
        return validInput;
    }
}
