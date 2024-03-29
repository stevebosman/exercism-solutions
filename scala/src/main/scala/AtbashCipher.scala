object AtbashCipher {
  def decode(str: String): String =
    str.filter(c => c.isLetterOrDigit).map(encodeChar)

  def encode(str: String): String =
    decode(str.toLowerCase).grouped(5).mkString(" ")

  private def encodeChar(c: Char): Char =
    if (c.isDigit) c else ('a' + 'z' - c).toChar
}
