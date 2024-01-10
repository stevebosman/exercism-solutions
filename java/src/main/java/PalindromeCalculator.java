import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

// This version takes several times as long as the previous iteration to run the tests
// submitted because it works and I wanted to try an alternative.
// Major difference is the map holds all palindromes.
class PalindromeCalculator {

  SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(final int minFactor, final int maxFactor) {
    if (minFactor > maxFactor) {
      throw new IllegalArgumentException("invalid input: min must be <= max");
    }

    final SortedMap<Long, List<List<Integer>>> result = new TreeMap<>();
    IntStream.rangeClosed(minFactor, maxFactor)
             .forEach(i -> IntStream.rangeClosed(i, maxFactor)
                                    .forEach(j -> {
                                      final long product = (long) i * j;
                                      if (result.isEmpty() || result.firstKey() >= product || result.lastKey() <= product) {
                                        if (isPalindrome(Long.toString(product))) {
                                          result.computeIfAbsent(product, x -> new ArrayList<>())
                                                .add(List.of(i, j));
                                        }
                                      }
                                    })
             );

    return result;
  }

  private boolean isPalindrome(final String text) {
    final int len = text.length();
    return IntStream.range(0, len / 2)
                    .allMatch(i -> text.charAt(i) == text.charAt(len - 1 - i));
  }

}