object Minesweeper {
  def annotate(list: List[String]): List[String] = {
    val array = list.map(_.toArray).toArray

    def minesAt(row: Int, column: Int): Int = (row, column) match {
      case (r, _) if r < 0 || r >= array.length => 0
      case (_, c) if c < 0 || c >= array(0).length => 0
      case (r, c) if array(r)(c) == '*' => 1
      case _ => 0
    }

    def neighbourCount(row: Int, column: Int): Int = {
      minesAt(row - 1, column - 1) + minesAt(row - 1, column) + minesAt(row - 1, column + 1) + minesAt(row, column - 1) + minesAt(row, column + 1) + minesAt(row + 1, column - 1) + minesAt(row + 1, column) + minesAt(row + 1, column + 1)
    }

    def neighbourCountChar(row: Int, column: Int): Char = neighbourCount(row, column) match {
      case 0 => ' '
      case c => (c + '0').toChar
    }

    def transform(cell: Char, row: Int, column: Int): Char = cell match {
      case ' ' => neighbourCountChar(row, column)
      case _ => cell
    }

    array.zipWithIndex
      .map(row => row._1.zipWithIndex.map(cell => transform(cell._1, row._2, cell._2)).mkString(""))
      .toList
  }
}