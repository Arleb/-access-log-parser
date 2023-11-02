import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private LocalDateTime dateTime;
    private String ipAddress;
    private String requestMethod;
    private String requestPath;
    private String responseCode;
    private long dataSize;
    private UserAgent userAgent;

    public LogEntry(String logString) {
        String[] logParts = logString.split(" ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.US);
        this.dateTime = LocalDateTime.parse(logParts[3].substring(1), formatter);

        this.ipAddress = logParts[0];
        this.requestMethod = logParts[5].replaceAll("\"", "");
        this.requestPath = logParts[6];
        this.responseCode = logParts[8];

        if (!logParts[9].equals("-")) {
            this.dataSize = Long.parseLong(logParts[9]);
        } else {
            this.dataSize = 0;
        }

        if (logParts.length >= 12) {
            String userAgentString = logString.substring(logString.indexOf(logParts[11]));
            this.userAgent = new UserAgent(userAgentString);
        } else {
            this.userAgent = new UserAgent("");
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public long getDataSize() {
        return dataSize;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}