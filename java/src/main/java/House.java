import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class House {
  private static final List<SongPair> SONG_PAIRS = List.of(
          new SongPair("horse and the hound and the horn", "belonged to"),
          new SongPair("farmer sowing his corn", "kept"),
          new SongPair("rooster that crowed in the morn", "woke"),
          new SongPair("priest all shaven and shorn", "married"),
          new SongPair("man all tattered and torn", "kissed"),
          new SongPair("maiden all forlorn", "milked"),
          new SongPair("cow with the crumpled horn", "tossed"),
          new SongPair("dog", "worried"),
          new SongPair("cat", "killed"),
          new SongPair("rat", "ate"),
          new SongPair("malt", "lay in"),
          new SongPair("house", "Jack built"));

  private static final int COUNT = SONG_PAIRS.size();

  String verse(final int verse) {
    final StringBuilder sb = new StringBuilder("This is");
    SONG_PAIRS.subList(COUNT - verse, COUNT)
              .forEach(e -> sb.append(" the ")
                           .append(e.what)
                           .append(" that ")
                           .append(e.did));
    sb.append(".");
    return sb.toString();
  }

  String verses(final int startVerse, final int endVerse) {
    return IntStream.rangeClosed(startVerse, endVerse)
                    .boxed()
                    .map(this::verse)
                    .collect(Collectors.joining("\n"));
  }

  String sing() {
    return verses(1, COUNT);
  }
}

class SongPair {
  final String what;
  final String did;

  SongPair(final String what, final String did) {
    this.what = what;
    this.did = did;
  }
}