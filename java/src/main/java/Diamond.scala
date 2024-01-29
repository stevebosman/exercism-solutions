object Diamond {
  private def makeLine(current: Int, halfLength: Int): String = {
    val end = (0 until halfLength).map(i => if (i == current) ('A' + current).toChar else ' ').mkString
    end.tail.reverse + end
  }

  def rows(c: Char): List[String] = {
    val start = c - 'A'
    val triangle = (0 to start).map(makeLine(_, start + 1)).toList
    triangle ++ triangle.reverse.tail
  }
}