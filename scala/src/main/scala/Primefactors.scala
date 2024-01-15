import scala.annotation.tailrec

object PrimeFactors {
  @tailrec
  def factors(n: Long, d: Int = 2, list: List[Int] = List()): List[Int] = n match {
    case 1 => list
    case n if (n % d == 0) => factors(n/d, d, list :+ d)
    case _ => factors(n, d+1, list)
  }
}