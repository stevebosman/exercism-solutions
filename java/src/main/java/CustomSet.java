import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class CustomSet<T> {
  private final Set<T> set = new HashSet<>();

  CustomSet(final Collection<T> data) {
    set.addAll(data);
  }

  boolean isEmpty() {
    return set.isEmpty();
  }

  boolean contains(final T element) {
    return set.contains(element);
  }

  boolean isDisjoint(final CustomSet<T> other) {
    return isEmpty() || other.isEmpty() || getIntersection(other).size() == 0;
  }

  private int size() {
    return set.size();
  }

  boolean add(final T element) {
    return set.add(element);
  }

  CustomSet<T> getIntersection(final CustomSet<T> other) {
    return new CustomSet<>(other.set.stream()
                                    .filter(set::contains)
                                    .collect(Collectors.toSet()));
  }

  CustomSet<T> getUnion(final CustomSet<T> other) {
    final Set<T> ts = new HashSet<>(set);
    ts.addAll(other.set);
    return new CustomSet<>(ts);
  }

  CustomSet<T> getDifference(final CustomSet<T> other) {
    return new CustomSet<>(set.stream()
                              .filter(e -> !other.set.contains(e))
                              .collect(Collectors.toSet()));
  }

  boolean isSubset(final CustomSet<T> other) {
    return other.isEmpty() || getIntersection(other).size() == other.size();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final CustomSet<?> customSet = (CustomSet<?>) o;
    return Objects.equals(set, customSet.set);
  }

  @Override
  public int hashCode() {
    return Objects.hash(set);
  }
}
