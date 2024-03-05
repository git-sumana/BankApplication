import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bankk {
    private Map<String, Double> accounts;

    public Bankk() {
        accounts = new HashMap<>();
    }

    public synchronized void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Double balance = accounts.getOrDefault(accountNumber, 0.0);
        balance += amount;
        accounts.put(accountNumber, balance);
        System.out.println("Deposited " + amount + " into account " + accountNumber);
    }

    public synchronized void withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Double balance = accounts.getOrDefault(accountNumber, 0.0);
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        balance -= amount;
        accounts.put(accountNumber, balance);
        System.out.println("Withdrawn " + amount + " from account " + accountNumber);
    }

    public synchronized double getBalance(String accountNumber) {
        return accounts.getOrDefault(accountNumber, 0.0);
    }
}

class Transaction_ implements Runnable {
    private Bankk bank;
    private String accountNumber;
    private double amount;
    private boolean isDeposit;

    public Transaction_(Bankk bank, String accountNumber, double amount, boolean isDeposit) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    @Override
    public void run() {
        try {
            if (isDeposit) {
                bank.deposit(accountNumber, amount);
            } else {
                bank.withdraw(accountNumber, amount);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}

public class BankApp {
    public static void main(String[] args) {
        Bankk bank = new Bankk();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter account number:");
            String accountNumber = scanner.nextLine();

            System.out.println("Enter amount:");
            double amount = scanner.nextDouble();

            System.out.println("Is it a deposit? (true/false):");
            boolean isDeposit = scanner.nextBoolean();
            scanner.nextLine();

            Thread thread = new Thread(new Transaction_(bank, accountNumber, amount, isDeposit));
            thread.start();

            System.out.println("Transaction in progress...");

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Transaction completed. Account balance for account " + accountNumber + ": " +
                    bank.getBalance(accountNumber));

            System.out.println("Do you want to perform another transaction? (yes/no)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
    }
}