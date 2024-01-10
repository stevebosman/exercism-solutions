import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PythagoreanTriplet {
  private final int a;
  private final int b;
  private final int c;

  PythagoreanTriplet(final int a, final int b, final int c) {
    if (a * a + b * b != c * c) {
      throw new IllegalArgumentException(String.format("(%d, %d, %d) is not a triple", a, b, c));
    }
    this.a = a;
    this.b = b;
    this.c = c;
  }

  static TripletListBuilder makeTripletsList() {
    return new TripletListBuilder();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final PythagoreanTriplet that = (PythagoreanTriplet) o;
    return a == that.a && b == that.b && c == that.c;
  }

  @Override
  public String toString() {
    return "PythagoreanTriplet{" +
            "a=" + a +
            ", b=" + b +
            ", c=" + c +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b, c);
  }

  static class TripletListBuilder {
    private static final double ROOT2 = Math.sqrt(2);
    private static final double SCALE = 2 + ROOT2;
    private Integer sum = null;
    private Integer maxFactor = null;

    TripletListBuilder thatSumTo(final int sum) {
      this.sum = sum;
      if (maxFactor == null) this.maxFactor = sum / 2;
      return this;
    }

    TripletListBuilder withFactorsLessThanOrEqualTo(final int maxFactor) {
      this.maxFactor = maxFactor;
      return this;
    }

    List<PythagoreanTriplet> build() {
      if (this.sum == null) {
        throw new IllegalArgumentException("maxFactor must be set");
      }
      if (this.maxFactor < 0) {
        throw new IllegalArgumentException("maxFactor invalid");
      }

      return IntStream.rangeClosed(3, (int) Math.floor(sum / SCALE))
                      .parallel()
                      .boxed()
                      .map(this::calculateTriplet)
                      .flatMap(Optional::stream)
                      .collect(Collectors.toList());
    }

    /**
     * Based on <a href="https://exercism.org/tracks/java/exercises/pythagorean-triplet/solutions/dsletten">dsletten's solution</a>.
     * Slight change to calculation to speed up numerator calculation.
     * <p>
     * Assuming that a + b + c = n and a² + b² = c², then:
     * b + c = n - a
     * and
     * a² + b² + 2bc + c² = c² + 2bc + c²
     * a² + (b + c)² = 2bc + 2c²
     * a² + (b + c)² = c(2b + 2c)
     * <p>
     * a² + (b + c)²
     * ------------- = c
     * 2(b + c)
     * <p>
     * Substituting n - a for b + c:
     * a² + (n - a)²
     * ------------- = c
     * 2(n - a)
     *
     * @param a short side of triplet.
     * @return triplet
     */
    private Optional<PythagoreanTriplet> calculateTriplet(final int a) {
      final int sumMinusA = sum - a;
      final int numerator = a * a + sumMinusA * sumMinusA;
      final int denominator = 2 * sumMinusA;

      if (numerator % denominator == 0) {
        final int c = numerator / denominator;

        if (c <= maxFactor) {
          final int b = sum - a - c;
          return Optional.of(new PythagoreanTriplet(a, b, c));
        }
      }
      return Optional.empty();
    }
  }
}