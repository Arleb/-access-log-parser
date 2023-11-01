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
    private Set<String> nonExistingPages;
    private Map<String, Integer> browserStats;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.now();
        maxTime = LocalDateTime.MIN;
        uniquePages = new HashSet<>();
        operatingSystems = new HashMap<>();
        nonExistingPages = new HashSet<>();
        browserStats = new HashMap<>();
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
        String responseCode = logEntry.getResponseCode();
        String browserName = logEntry.getBrowserName();

        if (requestPath != null && responseCode.equals("404")) {
            nonExistingPages.add(requestPath);
        }

        if (operatingSystem != null) {
            operatingSystems.put(operatingSystem, operatingSystems.getOrDefault(operatingSystem, 0) + 1);
        }

        if (browserName != null) {
            browserStats.put(browserName, browserStats.getOrDefault(browserName, 0) + 1);
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

    public Set<String> getNonExistingPages() {
        return nonExistingPages;
    }

    public Map<String, Double> getBrowserStatistics() {
        Map<String, Double> statistics = new HashMap<>();

        int totalEntries = browserStats.values().stream().mapToInt(Integer::intValue).sum();

        for (String browser : browserStats.keySet()) {
            double percentage = (double) browserStats.get(browser) / totalEntries;
            statistics.put(browser, percentage);
        }

        return statistics;
    }

    public double getTrafficRate() {
        long totalHours = Math.max(1, minTime.until(maxTime, java.time.temporal.ChronoUnit.HOURS));
        return (double) totalTraffic / totalHours;
    }
}