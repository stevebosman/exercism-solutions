import scala.annotation.tailrec

object Sieve {
  /**
   * sieve is 2 based, not 0 based
   */
  private val OFFSET = 2

  @tailrec
  def sieve(n: Int, i: Int, values: Array[Boolean]): List[Int] = {
    (2 * i to n by i).foreach(n => values(n - OFFSET) = true)
    values.indexOf(false, i - 1) match {
      case -1 => values.zipWithIndex.filter(!_._1).map(_._2 + OFFSET).toList
      case nextI => sieve(n, nextI + OFFSET, values)
    }
  }

  def primes(n: Int): List[Int] = sieve(n, 2, Array.fill(n - 1) {
    false
  })
}