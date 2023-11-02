import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private Set<String> uniquePages;
    private Map<String, Integer> operatingSystemStatistics;
    private Set<String> nonExistingPages;
    private Map<String, Integer> browserStatistics;
    private long totalRequests;
    private long totalDataSize;

    public Statistics() {
        uniquePages = new HashSet<>();
        operatingSystemStatistics = new HashMap<>();
        nonExistingPages = new HashSet<>();
        browserStatistics = new HashMap<>();
        totalRequests = 0;
        totalDataSize = 0;
    }

    public void addEntry(LogEntry logEntry) {
        uniquePages.add(logEntry.getRequestPath());

        String os = logEntry.getUserAgent().getOperatingSystem();
        operatingSystemStatistics.put(os, operatingSystemStatistics.getOrDefault(os, 0) + 1);

        if (logEntry.getResponseCode().equals("404")) {
            nonExistingPages.add(logEntry.getRequestPath());
        }

        String browser = logEntry.getUserAgent().getBrowserName();
        browserStatistics.put(browser, browserStatistics.getOrDefault(browser, 0) + 1);

        totalRequests++;
        totalDataSize += logEntry.getDataSize();
    }

    public Set<String> getUniquePages() {
        return uniquePages;
    }

    public Map<String, Integer> getOperatingSystemStatistics() {
        return operatingSystemStatistics;
    }

    public Set<String> getNonExistingPages() {
        return nonExistingPages;
    }

    public Map<String, Integer> getBrowserStatistics() {
        return browserStatistics;
    }

    public double getTrafficRate() {
        if (totalRequests == 0) {
            return 0;
        }
        return (double) totalDataSize / totalRequests;
    }
}