object Bob {
  private def allcaps(s: String): Boolean = s.toUpperCase() == s
  private def hasLetters(s: String): Boolean = s.exists(_.isLetter) 
  private def isQuestion(s: String): Boolean = s.endsWith("?") 
  
  def response(statement: String): String = {
    val trimmed = statement.trim()
    (trimmed.isEmpty(), hasLetters(trimmed), allcaps(trimmed), isQuestion(trimmed)) match {
      case (true, _, _, _) => "Fine. Be that way!"
      case (_, true, true, true) => "Calm down, I know what I'm doing!"
      case (_, true, true, _) => "Whoa, chill out!"
      case (_, _, _, true) => "Sure."
      case (_, _, _, _) => "Whatever."
    }
  }
}
