package Task6;

// ExchangeTransaction.java
public class ExchangeTransaction extends Transaction {
    private final String fromCurrency;
    private final String toCurrency;

    public ExchangeTransaction(int clientId, String fromCurrency, String toCurrency, double amount) {
        super("EXCHANGE", clientId, amount);
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    @Override
    public void process(Bank bank) {
        bank.exchangeCurrency(clientId, fromCurrency, toCurrency, amount);
    }

    public String getFromCurrency() { return fromCurrency; }
    public String getToCurrency() { return toCurrency; }
}
