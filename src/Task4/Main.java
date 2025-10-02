package Task4;

public class Main {
    public static void main(String[] args) {
        ObservableStringBuilder osb = new ObservableStringBuilder("Hello");

        // Добавляем наблюдателя
        osb.addObserver(newValue -> System.out.println("StringBuilder изменен: " + newValue));

        // Проводим изменения
        osb.append(" World");
        osb.insert(5, ",");
        osb.replace(0, 5, "Hi");
        osb.delete(2, 3);
        osb.reverse();
    }
}