import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private Set<String> uniquePages;
    private Map<String, Integer> operatingSystems;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.now();
        maxTime = LocalDateTime.MIN;
        uniquePages = new HashSet<>();
        operatingSystems = new HashMap<>();
    }

    public void addEntry(LogEntry logEntry) {
        totalTraffic += logEntry.getDataSize();

        LocalDateTime entryTime = logEntry.getDateTime();
        if (entryTime.isBefore(minTime)) {
            minTime = entryTime;
        }
        if (entryTime.isAfter(maxTime)) {
            maxTime = entryTime;
        }

        String requestPath = logEntry.getRequestPath();
        String operatingSystem = logEntry.getOperatingSystem();

        if (requestPath != null && logEntry.getResponseCode().equals("200")) {
            uniquePages.add(requestPath);
        }

        if (operatingSystem != null) {
            operatingSystems.put(operatingSystem, operatingSystems.getOrDefault(operatingSystem, 0) + 1);
        }
    }

    public Set<String> getUniquePages() {
        return uniquePages;
    }

    public Map<String, Double> getOperatingSystemStatistics() {
        Map<String, Double> statistics = new HashMap<>();

        int totalEntries = operatingSystems.values().stream().mapToInt(Integer::intValue).sum();

        for (String os : operatingSystems.keySet()) {
            double percentage = (double) operatingSystems.get(os) / totalEntries;
            statistics.put(os, percentage);
        }

        return statistics;
    }

    public double getTrafficRate() {
        long totalHours = Math.max(1, minTime.until(maxTime, java.time.temporal.ChronoUnit.HOURS));
        return (double) totalTraffic / totalHours;
    }
}