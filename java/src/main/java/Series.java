import java.util.List;
import java.util.stream.IntStream;

class Series {
  private final String string;

  Series(final String string) {
    this.string = string;
  }

  List<String> slices(final int num) {
    if (num <= 0) throw new IllegalArgumentException("Slice size is too small.");
    if (num > string.length()) throw new IllegalArgumentException("Slice size is too big.");
    return IntStream.rangeClosed(0, string.length() - num)
                    .boxed()
                    .map(i -> string.substring(i, i + num))
                    .toList();
  }
}
