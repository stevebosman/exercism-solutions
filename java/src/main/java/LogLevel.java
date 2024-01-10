public enum LogLevel {
  UNKNOWN("", 0),
  TRACE("TRC", 1),
  DEBUG("DBG", 2),
  INFO("INF", 4),
  WARNING("WRN", 5),
  ERROR("ERR", 6),
  FATAL("FTL", 42);

  private final String abbreviation;
  private final int levelValue;

  LogLevel(final String abbreviation, final int levelValue) {
    this.abbreviation = abbreviation;
    this.levelValue = levelValue;
  }

  public static LogLevel fromAbbreviation(final String abbreviation) {
    LogLevel result = UNKNOWN;
    for (final LogLevel logLevel : values()) {
      if (logLevel.abbreviation.equals(abbreviation)) {
        result = logLevel;
        break;
      }
    }
    return result;
  }

  public int getLevelValue() {
    return levelValue;
  }
}
