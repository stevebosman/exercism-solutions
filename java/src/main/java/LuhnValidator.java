import java.util.stream.IntStream;

class LuhnValidator {

  boolean isValid(final String candidate) {
    final String normalized = candidate.replaceAll(" ", "");
    return normalized.matches("^[0-9]{2,}$")
            && IntStream.range(0, normalized.length())
                        .map(i -> luhnValue(i, normalized))
                        .sum() % 10 == 0;
  }

  private int luhnValue(final int i, final String candidate) {
    int val = candidate.charAt(candidate.length() - i - 1) - '0';
    if (i % 2 == 1) {
      val *= 2;
      if (val >= 10) val -= 9;
    }
    return val;
  }

}
