import java.time.format.DateTimeFormatter;

public class LogUtils {
    public static DateTimeFormatter getLogDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
}