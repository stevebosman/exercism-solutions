import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object PrimeFactors {
  @tailrec
  def factors(n: Long, d: Int = 2, list: ListBuffer[Int] = ListBuffer()): List[Int] = n match {
    case 1 => list.toList
    case n if (n % d == 0) =>
      list.addOne(d)
      factors(n/d, d, list)
    case _ =>
      factors(n, d+1, list)
  }
}