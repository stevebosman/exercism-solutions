class RnaTranscription {

  String transcribe(final String dnaStrand) {
    return dnaStrand.codePoints()
                    .map(RnaTranscription::toRnaNucleotide)
                    .collect(StringBuilder::new,
                             StringBuilder::appendCodePoint, 
                             StringBuilder::append)
                    .toString();
  }

  private static int toRnaNucleotide(final int dnaNucleotide) {
    return switch (dnaNucleotide) {
      case 'G' -> 'C';
      case 'C' -> 'G';
      case 'T' -> 'A';
      case 'A' -> 'U';
      default -> throw new IllegalArgumentException(
              "DNA nucleotides can only contain 'G', 'C', 'U', or 'A' characters, and not " + dnaNucleotide);
    };
  }

}
