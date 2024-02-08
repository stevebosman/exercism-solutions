case class Matrix(matrix: List[List[Int]]) {
  private def rowMaxima(row: List[Int], i: Int): List[(Int, Int)] =
    row.zipWithIndex.filter(_._1 == row.max).map(x => (i, x._2))

  private def columnMinima(column: List[Int], i: Int): List[(Int, Int)] =
    column.zipWithIndex.filter(_._1 == column.min).map(x => (x._2, i))

  def saddlePoints: Set[(Int, Int)] =
    matrix.zipWithIndex.flatMap(l => rowMaxima(l._1, l._2))
      .intersect(matrix.transpose.zipWithIndex.flatMap(l => columnMinima(l._1, l._2)))
      .toSet
}