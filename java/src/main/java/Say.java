import java.util.List;

public class Say {
  /**
   * Words representing 10s.
   */
  static final List<String> TENS = List.of(
          "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
  );

  /**
   * Zero text.
   */
  private static final String ZERO_TEXT = "zero";
    
  /**
   * Words representing units.
   */
  private static final List<String> UNITS = List.of("", "one", "two", "three", "four", "five", "six", "seven", "eight",
                                                    "nine");
    
  /**
   * Words representing numbers between 10 and 19.
   */
  private static final List<String> TEENS = List.of(
          "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
  );
    

  public String say(final long number) {
    if (number < 0) throw new IllegalArgumentException("number must be non-negative");
    if (number >= 1_000_000_000_000L) throw new IllegalArgumentException("number too large");
    String result = "";  
    if (number == 0) {
        result = "zero";
    } else {
        int subThousand = (int)(number % 1000);
        int thousands = (int)(number / 1000 % 1000);
        int millions = (int)(number / 1_000_000 % 1000);
        int billions = (int)(number / 1_000_000_000 % 1000);
        
        if (billions > 0) {
            result = result + getSubThousandText(billions) + " billion ";
        }
        if (millions > 0) {
            result = result + getSubThousandText(millions) + " million ";
        }
        if (thousands > 0) {
            result = result + getSubThousandText(thousands) + " thousand ";
        }
        if (subThousand > 0) {
            result = result + getSubThousandText(subThousand);
        }
    }
    return result.trim();
  }

  /**
   * Determine text for numbers between 1 and 999.
   *
   * @param subThousand Number to convert - must be between 1 and 999.
   * @return converted number.
   */
  private String getSubThousandText(final int subThousand) {
    final StringBuilder result = new StringBuilder();
    final int subHundred = subThousand % 100;
    if (subThousand >= 100) {
      result.append(getSubHundredText(subThousand / 100))
            .append(" ")
            .append("hundred");
      if (subHundred > 0) {
        result.append(" ");
      }
    }
    if (subHundred > 0) {
      result.append(getSubHundredText(subHundred));
    }
    return result.toString();
  }

  /**
   * Determine text for numbers between 1 and 99.
   *
   * @param subHundred Number to convert - must be between 1 and 99.
   * @return converted number.
   */
  private String getSubHundredText(final int subHundred) {
    final String result;
    if (subHundred >= 10 && subHundred < 20) {
      result = TEENS.get(subHundred - 10);
    } else {
      final int tens = subHundred / 10;
      final String tensString = TENS.get(tens);

      final int units = subHundred % 10;
      final String unitsString = UNITS.get(units);

      if (tens == 0) {
        result = unitsString;
      } else if (units == 0) {
        result = tensString;
      } else {
        result = tensString + "-" + unitsString;
      }
    }
    return result;
  }
}
