import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DiamondPrinter {

    public static final char STARTER = 'A';

    List<String> printToList(final char a) {
    final int mid = (a - STARTER);
    final int end = 1 + 2 * mid;
    return IntStream.rangeClosed(0, end - 1)
                    .boxed()
                    .map(i -> drawLine(i > mid
                                       ? end - i - 1
                                       : i, end))
                    .collect(Collectors.toList());
  }

  private String drawLine(final int i, final int width) {
    final char c = (char) (STARTER + i);
    if (i == 0) {
      final String spacer = " ".repeat(width / 2);
      return spacer + c + spacer;
    } else {
      final String spacer = " ".repeat(width / 2 - i);
      return spacer + c + " ".repeat(2 * i - 1) + c + spacer;
    }
  }

}
