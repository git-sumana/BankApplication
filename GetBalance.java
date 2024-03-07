class GetBalance implements Runnable {
    private Bank bank;
    private String accountNumber;

    public GetBalance(Bank bank, String accountNumber) {
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    public void run() {
        double balance = bank.getBalance(accountNumber);
        System.out.println("Account balance for account " + accountNumber + ": " + balance);
    }
}