import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Transaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        // Adding some dummy thinsg
        bank.addAccount(new BankAccount(1234567890, "password1", 0));
        bank.addAccount(new BankAccount(1234567809, "password2", 100));

        while (true) {
            System.out.println("Enter your 10 digit account number:");
            long accountNumber = scanner.nextLong();
            System.out.println("Enter your password:");
            String password = scanner.next();

            BankAccount account = bank.findAccount(accountNumber);
            if (account != null && account.validatePassword(password)) {
                System.out.println("Login Successful.");
                performTransactions(account, scanner);
            } else {
                System.out.println("Invalid account number or password. Transaction terminated.");
                break;
            }

            System.out.println("Do you want to perform another transaction? (yes/no)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("no")) {
                break;
            }
        }
    }

    public static void performTransactions(BankAccount account, Scanner scanner) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        while (true) {
            System.out.println("Choose a transaction:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    executor.submit(new DepositTransaction(account, depositAmount));
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:");
                    double withdrawAmount = scanner.nextDouble();
                    executor.submit(new WithdrawTransaction(account, withdrawAmount));
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 4:
                    executor.shutdown();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
