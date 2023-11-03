import java.util.*;

public class Statistics {
    private Set<String> uniquePages;
    private Map<String, Integer> operatingSystemStatistics;
    private Set<String> nonExistingPages;
    private Map<String, Integer> browserStatistics;
    private long totalRequests;
    private long totalDataSize;
    private Map<Integer, Integer> visitCountMap;
    private Set<String> referringDomains;
    private Map<String, Integer> userVisitCountMap;
    private Set<String> botUserAgents;

    public Statistics() {
        uniquePages = new HashSet<>();
        operatingSystemStatistics = new HashMap<>();
        nonExistingPages = new HashSet<>();
        browserStatistics = new HashMap<>();
        totalRequests = 0;
        totalDataSize = 0;
        visitCountMap = new HashMap<>();
        referringDomains = new HashSet<>();
        userVisitCountMap = new HashMap<>();
        botUserAgents = new HashSet<>();
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

        if (!logEntry.getUserAgent().isBot()) {
            int second = logEntry.getDateTime().getSecond();
            visitCountMap.put(second, visitCountMap.getOrDefault(second, 0) + 1);
            referringDomains.add(getDomainFromReferer(logEntry.getUserAgent().getReferer()));
            String ip = logEntry.getIpAddress();
            userVisitCountMap.put(ip, userVisitCountMap.getOrDefault(ip, 0) + 1);
        } else {
            addBotUserAgent(logEntry.getUserAgent().getUserAgentString());
        }
    }

    public int getMaxVisitsPerSecond() {
        int maxVisits = 0;
        for (int visits : visitCountMap.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }
        return maxVisits;
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
            return 0.0;
        }
        double uniqueVisits = userVisitCountMap.size();
        return (uniqueVisits / totalRequests) * 100;
    }

    public Set<String> getSitesWithReferringLinks() {
        return referringDomains;
    }

    public int getMaxVisitsByUser() {
        int maxVisits = 0;
        for (int visits : userVisitCountMap.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }
        return maxVisits;
    }

    public void addBotUserAgent(String userAgent) {
        botUserAgents.add(userAgent);
    }

    private String getDomainFromReferer(String referer) {
        if (referer != null && !referer.isEmpty()) {
            int startIndex = referer.indexOf("//") + 2;
            int endIndex = referer.indexOf("/", startIndex);
            if (endIndex == -1) {
                endIndex = referer.length();
            }
            return referer.substring(startIndex, endIndex);
        }
        return null;
    }
}