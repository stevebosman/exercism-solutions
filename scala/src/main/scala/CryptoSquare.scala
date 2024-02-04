import scala.math.ceil

object CryptoSquare {
  private def rows(length: Int, c: Int): Int = {
    ceil(length.toFloat / c).toInt
  }

  private def isGood(shape: (Int, Int)): Boolean = {
    val c = shape._1
    val r = shape._2
    c >= r && c - r <= 1
  }

  def ciphertext(text: String): String = {
    val remediated = text.toLowerCase.replaceAll("[^\\da-z]+", "")
    val length = remediated.length
    if (length <= 1)
      remediated
    else {
      val (c, r) = (2 to length)
        .map(c => (c, rows(length, c)))
        .find(shape => isGood(shape)).get
      (remediated + " " * (r * c - length)).grouped(c).toList.transpose.map(l => l.mkString("")).mkString(" ")
    }
  }
}