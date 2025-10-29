package Task6;

// TransferTransaction.java
public class TransferTransaction extends Transaction {
    private final int receiverId;

    public TransferTransaction(int senderId, int receiverId, double amount) {
        super("TRANSFER", senderId, amount);
        this.receiverId = receiverId;
    }

    @Override
    public void process(Bank bank) {
        bank.transferFunds(clientId, receiverId, amount);
    }

    public int getReceiverId() { return receiverId; }
}
