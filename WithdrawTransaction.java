public class WithdrawTransaction implements Runnable {
    private BankAccount account;
    private double amount;

    public WithdrawTransaction(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.withdraw(amount);
        System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
    }
}
