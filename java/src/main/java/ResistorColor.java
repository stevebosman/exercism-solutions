import java.util.Arrays;

class ResistorColor {

    private static final String[] BAND_COLOURS = Arrays.stream(ResistorBand.values())
                                                      .map(Enum::toString)
                                                      .toArray(String[]::new);

    int colorCode(final String color) {
        return ResistorBand.valueOf(color).ordinal();
    }

    String[] colors() {
        return BAND_COLOURS;
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
