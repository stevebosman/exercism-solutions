import java.util.HashSet;
import java.util.Set;

class NaturalNumber {
    private final Classification classification; 
    NaturalNumber(final int number) {
        if (number < 1) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        final int aliquot = aliquot(number);
        if (aliquot < number) {
            this.classification = Classification.DEFICIENT;
        } else if (aliquot > number) {
            this.classification = Classification.ABUNDANT;
        } else {
            this.classification = Classification.PERFECT;
        }
    }

    Classification getClassification() {
        return classification;
    }

    /**
     * Calculates the aliquot number of {@code number}.
     */
    private static int aliquot(final int number) {
        return number == 1 
               ? 0 : 
               1 + nonTrivialFactors(number).stream()
                                            .mapToInt(n -> n)
                                            .sum();
    }

    /**
     * Returns non-trivial factors of {@code number},
     * excludes 1 and {@code number}.
     */
    private static Set<Integer> nonTrivialFactors(final int number) {
        final Set<Integer> result = new HashSet<>();
        final int max = (int)Math.sqrt(number);
        for (int i = 2; i < max + 1; i++) {
            if (number % i == 0) {
                result.add(i);
                result.add(number / i);
            }
        }
        return result;
    }
}
