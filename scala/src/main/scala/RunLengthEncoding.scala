import scala.annotation.tailrec

object RunLengthEncoding {
  @tailrec
  def encode(str: String, result: String = ""): String =
    if (str.isEmpty) result
    else str.takeWhile(_ == str.head).length match {
      case 1 => encode(str.tail, result + str.head)
      case len => encode(str.substring(len), result + len + str.head)
    }

  private def numberWithSize(n: Int): (Int, Int) =
    (n, n.toString.length)

  private def repsCount(str: String): (Int, Int) =
    if (str.head.isDigit) numberWithSize(str.takeWhile(_.isDigit).toInt)
    else numberWithSize(1)

  @tailrec
  def decode(str: String, result: String = ""): String =
    if (str.isEmpty) result
    else repsCount(str) match {
      case (1, 1) => decode(str.tail, result + str.head)
      case (n, l) => decode(str.substring(l + 1), result + str(l).toString * n)
    }

}