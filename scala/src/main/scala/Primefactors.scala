import scala.collection.mutable.ListBuffer

object PrimeFactors {
  def factors(n: Long): List[Int] = {
    if (n <= 1) return List()
    val result: ListBuffer[Int] = ListBuffer()
    var r = n
    var d = 2
    while (r > 1) {
      if (r % d == 0) {
        result.addOne(d)
        r = r / d
      } else {
        d += 1
      }
    }
    result.toList
  }
}