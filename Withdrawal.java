class Withdrawal implements Runnable {
    private Bank bank;
    private String accountNumber;
    private double amount;

    public Withdrawal(Bank bank, String accountNumber, double amount) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public void run() {
        try {
            bank.withdraw(accountNumber, amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }
}
