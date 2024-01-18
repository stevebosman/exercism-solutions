import scala.math.abs

case class Queen(x: Int, y: Int)

object Queen {
  private val validColumns: Range.Inclusive = 0 to 7
  private val validRows: Range.Inclusive = 0 to 7

  def create(x: Int, y: Int): Option[Queen] = {
    if ((validColumns contains x) && (validRows contains y)) Some(new Queen(x, y))
    else None
  }
}

object QueenAttack {
  def canAttack(q1: Queen, q2: Queen): Boolean = q1.x == q2.x || q1.y == q2.y || abs(q1.x - q2.x) == abs(q1.y - q2.y)
}

