import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {
  private static final String[] DAY_NUMBERS = {
          "first", "second", "third", "fourth", "fifth", "sixth",
          "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};

  private static final String[] PRESENTS = {
          "twelve Drummers Drumming", "eleven Pipers Piping", "ten Lords-a-Leaping", "nine Ladies Dancing",
          "eight Maids-a-Milking", "seven Swans-a-Swimming", "six Geese-a-Laying", "five Gold Rings",
          "four Calling Birds", "three French Hens", "two Turtle Doves"};

  String verse(final int verseNumber) {
    return MessageFormat.format(
            "On the {0} day of Christmas my true love gave to me: {1}{2}a Partridge in a Pear Tree.\n",
            DAY_NUMBERS[verseNumber - 1],
            IntStream.range(12 - verseNumber, 11)
                     .boxed()
                     .map(i -> PRESENTS[i])
                     .collect(Collectors.joining(", ")),
            verseNumber == 1 ? "" : ", and "
    );
  }

  String verses(final int startVerse, final int endVerse) {
    return IntStream.rangeClosed(startVerse, endVerse)
                    .boxed()
                    .map(this::verse)
                    .collect(Collectors.joining("\n"));
  }

  String sing() {
    return verses(1, 12);
  }
}
