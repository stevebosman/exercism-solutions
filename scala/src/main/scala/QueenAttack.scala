import scala.math.abs

class Queen(val x: Int, val y: Int) {
  private def canEqual(other: Any): Boolean = other.isInstanceOf[Queen]

  override def equals(other: Any): Boolean = other match {
    case that: Queen =>
      that.canEqual(this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Queen {
  private val validColumns: Range.Inclusive = 0 to 7
  private val validRows: Range.Inclusive = 0 to 7

  def create(x: Int, y: Int): Option[Queen] = {
    if ((validColumns contains x) && (validRows contains y)) Some(new Queen(x, y))
    else None
  }

  def apply(x: Int, y: Int): Queen = new Queen(x, y)
}

object QueenAttack {
  def canAttack(q1: Queen, q2: Queen): Boolean = q1.x == q2.x || q1.y == q2.y || abs(q1.x - q2.x) == abs(q1.y - q2.y)
}

