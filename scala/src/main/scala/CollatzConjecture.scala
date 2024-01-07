import scala.annotation.tailrec

object CollatzConjecture {
  @tailrec
  private def countSteps(steps: Int, num: Int): Option[Int] = {
    (num < 1, num, num % 2 == 0) match {
      case (true, _, _) => None
      case (_, 1, _)    => Some(steps)
      case (_, _, true) => countSteps(steps + 1, num / 2)
      case (_, _, _)    => countSteps(steps + 1, 3 * num + 1)
    }
  }

  def steps(start: Int): Option[Int] = {
    countSteps(0, start)
  }
}