object RotationalCipher {
  private def rotateLetter(c: Char, offset: Int) = {
    val baseLetter = if (c.isLower) 'a' else 'A'
    ((c - baseLetter + offset) % 26 + baseLetter).asInstanceOf[Char].toString
  }

  def rotate(str: String, offset: Int): String =
    str.map(c => if (c.isLetter) rotateLetter(c, offset % 26) else c.toString).mkString
}