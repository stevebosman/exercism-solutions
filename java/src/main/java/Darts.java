class Darts {

  public static final int SCORE_MISS = 0;
  public static final int SCORE_OUTER = 1;
  public static final int SCORE_MIDDLE = 5;
  public static final int SCORE_INNER = 10;
  public static final double RADIUS_INNER_SQR = 1.0;
  public static final double RADIUS_MIDDLE_SQR = 25.0;
  public static final double RADIUS_OUTER_SQR = 100.0;

  int score(final double xOfDart, final double yOfDart) {
    final double dSquared = xOfDart * xOfDart + yOfDart * yOfDart;
    final int result;
    if (dSquared > RADIUS_OUTER_SQR) {
      result = SCORE_MISS;
    } else if (dSquared > RADIUS_MIDDLE_SQR) {
      result = SCORE_OUTER;
    } else if (dSquared > RADIUS_INNER_SQR) {
      result = SCORE_MIDDLE;
    } else {
      result = SCORE_INNER;
    }
    return result;
  }
}
