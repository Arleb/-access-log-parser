import java.util.HashMap;
import java.util.Map;

public class BrowserUtils {
    private static final Map<String, String> BROWSER_NAMES = new HashMap<>();
    static {
        BROWSER_NAMES.put("trident", "Internet Explorer");
        BROWSER_NAMES.put("msie", "Internet Explorer");
        BROWSER_NAMES.put("edge", "Microsoft Edge");
        BROWSER_NAMES.put("edg", "Microsoft Edge");
        BROWSER_NAMES.put("chrome", "Google Chrome");
        BROWSER_NAMES.put("safari", "Safari");
        BROWSER_NAMES.put("firefox", "Mozilla Firefox");
        BROWSER_NAMES.put("opera", "Opera");
    }

    public static String getBrowserName(String userAgent) {
        for (String keyword : BROWSER_NAMES.keySet()) {
            if (userAgent.toLowerCase().contains(keyword)) {
                return BROWSER_NAMES.get(keyword);
            }
        }
        return "Unknown";
    }

    public static String getOperatingSystem(String userAgent) {
        if (userAgent.toLowerCase().contains("windows")) {
            return "Windows";
        } else if (userAgent.toLowerCase().contains("mac")) {
            return "Mac";
        } else if (userAgent.toLowerCase().contains("linux")) {
            return "Linux";
        } else if (userAgent.toLowerCase().contains("android")) {
            return "Android";
        } else if (userAgent.toLowerCase().contains("ios")) {
            return "iOS";
        } else {
            return "Unknown";
        }
    }
}