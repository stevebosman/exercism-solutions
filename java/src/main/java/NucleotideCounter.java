import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

// I prefer iteration 1, but thought I'd try this too.
class NucleotideCounter {
  private static final Set<Character> NUCLEOTIDES = Set.of('C', 'G', 'A', 'T');
  private final Map<Character, Integer> counts;

  NucleotideCounter(final String sequence) {
    if (!sequence.matches("^[CGAT]*$")) {
      throw new IllegalArgumentException("sequence must only contain C, G,A, or T characters");
    }
    counts = NUCLEOTIDES.stream()
                        .collect(Collectors.toMap(Function.identity(),
                                                  n -> count(sequence, n)));
  }

  private int count(final String sequence, final Character base) {
    return (int) sequence.chars()
                         .filter(c -> c == base)
                         .count();
  }

  int count(final char base) {
    return counts.get(base);
  }

  Map<Character, Integer> nucleotideCounts() {
    return counts;
  }

}