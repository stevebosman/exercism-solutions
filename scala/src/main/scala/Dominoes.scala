object Dominoes {
  def loopify(input: List[(Int, Int)]): List[(Int, Int)] = {
    input.foldLeft(List[(Int, Int)]()) { (acc, domino) =>
      (acc.lastOption, domino) match {
        case (Some((_, s)), d) if s == d._1 => acc :+ d
        case (Some((_, s)), d) if s == d._2 => acc :+ d.swap
        case (None, d) => acc :+ d
        case _ => return List()
      }
    }
  }

  def chain(input: List[(Int, Int)]): Option[List[(Int, Int)]] = {
    if (input.isEmpty)
      Some(input)
    else
      input.permutations.map(p => loopify(p)).find(l => l.nonEmpty && l.head._1 == l.last._2)
  }
}