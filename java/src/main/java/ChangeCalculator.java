import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class ChangeCalculator {
  final List<Integer> currencyCoins;

  ChangeCalculator(final List<Integer> currencyCoins) {
    Collections.sort(currencyCoins);
    Collections.reverse(currencyCoins);
    this.currencyCoins = currencyCoins;
  }

  List<Integer> computeMostEfficientChange(final int grandTotal) {
    if (grandTotal < 0) {
      throw new IllegalArgumentException("Negative totals are not allowed.");
    }

    final List<Integer> result = bestChange(grandTotal);

    if (getSum(result) != grandTotal) {
      throw new IllegalArgumentException(
              String.format("The total %d cannot be represented in the given currency.",
                            grandTotal));
    };

    if (result.size() > 1) Collections.sort(result);
    return result;
  }

  List<Integer> bestChange(final int desiredTotal) {
    if (desiredTotal==0) return List.of();
    if (currencyCoins.contains(desiredTotal)) return List.of(desiredTotal);

    List<Integer> currentBest = new ArrayList<>();
    int currentBestSize = Integer.MAX_VALUE;

    for (final int coin: currencyCoins) {
      if (coin < desiredTotal && desiredTotal / coin < currentBestSize) {
        final List<Integer> best = new ArrayList<>();
        best.add(coin);
        best.addAll(bestChange(desiredTotal - coin));
        if (getSum(best) == desiredTotal
                && (currentBestSize == Integer.MAX_VALUE || best.size() < currentBestSize)) {
          currentBest = best;
          currentBestSize = currentBest.size();
        }
      }
    }
    return currentBest;
  }

  private static int getSum(final List<Integer> list) {
    return list.stream().reduce(0, Integer::sum);
  }
}