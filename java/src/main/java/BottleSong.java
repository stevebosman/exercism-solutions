import java.util.List;

class BottleSong {
  private static final List<String> NUMBERS = List.of("No", "One", "Two", "Three", "Four", "Five",
                                                      "Six", "Seven", "Eight", "Nine", "Ten");

  String recite(final int startBottles, final int takeDown) {
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < takeDown; i++) {
      if (i != 0) sb.append("\n");
      addVerse(sb, startBottles - i);
    }
    return sb.toString();
  }

  private void addVerse(final StringBuilder sb, final int count) {
    final String startLines = String.format("%s green %s hanging on the wall,\n",
                                            NUMBERS.get(count),
                                            getBottlesText(count));
    final String lastLine = String.format("There'll be %s green %s hanging on the wall.\n",
                                          NUMBERS.get(count - 1)
                                                 .toLowerCase(),
                                          getBottlesText(count - 1));
    sb.append(startLines)
      .append(startLines)
      .append("And if one green bottle should accidentally fall,\n")
      .append(lastLine);
  }

  private String getBottlesText(final int count) {
    return count == 1
           ? "bottle"
           : "bottles";
  }

}