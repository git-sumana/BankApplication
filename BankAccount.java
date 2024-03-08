public class BankAccount {
    private long accountNumber;
    private String password;
    private double balance;

    public BankAccount(long accountNumber, String password, double balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}
