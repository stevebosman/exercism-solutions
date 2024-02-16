object SpiralMatrix {

  import SpiralMatrix.Direction.Direction

  private object Direction extends Enumeration {
    type Direction = Value
    val Right, Down, Left, Up = Value
  }

  private def inArray(arr: Array[Array[Int]], pos: (Int, Int)): Boolean =
    if (pos._1<0 || pos._2 <0 || pos._1>=arr.length || pos._2>=arr.length) false
    else arr(pos._1)(pos._2) == 0

  private def nextDirection(arr: Array[Array[Int]], nextPos: (Int, Int), direction: Direction): Direction = {
    (inArray(arr, nextPos), direction) match {
      case (true, _) => direction
      case (_, Direction.Right) => Direction.Down
      case (_, Direction.Down) => Direction.Left
      case (_, Direction.Left) => Direction.Up
      case (_, Direction.Up) => Direction.Right
    }
  }

  private def nextPosition(pos: (Int, Int), direction: Direction): (Int, Int) =
    direction match {
      case Direction.Right => (pos._1, pos._2 + 1)
      case Direction.Down => (pos._1 + 1, pos._2)
      case Direction.Left => (pos._1, pos._2 - 1)
      case Direction.Up => (pos._1 - 1, pos._2)
    }

  def spiralMatrix(size: Int): List[List[Int]] =
    if (size == 0) List()
    else {
      val arr: Array[Array[Int]] = Array.ofDim[Int](size, size)
      var pos = (0, 0)
      var d = Direction.Right
      for (current <- 1 to size * size) {
        arr(pos._1)(pos._2) = current
        val nextPos = nextPosition(pos, d)
        d = nextDirection(arr, nextPos, d)
        pos = nextPosition(pos, d)
      }
      arr.map(_.toList).toList
    }
}