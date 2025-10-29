package Task4;

import java.util.ArrayList;
import java.util.List;




public class ObservableStringBuilder {
    private StringBuilder sb;
    private List<StringBuilderObserver> observers;

    public ObservableStringBuilder() {
        sb = new StringBuilder();
        observers = new ArrayList<>();
    }

    public ObservableStringBuilder(String str) {
        sb = new StringBuilder(str);
        observers = new ArrayList<>();
    }


    public void addObserver(StringBuilderObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Наблюдатель не может быть null");
        }
        observers.add(observer);
    }


    public void removeObserver(StringBuilderObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Наблюдатель не может быть null");
        }
        observers.remove(observer);
    }

    // Уведомление всех наблюдателей
    private void notifyObservers() {
        for (StringBuilderObserver observer : observers) {
            observer.onChange(sb.toString());
        }
    }


    public ObservableStringBuilder append(String str) {
        try {
            sb.append(str);
            notifyObservers();
        }catch (Exception e){
            throw new RuntimeException("Ошибка при добавлении строки: " + str, e);
        }
        return this;
    }

    public ObservableStringBuilder delete(int start, int end) {
        try {
            sb.delete(start, end);

            notifyObservers();
        }catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException(
                    String.format("Некорректный диапазон удаления: start=%d, end=%d, длина=%d",
                            start, end, sb.length()));
        }
        catch (Exception e ){
            throw new RuntimeException("Ошибка при удалении", e);
        }
        return this;
    }

    public ObservableStringBuilder insert(int offset, String str) {

     try{
        sb.insert(offset, str);
        notifyObservers();
    } catch (StringIndexOutOfBoundsException e) {
        throw new StringIndexOutOfBoundsException(
                String.format("Некорректная позиция вставки: offset=%d, длина=%d",
                        offset, sb.length()));
    } catch (Exception e) {
        throw new RuntimeException("Ошибка при вставке", e);
    }

        try{
            sb.insert(offset, str);
            notifyObservers();
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException(
                    String.format("Некорректная позиция вставки: offset=%d, длина=%d",
                            offset, sb.length()));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при вставке", e);
        }

        return this;
    }

    public ObservableStringBuilder replace(int start, int end, String str) {

    try{
        sb.replace(start, end, str);
        notifyObservers();
    } catch (StringIndexOutOfBoundsException e) {
        throw new StringIndexOutOfBoundsException(
                String.format("Некорректный диапазон замены: start=%d, end=%d, длина=%d",
                        start, end, sb.length()));
    } catch (Exception e) {
        throw new RuntimeException("Ошибка при замене", e);
    }

        try{
            sb.replace(start, end, str);
            notifyObservers();
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException(
                    String.format("Некорректный диапазон замены: start=%d, end=%d, длина=%d",
                            start, end, sb.length()));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при замене", e);
        }

        return this;
    }

    public ObservableStringBuilder reverse() {
        try{
            sb.reverse();
            notifyObservers();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при реверсе строки", e);
        }
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}