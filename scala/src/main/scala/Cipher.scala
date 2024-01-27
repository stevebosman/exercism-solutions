class Cipher(val key: String) {
  private def alphaValue(c: Char): Int = c - 'a'

  private def keyValue(i: Int): Int = alphaValue(key(i % key.length))

  def encode(str: String): String =
    str.zipWithIndex
      .map(c => ((alphaValue(c._1) + (keyValue(c._2))) % 26 + 'a').asInstanceOf[Char])
      .mkString("")

  def decode(str: String): String =
    str.zipWithIndex
      .map(c => ((26 + alphaValue(c._1) - keyValue(c._2)) % 26 + 'a').asInstanceOf[Char])
      .mkString("")
}

object Cipher {
  private val RANDOM = new scala.util.Random

  private def randomKey: String =
    (1 to 100 map { _ => ('a' + RANDOM.nextInt(26)).asInstanceOf[Char] }).mkString("")

  def apply(key: Option[String]): Cipher = key match {
    case None => new Cipher(randomKey)
    case Some(x) if x.matches("[a-z]+") => new Cipher(x)
    case _ => throw new IllegalArgumentException()
  }
}
