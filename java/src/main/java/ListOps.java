import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class ListOps {

  static <T> List<T> append(final List<T> list1, final List<T> list2) {
    return Stream.concat(list1.stream(), list2.stream()).toList();
  }

  static <T> List<T> concat(final List<List<T>> listOfLists) {
    return listOfLists.stream().flatMap(lt -> lt.stream()).toList();
  }

  static <T> List<T> filter(final List<T> list, final Predicate<T> predicate) {
    return list.stream().filter(predicate).toList();
  }

  static <T> int size(final List<T> list) {
    return list.size();
  }

  static <T, U> List<U> map(final List<T> list, final Function<T, U> transform) {
    return list.stream().map(transform).toList();
  }

  static <T> List<T> reverse(final List<T> list) {
    return list.reversed();
  }

  static <T, U> U foldLeft(final List<T> list, final U initial, final BiFunction<U, T, U> f) {
    U acc = initial;
    for (final T t : list)
      acc = f.apply(acc, t);
    return acc;
  }

  static <T, U> U foldRight(final List<T> list, final U initial, final BiFunction<T, U, U> f) {
    U acc = initial;
    for (final T t : reverse(list))
      acc = f.apply(t, acc);
    return acc;
  }

  private ListOps() {
    // No instances.
  }

}