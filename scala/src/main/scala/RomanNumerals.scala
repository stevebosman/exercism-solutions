import scala.annotation.tailrec
import scala.collection.immutable.ListMap

object RomanNumerals {
  private def RomanDigits: Map[Int, String] = ListMap(
    1000 -> "M",
    900 -> "CM",
    500 -> "D",
    400 -> "CD",
    100 -> "C",
    90 -> "XC",
    50 -> "L",
    40 -> "XL",
    10 -> "X",
    9 -> "IX",
    5 -> "V",
    4 -> "IV",
    1 -> "I",
  )

  @tailrec
  def roman(input: Int, romanString: String = ""): String = {
    if (input==0) return romanString
    val nextDigit = RomanDigits.find(input >= _._1).get
    roman(input - nextDigit._1, romanString + nextDigit._2)
  }
}