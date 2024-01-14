object Isogram {
  def isIsogram(s: String): Boolean = {
    val normalised = s.toLowerCase.filter(_.isLetter)
    normalised.toSet.size == normalised.length
  }
}