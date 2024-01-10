import java.util.Arrays;


class ResistorColorDuo {
    int value(final String[] colors) {
        return Arrays.stream(colors)
                     .limit(2)
                     .map(this::colorCode)
                     .reduce((a, b) -> 10 * a + b)
                     .orElse(-1);
    }

    private int colorCode(final String color) {
        return ResistorBand.valueOf(color)
                           .ordinal();
    }
}

enum ResistorBand {
  black,
  brown,
  red,
  orange,
  yellow,
  green,
  blue,
  violet,
  grey,
  white;
}

