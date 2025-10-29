package Task6;

// DepositTransaction.java
public class DepositTransaction extends Transaction {
    public DepositTransaction(int clientId, double amount) {
        super("DEPOSIT", clientId, amount);
    }

    @Override
    public void process(Bank bank) {
        bank.deposit(clientId, amount);
    }
}
