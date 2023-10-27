import java.time.LocalDateTime;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.now();
        maxTime = LocalDateTime.MIN;
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
    }

    public double getTrafficRate() {
        long totalHours = Math.max(1, minTime.until(maxTime, java.time.temporal.ChronoUnit.HOURS));
        return (double) totalTraffic / totalHours;
    }
}