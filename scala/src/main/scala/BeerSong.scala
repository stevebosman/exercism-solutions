import scala.language.postfixOps

object BeerSong {
  private def bottleCount(verse: Int, upper: Boolean): String = {
    (verse, upper) match {
      case (0, true) =>
        "No more bottles"
      case (0, _) =>
        "no more bottles"
      case (1, _) =>
        "1 bottle"
      case (_, _) =>
        f"${verse} bottles"
    }
  }

  private def lineTwo(verse: Int): String = {
    verse match {
      case 0 =>
        f"Go to the store and buy some more, ${bottleCount(99, false)}"
      case 1 =>
        f"Take it down and pass it around, ${bottleCount(verse - 1, false)}"
      case _ =>
        f"Take one down and pass it around, ${bottleCount(verse - 1, false)}"
    }
  }

  private def verse(verse: Int): String = {
    f"${bottleCount(verse, true)} of beer on the wall, ${bottleCount(verse, false)} of beer.\n${lineTwo(verse)} of beer on the wall.\n"
  }
  
  def recite(startVerse: Int, totalVerses: Int): String = {
    Range(0, totalVerses).map(i => verse(startVerse - i)).mkString("", "\n", "")
  }
}