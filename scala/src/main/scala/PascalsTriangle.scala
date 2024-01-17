import Function.tupled
object PascalsTriangle {
  def rows(n: Int): List[List[Int]] =
    List.iterate(List(1), n) {
      row => 0 +: row zip row :+ 0 map tupled { _ + _ }
    }
}