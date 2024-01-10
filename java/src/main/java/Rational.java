import java.util.Objects;

class Rational {
  private static final Rational ONE = new Rational(1, 1);
  private final int numerator, denominator;

  Rational(int numerator, int denominator) {
    // simplify
    if (denominator < 0) {
      numerator *= -1;
      denominator *= -1;
    }

    final int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
    this.numerator = numerator / gcd;
    this.denominator = denominator / gcd;
  }

  /**
   * GCD by Euclid's algorithm.
   *
   * @param n1 first value.
   * @param n2 second value.
   * @return Greatest common divisor.
   */
  private int gcd(final int n1, final int n2) {
    if (n2 == 0) {
      return n1;
    }
    return gcd(n2, n1 % n2);
  }

  int getNumerator() {
    return numerator;
  }

  int getDenominator() {
    return denominator;
  }

  Rational add(final Rational other) {
    return new Rational(numerator * other.denominator + denominator * other.numerator, denominator * other.denominator);
  }

  Rational subtract(final Rational other) {
    return new Rational(numerator * other.denominator - denominator * other.numerator, denominator * other.denominator);
  }

  Rational multiply(final Rational other) {
    return new Rational(numerator * other.numerator, denominator * other.denominator);
  }

  Rational divide(final Rational other) {
    return new Rational(numerator * other.denominator, denominator * other.numerator);
  }

  Rational abs() {
    return new Rational(Math.abs(numerator), Math.abs(denominator));
  }

  Rational pow(final int power) {
    if (power == 0) return ONE;
    if (power < 0) new Rational((int) Math.pow(denominator, power), (int) Math.pow(numerator, power));
    return new Rational((int) Math.pow(numerator, power), (int) Math.pow(denominator, power));
  }

  double toDouble() {
    return (double) numerator / (double) denominator;
  }

  double exp(final double exponent) {
    return Math.pow(Math.pow(exponent, 1.0 / denominator), numerator);
  }

  @Override
  public String toString() {
    return String.format("%d/%d", this.getNumerator(), this.getDenominator());
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Rational other) {
      return this.getNumerator() == other.getNumerator()
              && this.getDenominator() == other.getDenominator();
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getNumerator(), this.getDenominator());
  }
}
