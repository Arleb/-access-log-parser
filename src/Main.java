import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:/Users/ARLebedev/Downloads/access.log";
        List<String> logFileData = readLogFile(filePath);

        for (String logString : logFileData) {
            UserAgent userAgent = new UserAgent(logString);
            String browserName = userAgent.getBrowserName();
            String browserVersion = userAgent.getBrowserVersion();
            String operatingSystem = userAgent.getOperatingSystem();
            String operatingSystemVersion = userAgent.getOperatingSystemVersion();

            System.out.println("Browser: " + browserName + " " + browserVersion);
            System.out.println("OS: " + operatingSystem + " " + operatingSystemVersion);
            System.out.println();
        }
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