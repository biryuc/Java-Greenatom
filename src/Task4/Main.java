package Task4;

public class Main {
    public static void main(String[] args) {
        ObservableStringBuilder osb = new ObservableStringBuilder("Рукописи");
        osb.addObserver(newValue -> System.out.println("Изменение: " + newValue));

        try {
            osb.append(" не горят");
            osb.replace(0, 9, "Рукописи всё же");
            osb.delete(9, 15);
            osb.reverse();


            osb.insert(100, "ошибка");

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Ошибка индекса: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Общая ошибка: " + e.getMessage());
        }
    }
}