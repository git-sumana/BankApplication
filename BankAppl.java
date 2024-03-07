import java.util.Scanner;

public class BankAppl {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean continueTransaction = true;

        while (continueTransaction) {
            System.out.println("Enter account number:");
            String accountNumber = scanner.nextLine();

            if (accountNumber.length() != 10) {
                System.out.println("Account number must be 10 characters long.");
                continue; // Skip to the next iteration of the loop
            }

            System.out.println("Select operation (1: Deposit, 2: Withdrawal, 3: Get Balance):");
            int operation = scanner.nextInt();
            scanner.nextLine();

            switch (operation) {
                case 1:
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    Thread depositThread = new Thread(new Deposit(bank, accountNumber, depositAmount));
                    depositThread.start();
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:");
                    double withdrawalAmount = scanner.nextDouble();
                    Thread withdrawalThread = new Thread(new Withdrawal(bank, accountNumber, withdrawalAmount));
                    withdrawalThread.start();
                    break;
                case 3:
                    Thread getBalanceThread = new Thread(new GetBalance(bank, accountNumber));
                    getBalanceThread.start();
                    break;
                default:
                    System.out.println("Invalid operation selected");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                System.out.println("Do you want to perform another transaction? (yes/no)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    continueTransaction = false;
                    break;
                } else if (response.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
        scanner.close();
    }
}