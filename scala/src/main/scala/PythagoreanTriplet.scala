object PythagoreanTriplet {
  type Triple = (Int, Int, Int)

  private def square(v: Int): Int = v * v

  def isPythagorean(triple: Triple): Boolean =
    square(triple._1) + square(triple._2) == square(triple._3)

  def pythagoreanTriplets(low: Int, high: Int): Seq[Triple] =
    for {
      x <- low to high - 2
      z <- x + 2 to high
      y = Math.sqrt(square(z) - square(x)).toInt
      tuple = (x, y, z)
      if x < y && y < z && isPythagorean(tuple)
    } yield tuple

  def pythagoreanTripletsSum(sum: Int): Seq[Triple] =
    for {
      x <- 1 to sum / 3
      z <- x + 2 until sum - x
      y = sum - z - x
      tuple = (x, y, z)
      if x < y && y < z && isPythagorean(tuple)
    } yield tuple
}