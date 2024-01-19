object House {
  private val who = List(
    "house", "malt", "rat",
    "cat", "dog", "cow with the crumpled horn",
    "maiden all forlorn", "man all tattered and torn", "priest all shaven and shorn",
    "rooster that crowed in the morn", "farmer sowing his corn", "horse and the hound and the horn",
  )

  private val what = List(
    "Jack built.", "lay in", "ate", "killed", "worried", "tossed",
    "milked", "kissed", "married", "woke", "kept", "belonged to",
  )

  private def verse(number: Int): String =
    "This is " + (number to 1 by -1).map(i => who(i - 1) + " that " + what(i - 1)).mkString("the ", " the ", "")

  def recite(start: Int, end: Int): String = (start to end).map(verse).mkString("", "\n", "\n\n")
}
