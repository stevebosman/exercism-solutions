case class PalindromeProducts(start: Int, end: Int) {

  def smallest: Option[(Int, Set[(Int, Int)])] = {
    if (start > end) return None

    val palindromes = findPalindromes
    if (palindromes.isEmpty) return None

    val seeking = palindromes.map(e => e._1 * e._2).min

    Some((seeking, palindromes.filter(e => e._1 * e._2 == seeking).toSet))
  }

  def largest: Option[(Int, Set[(Int, Int)])] = {
    if (start > end) return None

    val palindromes = findPalindromes
    if (palindromes.isEmpty) return None

    val seeking = palindromes.map(e => e._1 * e._2).max

    Some((seeking, palindromes.filter(e => e._1 * e._2 == seeking).toSet))
  }

  private def isPalindrome(value: Int): Boolean =
    value == value.toString.reverse.toInt

  private def findPalindromesN(i: Int): Seq[(Int, Int)] =
    (i to end).filter(j => isPalindrome(i * j)).map(j => (i, j))

  private def findPalindromes: Seq[(Int, Int)] = {
    (start to end).flatMap(i => findPalindromesN(i))
  }
}
