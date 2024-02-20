import scala.collection.mutable

case class Puzzle(left: Seq[String], right: String, chars: Seq[Char]) {
  private val bannedZero: Seq[Char] = (left.map(s => s.head) :+ right.head).distinct

  private def eval(lookup: Map[Char, Char]): Boolean = {
    val memo: mutable.Map[String, Long] =
      mutable.Map().withDefault(_.map(lookup).toLong)

    left.map(memo).sum == memo(right)
  }

  private def tryPermutation(permutation: IndexedSeq[Char]): Option[Map[Char, Int]] = {
    val z = permutation.indexOf('0')
    if (z>=0 && bannedZero.contains(chars(z))) return None
    val lookup: Map[Char, Char] = chars.zip(permutation).map(e => e._1 -> e._2).toMap
    if (eval(lookup)) Some(lookup.map(e => e._1 -> (e._2 - '0')))
    else None
  }

  def solve: Option[Map[Char, Int]] = {
    for (combo <- ('0' to '9').combinations(chars.length)) {
      for (perms <- combo.permutations) {
        val value = tryPermutation(perms)
        if (value.nonEmpty) return value
      }
    }
    None
  }
}

object Alphametics {
  def solve(puzzle: String): Option[Map[Char, Int]] = {
    val chars: List[Char] = puzzle.filter(_.isLetter).distinct.sorted.toList
    val lr = puzzle.replaceAll(" ", "").split("==")
    Puzzle(lr(0).split("\\+"), lr(1), chars).solve
  }
}
