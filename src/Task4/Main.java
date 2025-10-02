package Task4;

public class Main {
    public static void main(String[] args) {
        ObservableStringBuilder osb = new ObservableStringBuilder("Рукописи");

        // Добавляем наблюдателя
        osb.addObserver(newValue -> System.out.println("Изменение: " + newValue));

        // Делаем изменения
        osb.append(" не");
        osb.append(" горят");

        // Попробуем заменить часть строки
        osb.replace(0, 9, "Рукописи всё же");

        // Удалим добавленное " всё же"
        osb.delete(9, 15);

        // Для красоты развернём строку
        osb.reverse();
    }
}
