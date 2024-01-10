import java.util.Arrays;
import java.util.stream.IntStream;

class LargestSeriesProductCalculator {
  private final int[] digits;

  LargestSeriesProductCalculator(final String inputNumber) {
    if (!inputNumber.matches("^\\d*$")) throw new IllegalArgumentException("String to search may only contain digits.");
    digits = inputNumber.chars()
            .map(Character::getNumericValue)
            .toArray();
  }

  long calculateLargestProductForSeriesLength(final int numberOfDigits) {
    if (numberOfDigits < 1) {
      throw new IllegalArgumentException("Series length must be non-negative.");
    }
    if (numberOfDigits > digits.length) {
      throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
    }
    return IntStream.rangeClosed(0, digits.length - numberOfDigits)
                    .boxed()
                    .map(i -> digitProduct(i, numberOfDigits))
                    .max(Long::compareTo)
                    .orElse(0L);
  }

  private long digitProduct(final int index, final int numberOfDigits) {
    return Arrays.stream(digits, index, index + numberOfDigits)
                 .boxed()
                 .map(i -> (long) i)
                 .reduce(1L, (a, b) -> a * b);
  }
}
