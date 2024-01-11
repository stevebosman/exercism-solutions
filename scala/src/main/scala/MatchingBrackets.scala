import scala.annotation.tailrec

object MatchingBrackets {

  /**
   * @return Returns a tuple (Boolean, List) - if first part of tuple is true, then we know the brackets are unmatched
   *         and can exit fast.
   */
  private def nextBrackets(openBrackets: List[String], str: String): (Boolean, List[String]) = {
    (openBrackets.lastOption, str) match {
      case (Some("["), "]") | (Some("{"), "}") | (Some("("), ")") => (false, openBrackets.dropRight(1))
      case (_, "]") | (_, "}") | (_, ")") => (true, openBrackets)
      case (_, _) => (false, openBrackets :+ str)
    }
  }

  @tailrec
  private def checkBrackets(input: String, openBrackets: List[String]): Boolean = {
    if (input.isEmpty) return openBrackets.isEmpty
    val next = nextBrackets(openBrackets, input.substring(0, 1))
    if (next._1) return false
    checkBrackets(input.substring(1), next._2)
  }

  def isPaired(brackets: String): Boolean = {
    val normalised:String = brackets.replaceAll("[^{}()\\[\\]]+", "")
    checkBrackets(normalised, List.empty)
  }
}
