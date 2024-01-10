import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BookStore {
  public static final double BOOK_COST = 8.00;
  private static final double[] PRICES = {
          0.00,
          BOOK_COST,
          2 * BOOK_COST * 0.95,
          3 * BOOK_COST * 0.90,
          4 * BOOK_COST * 0.80,
          5 * BOOK_COST * 0.75
  };

  private static Map<Integer, Integer> countBooks(final List<Integer> books) {
    final Map<Integer, Integer> counts = new HashMap<>();
    books.forEach(i -> counts.put(i, counts.computeIfAbsent(i, x -> 0) + 1));
    return counts;
  }

  private static int extractCount(final int size, final Map<Integer, Integer> counts) {
    int result = 0;
    while (counts.size() == size) {
      result++;
      counts.entrySet()
            .forEach(e -> e.setValue(e.getValue() - 1));
      counts.entrySet()
            .removeIf(e -> e.getValue() == 0);
    }
    return result;
  }

  private static int[] calculateDiscountGroups(final Map<Integer, Integer> counts) {
    // one more than needed as ignoring index zero makes the logic easier to follow.
    final int[] groups = new int[6];
    for (int i = groups.length - 1; i > 0; i--) {
      groups[i] = extractCount(i, counts);
    }

    // two 4s is better than a 3 and a 5.
    while (groups[5] > 0 && groups[3] > 0) {
      groups[3]--;
      groups[5]--;
      groups[4] += 2;
    }

    return groups;
  }

  private static double calculateCost(final int[] groups) {
    double cost = 0;
    for (int i = 1; i < groups.length; i++) {
      cost += groups[i] * PRICES[i];
    }
    return cost;
  }

  double calculateBasketCost(final List<Integer> books) {
    final Map<Integer, Integer> counts = countBooks(books);
    final int[] groups = calculateDiscountGroups(counts);
    return calculateCost(groups);
  }
}