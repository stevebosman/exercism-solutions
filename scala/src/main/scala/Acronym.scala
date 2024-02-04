object Acronym {
  def abbreviate(phrase: String): String =
    phrase.replaceAll("([A-Za-z])[']([A-Za-z])", "$1$2")
      .replaceAll("([A-Za-z])[A-Za-z]*", "$1")
      .replaceAll("[^A-Za-z]+", "")
      .toUpperCase
}
