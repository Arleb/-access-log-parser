import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        System.out.println("Список уникальные страницы:");
        for (String page : uniquePages) {
            System.out.println(page);
        }

        Map<String, Double> operatingSystemStats = statistics.getOperatingSystemStatistics();
        System.out.println("ОС:");
        for (String os : operatingSystemStats.keySet()) {
            double percentage = operatingSystemStats.get(os);
            System.out.println(os + ": " + percentage);
        }

        Set<String> nonExistingPages = statistics.getNonExistingPages();
        System.out.println("Список несуществующих страниц:");
        for (String page : nonExistingPages) {
            System.out.println(page);
        }

        Map<String, Double> browserStats = statistics.getBrowserStatistics();
        System.out.println("Статистика браузера:");
        for (String browser : browserStats.keySet()) {
            double percentage = browserStats.get(browser);
            System.out.println(browser + ": " + percentage);
        }

        double trafficRate = statistics.getTrafficRate();
        System.out.println("Траффик: " + trafficRate);
    }

    private static List<String> readLogFile(String filePath) {
        List<String> logFileData = null;

        try {
            Path path = Path.of(filePath);
            logFileData = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return logFileData;
    }
}