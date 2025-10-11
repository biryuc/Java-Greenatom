package Task1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;
public class ArrHelper {

    public static int[] generateArrInt(int size, int min,int max){
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * (max - min + 1)) + min;
        }
        return arr;

    }

    public static double[] generateArrDouble(int size, double min,double max){
        double[] arr = new double[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (Math.random() * (max - min)) + min;
        }
        return arr;

    }

    public static int[] sortAsc(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                }
            }
        }
        return arr;
    }

    public static double[] sortAsc(double[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    double tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                }
            }
        }
        return arr;
    }

    public static int[] sortDesc(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                }
            }
        }
        return arr;
    }

    public static double[] sortDesc(double[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    double tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                }
            }
        }
        return arr;
    }

    public static int getMax(int[] arr) {
        int size = arr.length;
        arr = sortAsc(arr);
        return arr[size-1];
    }

    public static double getMax(double[] arr) {
        int size = arr.length;
        arr = sortAsc(arr);
        return arr[size-1];
    }

    public static int getMin(int[] arr) {
        int size = arr.length;
        arr = sortAsc(arr);
        return arr[0];
    }
    public static double getMin(double[] arr) {
        int size = arr.length;
        arr = sortAsc(arr);
        return arr[0];
    }

    public static double getAverage(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }

    public static double getAverage(double[] arr) {
        double sum = 0;
        for (double num : arr) {
            sum += num;
        }
        return  sum / arr.length;
    }

    public static void processArrInt(int[] array) {
        System.out.println("\nСгенерированный массив: " + Arrays.toString(array));

        int max = getMax(array);
        int min = getMin(array);
        double average = getAverage(array);

        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.println("Среднее значение: " + average);

        // Сортировка по возрастанию
        int[] ascending = sortAsc(array.clone());
        System.out.println("Отсортированный по возрастанию: " + Arrays.toString(ascending));

        // Сортировка по убыванию
        int[] descending = sortDesc(array.clone());
        System.out.println("Отсортированный по убыванию: " + Arrays.toString(descending));
    }

    // Обработка массива дробных чисел
    public static void processArrDouble(double[] array) {
        System.out.println("\nСгенерированный массив: " + Arrays.toString(array));

        double max = getMax(array);
        double min = getMin(array);
        double average = getAverage(array);

        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.println("Среднее значение: " + average);

        // Сортировка по возрастанию
        double[] ascending = sortAsc(array.clone());
        System.out.println("Отсортированный по возрастанию: " + Arrays.toString(ascending));

        // Сортировка по убыванию
        double[] descending = sortDesc(array.clone());
        System.out.println("Отсортированный по убыванию: " + Arrays.toString(descending));
    }



    public static int readPositiveInt(Scanner scanner,String prompt, String errorMessage){
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        if (size <= 0) throw new IllegalArgumentException(errorMessage);
        return size;
    }

    private static void validateRange(double min, double max) {
        if (min >= max) throw new IllegalArgumentException(
                "Ошибка: нижняя граница должна быть меньше верхней");
    }

    private static void processChoice(Scanner scanner, int size, double min, double max) {
        System.out.print("Выберите тип данных (1 - целые числа, 2 - дробные числа): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> processArrInt(generateArrInt(size, (int)min, (int)max));
            case 2 -> processArrDouble(generateArrDouble(size, min, max));
            default -> throw new IllegalArgumentException("Неверный выбор типа данных");
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        try {
            int size = readPositiveInt(scanner,"Введите размер массива: ","Ошибка: размер массива должен быть больше 0");

            System.out.print("Введите нижнюю границу : ");
            double min = scanner.nextDouble();
            System.out.print("Введите верхнюю границу : ");
            double max = scanner.nextDouble();

            validateRange( min,max);

            processChoice( scanner,  size,  min,  max);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: введите корректное число!");
            scanner.next();
        } catch (Exception e) {
<<<<<<< Updated upstream
                throw new RuntimeException(e);
=======
            throw new RuntimeException(e);
>>>>>>> Stashed changes
        } finally {
            scanner.close();
        }

    }

}