import scala.util.matching.Regex

object AtbashCipher {
  private val encoderPattern: Regex = """.{1,5}""".r

  def encode(str: String): String = {
    encoderPattern.findAllIn(decode(str)).mkString(" ")
  }

  def decode(str: String): String =
    str.toLowerCase.filter(c => c.isDigit || c.isLetter).map(encodeChar)

  private def encodeChar(c: Char): Char = c match {
    case c if c.isDigit => c
    case c => ('a' + 'z' - c).asInstanceOf[Char]
  }
}
