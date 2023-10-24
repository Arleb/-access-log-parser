import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String path = "C:/Users/ARLebedev/Downloads/access.log";
        int googleBotCount = 0;
        int yandexBotCount = 0;
        int totalRequests = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalRequests++;
                String userAgent = extractUserAgent(line);
                if (userAgent.contains("GoogleBot")) {
                    googleBotCount++;
                } else if (userAgent.contains("YandexBot")) {
                    yandexBotCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        double googleBotPercentage = calculatePercentage(googleBotCount, totalRequests);
        double yandexBotPercentage = calculatePercentage(yandexBotCount, totalRequests);

        System.out.println("Доля запросов от GoogleBot: " + googleBotPercentage + "%");
        System.out.println("Доля запросов от YandexBot: " + yandexBotPercentage + "%");
    }

    private static String extractUserAgent(String line) {
        String userAgent = "";
        int start = line.indexOf("\"Mozilla");
        int end = line.indexOf(")\"");
        if (start >= 0 && end >= 0 && end >= start) {
            userAgent = line.substring(start, end + 2);
        }
        return userAgent;
    }

    private static double calculatePercentage(int count, int total) {
        return (double) count / total * 100;
    }
}