public class DepositTransaction implements Runnable {
    private BankAccount account;
    private double amount;

    public DepositTransaction(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.deposit(amount);
        System.out.println("Deposit successful. Current balance: " + account.getBalance());
    }
}
