package Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> transports = createTransports();

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showAllTransports(transports);
                case 2 -> operateTransport(transports, scanner, Car.class);
                case 3 -> operateTransport(transports, scanner, Bicycle.class);
                case 4 -> operateTransport(transports, scanner, Boat.class);
                case 5 -> operateTransport(transports, scanner, Airplane.class);
                case 6 -> showTransportInfo(transports, scanner);
                case 7 -> showFuelStatistics(transports);
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static List<Vehicle> createTransports() {
        List<Vehicle> transports = new ArrayList<>();
        transports.add(new Car("Toyota Camry", 4));
        transports.add(new Car("Tesla Model S", 4));
        transports.add(new Bicycle("Stels Navigator", 2));
        transports.add(new Boat("Титаник", 52310));
        transports.add(new Airplane("Boeing 737", 12500));
        return transports;
    }

    private static void printMenu() {
        System.out.println("\n=== СИСТЕМА УПРАВЛЕНИЯ ТРАНСПОРТОМ ===");
        System.out.println("1. Показать весь транспорт");
        System.out.println("2. Управлять автомобилем");
        System.out.println("3. Управлять велосипедом");
        System.out.println("4. Управлять кораблем");
        System.out.println("5. Управлять самолетом");
        System.out.println("6. Информация о транспорте");
        System.out.println("7. Статистика по топливу");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void showAllTransports(List<Vehicle> transports) {
        System.out.println("\n=== ВЕСЬ ТРАНСПОРТ ===");
        for (int i = 0; i < transports.size(); i++) {
            Vehicle t = transports.get(i);
            String type = t.getClass().getSimpleName();
            System.out.println((i + 1) + ". " + type + " - " + t.getModel() +
                    " (" + t.getFuelType().getName() + ")");
        }
    }

    private static <T extends Vehicle> void operateTransport(
            List<Vehicle> transports, Scanner scanner, Class<T> transportType) {

        T transport = findTransport(transports, transportType);
        if (transport == null) {
            System.out.println("Транспорт не найден!");
            return;
        }

        System.out.println("\nУправляем: " + transport.getModel());
        transport.start();

        if (transport instanceof Car car) {
            System.out.print("Введите скорость (км/ч): ");
            int speed = scanner.nextInt();
            car.accelerate(speed);
        } else if (transport instanceof Bicycle bike) {
            System.out.print("Введите скорость (км/ч): ");
            int speed = scanner.nextInt();
            bike.pedal(speed);
        } else if (transport instanceof Boat ship) {
            System.out.print("Введите скорость (узлов): ");
            double speed = scanner.nextDouble();
            ship.sail(speed);
        } else if (transport instanceof Airplane plane) {
            plane.takeOff();
            plane.land();
        }

        transport.stop();
    }

    private static void showTransportInfo(List<Vehicle> transports, Scanner scanner) {
        showAllTransports(transports);
        System.out.print("Выберите транспорт: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < transports.size()) {
            transports.get(index).displayInfo();
        }
    }

    private static void showFuelStatistics(List<Vehicle> transports) {
        System.out.println("\n=== СТАТИСТИКА ПО ТОПЛИВУ ===");
        for (FuelType fuelType : FuelType.values()) {
            long count = transports.stream()
                    .filter(t -> t.getFuelType() == fuelType)
                    .count();
            if (count > 0) {
                System.out.println(fuelType.getName() + ": " + count + " транспорт(а)");
            }
        }
    }

    private static <T extends Vehicle> T findTransport(
            List<Vehicle> transports, Class<T> transportType) {

        return transports.stream()
                .filter(transportType::isInstance)
                .map(transportType::cast)
                .findFirst()
                .orElse(null);
    }
}
