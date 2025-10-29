package Task6;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final ConcurrentHashMap<Integer, Client> clients;
    private final List<Cashier> cashiers;
    private final ConcurrentHashMap<String, Double> exchangeRates;
    private final BlockingQueue<Transaction> transactionQueue;
    private final List<Observer> observers;
    private final ReentrantLock observersLock;
    private final ScheduledExecutorService rateUpdateExecutor;
    private final Random random;

    public Bank(int numberOfCashiers) {
        this.clients = new ConcurrentHashMap<>();
        this.cashiers = new CopyOnWriteArrayList<>();
        this.exchangeRates = new ConcurrentHashMap<>();
        this.transactionQueue = new LinkedBlockingQueue<>();
        this.observers = new ArrayList<>();
        this.observersLock = new ReentrantLock();
        this.random = new Random();

        // Инициализация курсов валют
        initializeExchangeRates();

        // Запуск касс
        initializeCashiers(numberOfCashiers);

        // Запуск автоматического обновления курсов валют
        this.rateUpdateExecutor = Executors.newScheduledThreadPool(1);
        startRateUpdates();
    }

    private void initializeExchangeRates() {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.73);
        exchangeRates.put("JPY", 110.0);
        exchangeRates.put("RUB", 75.0);
    }

    private void initializeCashiers(int numberOfCashiers) {
        for (int i = 1; i <= numberOfCashiers; i++) {
            Cashier cashier = new Cashier(i, this, transactionQueue);
            cashiers.add(cashier);
            cashier.start();
        }
    }

    private void startRateUpdates() {
        rateUpdateExecutor.scheduleAtFixedRate(() -> {
            updateExchangeRates();
        }, 0, 1, TimeUnit.SECONDS); // Обновление каждую секунду
    }

    private void updateExchangeRates() {
        // Симуляция изменения курсов валют
        for (String currency : exchangeRates.keySet()) {
            if (!currency.equals("USD")) {
                double currentRate = exchangeRates.get(currency);
                double change = (random.nextDouble() - 0.5) * 0.1; // ±5%
                double newRate = currentRate * (1 + change);
                exchangeRates.put(currency, newRate);

                notifyObservers(String.format(
                        "Курс обновлен: %s/USD = %.4f", currency, newRate));
            }
        }
    }

    // Методы для работы с клиентами
    public void addClient(Client client) {
        clients.put(client.getId(), client);
        notifyObservers(String.format("Добавлен клиент: %s", client));
    }

    public Client getClient(int clientId) {
        return clients.get(clientId);
    }

    // Основные банковские операции
    public void deposit(int clientId, double amount) {
        Client client = clients.get(clientId);
        if (client != null) {
            client.lock();
            try {
                double newBalance = client.getBalance() + amount;
                client.setBalance(newBalance);
                notifyObservers(String.format(
                        "Пополнение: клиент %d +%.2f %s. Новый баланс: %.2f",
                        clientId, amount, client.getCurrency(), newBalance));
            } finally {
                client.unlock();
            }
        } else {
            notifyObservers(String.format("Ошибка: клиент %d не найден", clientId));
        }
    }

    public void withdraw(int clientId, double amount) {
        Client client = clients.get(clientId);
        if (client != null) {
            client.lock();
            try {
                if (client.getBalance() >= amount) {
                    double newBalance = client.getBalance() - amount;
                    client.setBalance(newBalance);
                    notifyObservers(String.format(
                            "Снятие: клиент %d -%.2f %s. Новый баланс: %.2f",
                            clientId, amount, client.getCurrency(), newBalance));
                } else {
                    notifyObservers(String.format(
                            "Ошибка снятия: недостаточно средств у клиента %d", clientId));
                }
            } finally {
                client.unlock();
            }
        } else {
            notifyObservers(String.format("Ошибка: клиент %d не найден", clientId));
        }
    }

    public void exchangeCurrency(int clientId, String fromCurrency, String toCurrency, double amount) {
        Client client = clients.get(clientId);
        if (client == null) {
            notifyObservers(String.format("Ошибка обмена: клиент %d не найден", clientId));
            return;
        }

        if (!client.getCurrency().equals(fromCurrency)) {
            notifyObservers(String.format(
                    "Ошибка обмена: валюта клиента %s не совпадает с %s",
                    client.getCurrency(), fromCurrency));
            return;
        }

        Double fromRate = exchangeRates.get(fromCurrency);
        Double toRate = exchangeRates.get(toCurrency);

        if (fromRate == null || toRate == null) {
            notifyObservers(String.format(
                    "Ошибка обмена: неверная валюта %s или %s", fromCurrency, toCurrency));
            return;
        }

        client.lock();
        try {
            if (client.getBalance() >= amount) {
                // Конвертация через USD
                double amountInUSD = amount / fromRate;
                double convertedAmount = amountInUSD * toRate;

                client.setBalance(client.getBalance() - amount);

                // Создаем нового клиента с другой валютой или обновляем существующего
                Client newClient = new Client(clientId, convertedAmount, toCurrency);
                clients.put(clientId, newClient);

                notifyObservers(String.format(
                        "Обмен валют: клиент %d %.2f %s -> %.2f %s (курс: %.4f)",
                        clientId, amount, fromCurrency, convertedAmount, toCurrency, toRate/fromRate));
            } else {
                notifyObservers(String.format(
                        "Ошибка обмена: недостаточно средств у клиента %d", clientId));
            }
        } finally {
            client.unlock();
        }
    }

    public void transferFunds(int senderId, int receiverId, double amount) {
        Client sender = clients.get(senderId);
        Client receiver = clients.get(receiverId);

        if (sender == null || receiver == null) {
            notifyObservers(String.format(
                    "Ошибка перевода: отправитель %d или получатель %d не найден",
                    senderId, receiverId));
            return;
        }

        // Блокировка в определенном порядке для избежания deadlock
        Client firstLock = senderId < receiverId ? sender : receiver;
        Client secondLock = senderId < receiverId ? receiver : sender;

        firstLock.lock();
        secondLock.lock();

        try {
            if (sender.getBalance() >= amount) {
                // Если валюты разные, конвертируем сумму
                double transferAmount = amount;
                if (!sender.getCurrency().equals(receiver.getCurrency())) {
                    Double senderRate = exchangeRates.get(sender.getCurrency());
                    Double receiverRate = exchangeRates.get(receiver.getCurrency());

                    if (senderRate != null && receiverRate != null) {
                        double amountInUSD = amount / senderRate;
                        transferAmount = amountInUSD * receiverRate;
                    }
                }

                sender.setBalance(sender.getBalance() - amount);
                receiver.setBalance(receiver.getBalance() + transferAmount);

                notifyObservers(String.format(
                        "Перевод: %d -> %d : %.2f %s -> %.2f %s",
                        senderId, receiverId, amount, sender.getCurrency(),
                        transferAmount, receiver.getCurrency()));
            } else {
                notifyObservers(String.format(
                        "Ошибка перевода: недостаточно средств у отправителя %d", senderId));
            }
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }

    // Методы для работы с очередью транзакций
    public void addTransaction(Transaction transaction) {
        try {
            transactionQueue.put(transaction);
            notifyObservers(String.format(
                    "Транзакция добавлена в очередь: %s", transaction.getType()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            notifyObservers("Ошибка добавления транзакции в очередь");
        }
    }

    // Методы Observer pattern
    public void addObserver(Observer observer) {
        observersLock.lock();
        try {
            observers.add(observer);
        } finally {
            observersLock.unlock();
        }
    }

    public void removeObserver(Observer observer) {
        observersLock.lock();
        try {
            observers.remove(observer);
        } finally {
            observersLock.unlock();
        }
    }

    public void notifyObservers(String message) {
        observersLock.lock();
        try {
            for (Observer observer : observers) {
                observer.update(message);
            }
        } finally {
            observersLock.unlock();
        }
    }

    // Вспомогательные методы
    public void printClientInfo(int clientId) {
        Client client = clients.get(clientId);
        if (client != null) {
            notifyObservers(client.toString());
        }
    }

    public void printAllClients() {
        notifyObservers("=== ИНФОРМАЦИЯ О ВСЕХ КЛИЕНТАХ ===");
        for (Client client : clients.values()) {
            notifyObservers(client.toString());
        }
    }

    public void printExchangeRates() {
        notifyObservers("=== ТЕКУЩИЕ КУРСЫ ВАЛЮТ ===");
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            notifyObservers(String.format("%s/USD: %.4f", entry.getKey(), entry.getValue()));
        }
    }

    public void shutdown() {
        notifyObservers("Завершение работы банка...");

        // Останавливаем обновление курсов
        rateUpdateExecutor.shutdown();

        // Останавливаем кассы
        for (Cashier cashier : cashiers) {
            cashier.stopCashier();
        }

        // Ждем завершения обработки оставшихся транзакций
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        notifyObservers("Банк завершил работу");
    }

    public BlockingQueue<Transaction> getTransactionQueue() {
        return transactionQueue;
    }
}