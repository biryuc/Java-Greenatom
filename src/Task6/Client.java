package Task6;

import java.util.concurrent.locks.ReentrantLock;

public class Client {
    private final int id;
    private double balance;
    private final String currency;
    private final ReentrantLock lock;

    public Client(int id, double balance, String currency) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
        this.lock = new ReentrantLock();
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public int getId() { return id; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getCurrency() { return currency; }

    @Override
    public String toString() {
        return String.format("Client{id=%d, balance=%.2f %s}", id, balance, currency);
    }
}