import java.util.Scanner;

public class FruitJuiceMethodConsole {

    private static DispenserType apple = new DispenserType();
    private static DispenserType orange = new DispenserType(60.00);
    private static DispenserType mango = new DispenserType(75.00);
    private static DispenserType punch = new DispenserType(80.00);
    
    private static CashRegister vendor = new CashRegister();
    
    private static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean continuing;
        
        System.out.println("Fruit Juice Machine\n");
        
        do {
            showStock();
            selectProduct();
            continuing = programTerminator();
        } while (continuing);
        
        System.out.println("Thank you for using the Fruit Juice Machine!");
        keyboard.close();
    }
    
    public static void showStock() {
        System.out.println("\nSelect from the juices available:");
        System.out.println("ID - | - ITEM NAME - | - ITEM QTY");
        System.out.printf("1    |  Apple Juice  | %d%n", apple.getNoOfItems());
        System.out.printf("2    |  Orange Juice | %d%n", orange.getNoOfItems());
        System.out.printf("3    |  Mango Juice  | %d%n", mango.getNoOfItems());
        System.out.printf("4    |  Punch Juice  | %d%n", punch.getNoOfItems());
    }
    
    public static void selectProduct() {
        System.out.print("Enter juice choice (input num): ");
        int choice;
        
        // Validate choice input
        while (!keyboard.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            keyboard.next(); // Clear invalid input
        }
        choice = keyboard.nextInt();
        keyboard.nextLine(); // Clear the newline character
        
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
                System.out.println("The inputted choice is invalid. Please try again.\n");
                break;
        }
    }
    
    private static void processOrder(DispenserType juice) {
        if (juice.verifyStock()) {
            System.out.print("How many items would you like to purchase? ");
            int count = receiveCount(juice.getNoOfItems());
            
            double actualCost = count * juice.getCost();
            System.out.printf("Total cost to pay: Php. %.2f%n", actualCost);
            
            System.out.print("Enter amount to pay: Php. ");
            double cash = receiveCash(actualCost);

            juice.makeSale(count);
            vendor.acceptAmount(actualCost);

            double change = returnChange(cash, actualCost);
            System.out.printf("Your change is: Php. %.2f%n", change);
            
            double currentBalance = vendor.getCurrentBalance();
            System.out.printf("Current balance in register: Php. %.2f%n", currentBalance);
        }
    }
    
    public static int receiveCount(int stock) {
        int newValue;
        while (true) {
            System.out.print("Enter quantity (1 to " + stock + "): ");
            if (keyboard.hasNextInt()) {
                newValue = keyboard.nextInt();
                keyboard.nextLine(); // Clear the newline character
                if (newValue > 0 && newValue <= stock) {
                    return newValue;
                } else {
                    System.out.println("Please enter a valid quantity between 1 and " + stock + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                keyboard.next(); // Clear invalid input
            }
        }
    }
    
    public static double receiveCash(double actualCost) {
        double newValue;
        while (true) {
            if (keyboard.hasNextDouble()) {
                newValue = keyboard.nextDouble();
                keyboard.nextLine(); // Clear the newline character
                if (verifyCashAmount(newValue, actualCost)) {
                    return newValue;
                } else {
                    System.out.println("Please enter an amount greater than or equal to the total cost.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid cash amount.");
                keyboard.next(); // Clear invalid input
            }
        }
    }
    
    private static boolean verifyCashAmount(double newValue, double actualCost) {
        return newValue >= actualCost;
    }
    
    private static double returnChange(double newCash, double actualCost) {
        return newCash - actualCost;
    }
    
    private static boolean programTerminator() {
        String newChoice;
        while (true) {
            System.out.print("Do you like to purchase again? (Y/N) ");
            newChoice = keyboard.nextLine().trim();  // Use trim to remove whitespace

            if (newChoice.equalsIgnoreCase("Y")) {
                return true;
            } else if (newChoice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
    }
}
