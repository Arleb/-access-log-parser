import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String logFilePath = "C:/Users/ARLebedev/Downloads/access.log";

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String logString;
            Statistics statistics = new Statistics();

            while ((logString = reader.readLine()) != null) {
                LogEntry logEntry = new LogEntry(logString);
                statistics.addEntry(logEntry);
            }

            Set<String> uniquePages = statistics.getUniquePages();
            Map<String, Integer> operatingSystemStatistics = statistics.getOperatingSystemStatistics();
            Set<String> nonExistingPages = statistics.getNonExistingPages();
            Map<String, Integer> browserStatistics = statistics.getBrowserStatistics();
            double trafficRate = statistics.getTrafficRate();

            System.out.println("Уникальные страницы: " + uniquePages);
            System.out.println("Статистика операционных систем: " + operatingSystemStatistics);
            System.out.println("Не существующие страницы: " + nonExistingPages);
            System.out.println("Статистика браузеров: " + browserStatistics);
            System.out.println("Трафик: " + trafficRate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}