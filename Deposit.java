class Deposit implements Runnable {
    private Bank bank;
    private String accountNumber;
    private double amount;

    public Deposit(Bank bank,String accountNumber, double amount) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public void run() {
        try {
            bank.deposit(accountNumber, amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }
}
