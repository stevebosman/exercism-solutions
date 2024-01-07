object SumOfMultiples {
  def multiples(factor: Int, limit: Int): Set[Int] = {
    (factor until limit by factor).toSet
  }

  def sum(factors: Set[Int], limit: Int): Int = {
    factors.flatMap(multiples(_, limit)).sum
  }
}

