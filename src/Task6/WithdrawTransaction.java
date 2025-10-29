package Task6;

// WithdrawTransaction.java
public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(int clientId, double amount) {
        super("WITHDRAW", clientId, amount);
    }

    @Override
    public void process(Bank bank) {
        bank.withdraw(clientId, amount);
    }
}
