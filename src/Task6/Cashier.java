package Task6;

import java.util.concurrent.BlockingQueue;

public class Cashier extends Thread {
    private final int id;
    private final Bank bank;
    private volatile boolean active;
    private final BlockingQueue<Transaction> transactionQueue;

    public Cashier(int id, Bank bank, BlockingQueue<Transaction> transactionQueue) {
        this.id = id;
        this.bank = bank;
        this.transactionQueue = transactionQueue;
        this.active = true;
        this.setName("Cashier-" + id);
    }

    @Override
    public void run() {
        bank.notifyObservers(String.format("Касса %d запущена", id));

        while (active || !transactionQueue.isEmpty()) {
            try {
                Transaction transaction = transactionQueue.take();
                bank.notifyObservers(String.format(
                        "Касса %d начала обработку транзакции: %s", id, transaction.getType()));

                transaction.process(bank);

                bank.notifyObservers(String.format(
                        "Касса %d завершила обработку транзакции: %s", id, transaction.getType()));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                bank.notifyObservers(String.format(
                        "Касса %d была прервана", id));
                break;
            } catch (Exception e) {
                bank.notifyObservers(String.format(
                        "Ошибка в кассе %d: %s", id, e.getMessage()));
            }
        }

        bank.notifyObservers(String.format("Касса %d остановлена", id));
    }

    public void stopCashier() {
        this.active = false;
        this.interrupt();
    }
}
