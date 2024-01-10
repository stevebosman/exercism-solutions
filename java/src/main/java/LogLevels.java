import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLevels {

    private static final Pattern MESSAGE_PATTERN = Pattern.compile("^\\[([^]]+)]:\\s+(.+)\\s*$");
    
    public static String message(String logLine) {
        final Matcher m = MESSAGE_PATTERN.matcher(logLine);
        if (m.matches()) {
            return m.group(2).trim();
        }
        return "";
    }

    public static String logLevel(String logLine) {
        final Matcher m = MESSAGE_PATTERN.matcher(logLine);
        if (m.matches()) {
            return m.group(1).toLowerCase();
        }
        return "";
    }

    public static String reformat(String logLine) {
        return String.format("%s (%s)", message(logLine), logLevel(logLine));
    }
}
