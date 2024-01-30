import scala.annotation.tailrec

object Series {
  private def toDigitsList(str: String): List[Int] =
    str.map(_.toInt - '0').toList

  @tailrec
  def slices(length: Int, str: String, list: List[List[Int]] = List()): List[List[Int]] =
    if (str.length < length) list
    else slices(length, str.tail, list :+ toDigitsList(str.substring(0, length)))
}