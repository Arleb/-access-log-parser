import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class LogEntry {
    private String ipAddress;
    private LocalDateTime dateTime;
    private String requestMethod;
    private String requestPath;
    private String responseCode;
    private long dataSize;
    private String referer;
    private String userAgent;
    private String pageAddress;
    private String operatingSystem;
    private String browserName;

    public LogEntry(String logString) {
        try {
            String[] logParts = logString.split("\\s+");

            if (logParts.length >= 12) {
                this.ipAddress = logParts[0];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MMM/yyyy:HH:mm:ss][dd/MM/yyyy:HH:mm:ss]", Locale.ENGLISH);
                this.dateTime = LocalDateTime.parse(logParts[3].replaceAll("\\[|\\]", ""), formatter);
                String[] requestParts = logParts[5].split("\"");
                if (requestParts.length >= 3) {
                    this.requestMethod = requestParts[0];
                    this.requestPath = requestParts[1];
                }

                this.responseCode = logParts[8].replaceAll("\"", "");
                this.dataSize = logParts[9].equals("-") ? 0 : Long.parseLong(logParts[9]);
                this.referer = logParts[10].replaceAll("\"", "");
                this.userAgent = logParts[11].replaceAll("\"", "");
            } else {
                throw new IllegalArgumentException("Invalid log entry format");
            }

        } catch (DateTimeParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid log entry format", e);
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getPageAddress() {
        return pageAddress;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setPageAddress(String pageAddress) {
        this.pageAddress = pageAddress;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddress='" + ipAddress + '\'' +
                ", dateTime=" + dateTime +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", dataSize=" + dataSize +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", pageAddress='" + pageAddress + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", browserName='" + browserName + '\'' +
                '}';
    }
}