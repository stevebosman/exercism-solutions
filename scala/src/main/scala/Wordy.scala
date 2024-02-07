import scala.annotation.tailrec
import scala.util.matching.Regex

object Wordy {
  def answer(q: String): Option[Int] = {
    def operation(op: String): (Int, Int) => Int =
      op match {
        case "plus" => (A: Int, B: Int) => A + B
        case "minus" => (A: Int, B: Int) => A - B
        case "multiplied by" => (A: Int, B: Int) => A * B
        case "divided by" => (A: Int, B: Int) => A / B
      }

    @tailrec
    def calculate(result: Option[Int], fragment: String): Option[Int] = {
      if (result.isEmpty || fragment.isEmpty) result
      else {
        val nextMatch: Regex = "^([ a-z]+) ([+-]?\\d+)( .*|$)".r
        val matches = nextMatch.findAllMatchIn(fragment).toList
        if (matches.isEmpty) None
        else {
          val h = matches.head
          val op = operation(h.group(1))
          val n = h.group(2).toInt
          val nextFragment = h.group(3).trim
          calculate(Some(op.apply(result.get, n)), nextFragment)
        }
      }
    }

    val qMatch: Regex = "^What is ([+-]?\\d+)( .*|)\\?$".r
    val matches = qMatch.findAllMatchIn(q).toList
    if (matches.isEmpty) None
    else calculate(Some(matches.head.group(1).toInt), matches.head.group(2).trim)
  }
}