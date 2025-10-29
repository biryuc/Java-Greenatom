package Task6;

public abstract class Transaction {
    protected final String type;
    protected final int clientId;
    protected final double amount;

    public Transaction(String type, int clientId, double amount) {
        this.type = type;
        this.clientId = clientId;
        this.amount = amount;
    }

    public abstract void process(Bank bank);

    // Геттеры
    public String getType() { return type; }
    public int getClientId() { return clientId; }
    public double getAmount() { return amount; }
}

