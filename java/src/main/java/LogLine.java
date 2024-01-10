public class LogLine {
  private final String message;
  private final LogLevel level;

  public LogLine(final String logLine) {
    final String abbreviation = (logLine != null && logLine.length()>=5)
                                ? logLine.substring(1, 4)
                                : "";
    this.level = LogLevel.fromAbbreviation(abbreviation);
    this.message = (logLine != null && logLine.length()>=8)
                   ? logLine.substring(7)
                   : "";
  }

  public LogLevel getLogLevel() {
    return level;
  }

  public String getOutputForShortLog() {
    return String.format("%d:%s", getLogLevel().getLevelValue(), message);
  }
}
