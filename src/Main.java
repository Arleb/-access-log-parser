import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:/Users/ARLebedev/Downloads/access.log";
        List<String> logFileData = readLogFile(filePath);

        Statistics statistics = new Statistics();

        for (String logString : logFileData) {
            LogEntry logEntry = new LogEntry(logString);
            statistics.addEntry(logEntry);
        }

        Set<String> uniquePages = statistics.getUniquePages();
        System.out.println("Список уникальных страниц:");
        for (String page : uniquePages) {
            System.out.println(page);
        }

        Map<String, Double> operatingSystemStats = statistics.getOperatingSystemStatistics();
        System.out.println("ОС:");
        for (String os : operatingSystemStats.keySet()) {
            double percentage = operatingSystemStats.get(os);
            System.out.println(os + ": " + percentage);
        }

        double trafficRate = statistics.getTrafficRate();
        System.out.println("Траффик: " + trafficRate);
    }

    private static List<String> readLogFile(String filePath) {
        List<String> logFileData = new ArrayList<>();

        try {
            Path path = Path.of(filePath);
            if (Files.exists(path)) {
                logFileData = Files.readAllLines(path);
            } else {
                System.err.println("Лог файл не существует.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения лог-файла: " + e.getMessage());
        }

        return logFileData;
    }
}