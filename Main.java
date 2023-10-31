import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:/Users/ARLebedev/Downloads/access.log";
        List<String> logFileData = readLogFile(filePath);

        Statistics statistics = new Statistics();

        for (String logString : logFileData) {
            LogEntry logEntry = new LogEntry(logString);
            statistics.addEntry(logEntry);

            UserAgent userAgent = new UserAgent(logEntry.getUserAgent());
            String browserName = userAgent.getBrowserName();
            String operatingSystem = userAgent.getOperatingSystem();
            System.out.println("Browser Name: " + browserName);
            System.out.println("Operating System: " + operatingSystem);
        }

        double trafficRate = statistics.getTrafficRate();
        System.out.println("Traffic Rate: " + trafficRate);
    }

    private static List<String> readLogFile(String filePath) {
        List<String> logFileData = new ArrayList<>();

        try {
            Path path = Path.of(filePath);
            logFileData = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }

        return logFileData;
    }
}