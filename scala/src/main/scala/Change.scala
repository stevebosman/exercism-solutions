object Change {
  private def bestCoins(total: Int, coins: List[Int]): List[Int] = {
    if (total == 0)
      List()
    else if (coins.contains(total))
      List(total)
    else {
      var best: List[Int] = Nil
      for (coin <- coins) {
        if (best == Nil || best.size > total / coin) {
          if (total % coin == 0) {
            best = List.fill(total / coin)(coin)
          } else if (coin <= total) {
            val potential = bestCoins(total - coin, coins) :+ coin
            if (potential.sum == total && (best == Nil || potential.size < best.size))
              best = potential
          }
        }
      }
      best
    }
  }

  def findFewestCoins(total: Int, coins: List[Int]): Option[List[Int]] = {
    val best = bestCoins(total, coins.sortWith((a, b) => a > b))
    if (best.sum != total) None
    else Some(best)
  }
}