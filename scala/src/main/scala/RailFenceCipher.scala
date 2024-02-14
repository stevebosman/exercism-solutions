object RailFenceCipher {
  def column(c: Char, col: Int, lines: Int): List[Char] = {
    val x = 2 * lines - 2
    val i = col % x match {
      case l if l < lines => l
      case l => x - l
    }
    List.tabulate(lines)(j => if (i == j) c else ' ')
  }

  private def emptyGrid(width: Int, lines: Int): Seq[Seq[Char]] =
    (0 until width).map(e => column('.', e, lines))

  private def replaceChar(c: Char, empty: Seq[Char]): Seq[Char] =
    empty.map {
      case '.' => c
      case c => c
    }

  private def stringify(grid: Seq[Seq[Char]]): String =
    grid.transpose.map(_.mkString("")).mkString("").replaceAll(" ", "")

  def encode(s: String, lines: Int): String = {
    val empty = emptyGrid(s.length, lines)
    stringify(s.zipWithIndex.map(e => replaceChar(e._1, empty(e._2))))
  }

  def decode(s: String, lines: Int): String = {
    stringify(emptyGrid(s.length, lines).transpose.map(_.mkString("")).mkString("\n")
      .split("\\.").zip(s).map(e => e._1 + e._2).mkString("").split("\\n")
      .map(s2 => s2.padTo(s.length, ' ').toList))
  }
}