class Scrabble {
  final int score;

  Scrabble(final String word) {
    this.score = word.toUpperCase().chars().reduce(0, (a,b) -> a + getLetterScore(b));
  }

  private int getLetterScore(final int codepoint) {
    return switch (codepoint) {
          case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> 1;
          case 'D', 'G' -> 2;
          case 'B', 'C', 'M', 'P' -> 3;
          case 'F', 'H', 'V', 'W', 'Y' -> 4;
          case 'K' -> 5;
          case 'J', 'X' -> 8;
          case 'Q', 'Z' -> 10;
          default -> 0;
      };
  }

  int getScore() {
    return score;
  }

}
