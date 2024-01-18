import scala.annotation.tailrec

object NthPrime {
  @tailrec
  private def seek(n: Int, i: Int = 2, knownPrimes: List[Int] = List()): Int = {
    if (knownPrimes.length == n) return knownPrimes.last
    knownPrimes match {
      case k if k.exists(i % _ == 0) => seek(n, i + 1, knownPrimes)
      case _ => seek(n, i + 1, knownPrimes :+ i)
    }
  }

  def prime(n: Int): Option[Int] = {
    if (n <= 0) return None
    Some(seek(n))
  }
}