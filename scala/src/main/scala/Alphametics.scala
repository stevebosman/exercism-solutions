object Alphametics {

  def replaceChar(c: Char, lookup: Map[Char, Int]): Char = lookup.get(c) match {
    case Some(d) => ('0' + d).toChar
    case _ => c
  }

  def eval(value: String): Boolean = {
    if (value.matches("^(0|.* 0)\\d.*$")) return false
    val lr = value.split("==")
    lr(0).split("\\+").map(_.trim.toLong).sum == lr(1).trim.toLong
  }

  def solve(puzzle: String): Option[Map[Char, Int]] = {
    val chars: List[Char] = puzzle.filter(_.isLetter).distinct.sorted.toList
    for (combo <- (0 to 9).combinations(chars.length)) {
      for (digits <- combo.permutations) {
        val lookup: Map[Char, Int] = chars.zip(digits).map(e => e._1 -> e._2).toMap
        if (eval(puzzle.map(replaceChar(_, lookup)))) return Some(lookup)
      }
    }
    None
  }

}