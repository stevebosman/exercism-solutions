import scala.collection.mutable.ListBuffer

object PascalsTriangle {
  private def row(n: Int, previous: List[Int]): List[Int] =
    (previous :+ 0).zip(0 :: previous).map(e => e._1 + e._2)

  def rows(n: Int): List[List[Int]] = {
    if (n <= 0) return List()
    val buff = ListBuffer[List[Int]]()
    (1 to n).foreach(i => buff += (if (i == 1) List(1) else row(i, buff.last)))
    buff.toList
  }
}