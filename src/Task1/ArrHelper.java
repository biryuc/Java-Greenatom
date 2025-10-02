import java.util.Arrays;
import java.util.Scanner;

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


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод размера массива
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Ошибка: размер массива должен быть больше 0");
            scanner.close();
            return;
        }

        // Ввод границ генерации
        System.out.print("Введите нижнюю границу : ");
        double min = scanner.nextDouble();
        System.out.print("Введите верхнюю границу : ");
        double max = scanner.nextDouble();

        if (min >= max) {
            System.out.println("Ошибка: нижняя граница должна быть меньше верхней");
            scanner.close();
            return;
        }

        // Выбор типа данных
        System.out.print("Выберите тип данных (1 - целые числа, 2 - дробные числа): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            // Работа с целыми числами
            int[] intArray = generateArrInt(size, (int)min, (int)max);
            processArrInt(intArray);
        } else if (choice == 2) {
            // Работа с дробными числами
            double[] doubleArray = generateArrDouble(size, min, max);
            processArrDouble(doubleArray);
        } else {
            System.out.println("Неверный выбор типа данных");
        }

        scanner.close();
    }

}
