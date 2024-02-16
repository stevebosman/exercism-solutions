object SpiralMatrix {

  import SpiralMatrix.Direction.Direction

  private case class Directional(position: (Int, Int), direction: Direction)

  private object Direction extends Enumeration {
    type Direction = Value
    val Right, Down, Left, Up = Value
  }

  private def inArray(arr: Array[Array[Int]], pos: (Int, Int)): Boolean =
    if (pos._1 < 0 || pos._2 < 0 || pos._1 >= arr.length || pos._2 >= arr.length) false
    else arr(pos._1)(pos._2) == 0

  private def nextDirectional(arr: Array[Array[Int]], d: Directional): Directional = {
    val nextPos = nextPosition(d.position, d.direction)

    def switchDirection(p: (Int, Int), d: Direction) =
      Directional(nextPosition(p, d), d)

    (inArray(arr, nextPos), d.direction) match {
      case (true, _) => Directional(nextPos, d.direction)
      case (_, Direction.Right) => switchDirection(d.position, Direction.Down)
      case (_, Direction.Down) => switchDirection(d.position, Direction.Left)
      case (_, Direction.Left) => switchDirection(d.position, Direction.Up)
      case (_, Direction.Up) => switchDirection(d.position, Direction.Right)
    }
  }

  private def nextPosition(p: (Int, Int), d: Direction): (Int, Int) = {
    d match {
      case Direction.Right => (p._1, p._2 + 1)
      case Direction.Down => (p._1 + 1, p._2)
      case Direction.Left => (p._1, p._2 - 1)
      case Direction.Up => (p._1 - 1, p._2)
    }
  }

  def spiralMatrix(size: Int): List[List[Int]] =
    if (size == 0) List()
    else {
      val arr: Array[Array[Int]] = Array.ofDim[Int](size, size)
      var d = Directional((0, 0), Direction.Right)
      for (current <- 1 to size * size) {
        arr(d.position._1)(d.position._2) = current
        d = nextDirectional(arr, d)
      }
      arr.map(_.toList).toList
    }
}