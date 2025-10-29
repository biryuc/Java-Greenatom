package Task6;

import java.util.concurrent.TimeUnit;

public class BankExample {
    public static void main(String[] args) {
        System.out.println("=== ЗАПУСК БАНКОВСКОЙ СИМУЛЯЦИИ ===\n");

        // Создаем банк с 3 кассами
        Bank bank = new Bank(3);

        // Добавляем логгер
        Logger logger = new Logger();
        bank.addObserver(logger);

        // Добавляем клиентов
        bank.addClient(new Client(1, 1000.0, "USD"));
        bank.addClient(new Client(2, 500.0, "EUR"));
        bank.addClient(new Client(3, 2000.0, "GBP"));
        bank.addClient(new Client(4, 300.0, "JPY"));
        bank.addClient(new Client(5, 1500.0, "RUB"));

        // Ждем немного перед началом операций
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Выполняем различные операции
        bank.addTransaction(new DepositTransaction(1, 500.0));
        bank.addTransaction(new WithdrawTransaction(2, 100.0));
        bank.addTransaction(new ExchangeTransaction(3, "GBP", "EUR", 200.0));
        bank.addTransaction(new TransferTransaction(1, 2, 150.0));
        bank.addTransaction(new DepositTransaction(4, 1000.0));
        bank.addTransaction(new TransferTransaction(5, 1, 300.0));
        bank.addTransaction(new ExchangeTransaction(2, "EUR", "USD", 50.0));

        // Ждем обработки транзакций
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Показываем текущее состояние
        bank.printAllClients();
        bank.printExchangeRates();

        // Добавляем еще транзакций
        bank.addTransaction(new TransferTransaction(3, 4, 100.0));
        bank.addTransaction(new ExchangeTransaction(1, "USD", "GBP", 200.0));

        // Ждем обработки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Финальное состояние
        bank.printAllClients();

        // Завершаем работу
        bank.shutdown();

        System.out.println("\n=== СИМУЛЯЦИЯ ЗАВЕРШЕНА ===");
    }
}