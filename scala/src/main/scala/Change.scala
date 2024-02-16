object Change {
  def findFewestCoins(total: Int, coins: List[Int]): Option[List[Int]] = {
    if (total == 0)
      Some(List())
    else {
      val min = coins.min
      val expanded = coins.flatMap(i => List.fill(total/i)(i))
      for (elem <- 1 to total / min)
        for (list <- expanded.combinations(elem))
          if (list.sum == total) return Some(list)
      None
    }
  }
}