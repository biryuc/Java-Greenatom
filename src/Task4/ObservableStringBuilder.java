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
        observers.add(observer);
    }


    public void removeObserver(StringBuilderObserver observer) {
        observers.remove(observer);
    }

    // Уведомление всех наблюдателей
    private void notifyObservers() {
        for (StringBuilderObserver observer : observers) {
            observer.onChange(sb.toString());
        }
    }


    public ObservableStringBuilder append(String str) {
        sb.append(str);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder delete(int start, int end) {
        sb.delete(start, end);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder insert(int offset, String str) {
        sb.insert(offset, str);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder replace(int start, int end, String str) {
        sb.replace(start, end, str);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder reverse() {
        sb.reverse();
        notifyObservers();
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
