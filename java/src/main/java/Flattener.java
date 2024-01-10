import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Flattener {
  <T> List<T> flatten(final List<T> list) {
    return list.stream()
               .filter(Objects::nonNull)
               .flatMap(e -> e instanceof List
                             ? flatten((List<T>) e).stream()
                             : Stream.of(e))
               .collect(Collectors.toList());
  }
}