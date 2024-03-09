import scala.collection.mutable.ListBuffer
import scala.math.{floor, sqrt}

object PythagoreanTriplet {
  private type Triple = (Int, Int, Int)

  private val Scale = 2 + sqrt(2)

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

  def pythagoreanTripletsSum(sum: Int): Seq[Triple] = {
    val max:Int = floor(sum / Scale).toInt
    val list: ListBuffer[Triple] = ListBuffer()
    for (x:Int <- 3 to max) {
      val sum_minus_x:Int = sum - x
      val numerator:Int = x * x + sum_minus_x * sum_minus_x
      val denominator:Int = 2 * sum_minus_x
      if (numerator % denominator == 0) {
        val z = numerator / denominator
        val y = sum - x - z
        list.addOne((x,y,z))
      }
    }
    list.toList
  }
}