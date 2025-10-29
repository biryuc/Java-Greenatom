package Task6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements Observer {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void update(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.printf("[%s] LOG: %s%n", timestamp, message);
    }
}