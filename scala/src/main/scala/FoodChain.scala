
object FoodChain {
  private val ANIMALS = List("fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse")
  private val EFFECTS = List(
    "",
    "It wriggled and jiggled and tickled inside her.",
    "How absurd to swallow a bird!",
    "Imagine that, to swallow a cat!",
    "What a hog, to swallow a dog!",
    "Just opened her throat and swallowed a goat!",
    "I don't know how she swallowed a cow!",
    "\nShe's dead, of course!"
  )
  private val I_KNOW = "I know an old lady who swallowed a "
  private val THAT_WRIGGLED = "spider that wriggled and jiggled and tickled inside her"
  private val SHE_SWALLOWED = "\nShe swallowed the %s to catch the %s."
  private val ENDING = "\nI don't know why she swallowed the fly. Perhaps she'll die."

  private def toCatchAnimal(verse: Int, animal: Int) =
    if (animal == 1 && verse > 1) THAT_WRIGGLED
    else ANIMALS(animal)

  private def sheSwallowedLine(verse: Int, animal: Int) =
    SHE_SWALLOWED.format(ANIMALS(animal), toCatchAnimal(verse, animal - 1))

  private def sheSwallowedLines(verse: Int) = verse match {
    case 0 => ENDING
    case 7 => EFFECTS(verse)
    case _ => (verse to 1 by -1)
      .map(animal => sheSwallowedLine(verse, animal))
      .mkString("\n" + EFFECTS(verse), "", "") +
      ENDING
  }

  private def verse(verse: Int): String =
    I_KNOW + ANIMALS(verse) + "." + sheSwallowedLines(verse)

  def recite(start: Int, end: Int): String = (start to end).map(v => verse(v - 1)).mkString("", "\n\n", "\n\n")
}