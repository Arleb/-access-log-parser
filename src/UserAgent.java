import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
    private final String userAgent;
    private String browserName;
    private String browserVersion;
    private String operatingSystem;
    private String operatingSystemVersion;

    public UserAgent(String userAgent) {
        this.userAgent = userAgent.trim();
        extractBrowserAndOperatingSystem();
    }

    private void extractBrowserAndOperatingSystem() {
        Pattern browserPattern = Pattern.compile("(?i)(chrome|firefox|safari|edge|opera|ie)[/\\s]+(\\S+)");
        Matcher browserMatcher = browserPattern.matcher(userAgent);
        if (browserMatcher.find()) {
            browserName = browserMatcher.group(1);
            browserVersion = browserMatcher.group(2);
        } else {
            browserName = "Unknown";
            browserVersion = "Unknown";
        }

        Pattern osPattern = Pattern.compile("(?i)(windows|mac|linux|android|ios)(?: (\\S+))?");
        Matcher osMatcher = osPattern.matcher(userAgent);
        if (osMatcher.find()) {
            operatingSystem = osMatcher.group(1);
            operatingSystemVersion = osMatcher.group(2);
        } else {
            operatingSystem = "Unknown";
            operatingSystemVersion = "Unknown";
        }
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }
}