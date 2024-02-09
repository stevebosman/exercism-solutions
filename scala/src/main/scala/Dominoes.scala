object Dominoes {
  def chain(input: List[(Int, Int)], current: List[(Int, Int)] = List()): Option[List[(Int, Int)]] = {
    def removeFirst(input: List[(Int, Int)], v: (Int, Int)): List[(Int, Int)] = {
      val i = input.indexOf(v)
      input.zipWithIndex.filter(_._2!=i).map(_._1)
    }

    if (input.isEmpty)
      if (current.isEmpty || current.head._1 == current.last._2) Some(current)
      else None
    else if (current.isEmpty)
      chain(input.tail, current :+ input.head)
    else {
      for (a <- input.filter(i => i._1 == current.last._2)) {
        val option = chain(removeFirst(input, a), current :+ a)
        if (option.isDefined) return option
      }
      //reversed
      for (a <- input.filter(i => i._2 == current.last._2)) {
        val option = chain(removeFirst(input, a), current :+ (a._2, a._1))
        if (option.isDefined) return option
      }
      None
    }
  }
}