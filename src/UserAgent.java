import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
    private final String userAgent;
    private String browserName;
    private String browserVersion;
    private String operatingSystem;
    private String operatingSystemVersion;
    private String referer;
    private String userAgentString;

    private static final String[] BOT_IDENTIFIERS = {"bot", "spider", "crawler"};

    public UserAgent(String userAgent) {
        this.userAgent = userAgent.trim();
        extractBrowserAndOperatingSystem();
        extractReferer();
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

    private void extractReferer() {
        Pattern refererPattern = Pattern.compile("(?i)Referer:\\s*(\\S+)");
        Matcher refererMatcher = refererPattern.matcher(userAgent);
        if (refererMatcher.find()) {
            referer = refererMatcher.group(1);
        } else {
            referer = "Unknown";
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

    public String getReferer() {
        return referer;
    }

    public String getUserAgentString() {
        return userAgentString;
    }

    public boolean isBot() {
        String lowerCaseUserAgent = userAgent.toLowerCase();
        for (String identifier : BOT_IDENTIFIERS) {
            if (lowerCaseUserAgent.contains(identifier)) {
                return true;
            }
        }
        return false;
    }
}