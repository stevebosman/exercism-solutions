import scala.annotation.tailrec

object BookStore {
  private val PriceWithDiscount = Map(1 -> 800, 2 -> 1520, 3 -> 2160, 4 -> 2560, 5 -> 3000)

  private def bookCounts(prices: Seq[Int]): Map[Int, Int] =
    prices.groupBy(identity).map(e => e._1 -> e._2.size).filter(e => e._2 > 0)

  private def swap3sAnd5sFor4s(i: Int, discountGroups: Map[Int, Int]): Map[Int, Int] =
    discountGroups + (3 -> {
      discountGroups.getOrElse(3, i) - i
    }) + (5 -> {
      discountGroups.getOrElse(5, i) - i
    }) + (4 -> {
      discountGroups.getOrElse(4, 0) + 2 * i
    })

  @tailrec
  private def calculateDiscountGroups(counts: Map[Int, Int], discountGroups: Map[Int, Int] = Map()): Map[Int, Int] = {
    if (counts.isEmpty) {
      (discountGroups.get(3), discountGroups.get(5)) match {
        case (threes, fives) if threes.isDefined && fives.isDefined =>
          swap3sAnd5sFor4s(math.min(threes.get, fives.get), discountGroups)
        case _ => discountGroups
      }
    } else {
      val min = counts.values.min
      calculateDiscountGroups(counts.map(e => e._1 -> {
        e._2 - min
      }).filter(e => e._2 > 0),
        discountGroups + (counts.size -> min)
      )
    }
  }

  def total(prices: Seq[Int]): Int =
    calculateDiscountGroups(bookCounts(prices)).map(e => e._2 * PriceWithDiscount.getOrElse(e._1, 0)).sum

}