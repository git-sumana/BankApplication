import java.util.*;
import java.util.concurrent.*;

public class Bank {
    private ConcurrentSkipListSet<BankAccount> accounts;

    public Bank() {
        accounts = new ConcurrentSkipListSet<>(Comparator.comparingDouble(BankAccount::getBalance));
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount findAccount(long accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void sortAccountsByBalance() {
        for (BankAccount account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
        }
    }
}
