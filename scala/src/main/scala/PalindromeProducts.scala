import scala.util.Try

case class PalindromeProducts(start: Int, end: Int) {

  def smallest: Option[(Int, Set[(Int, Int)])] = Try {
      val head = findPalindromes.head
      (head._1, head._2.map(e => (e._2, e._3)).toSet)
    }.toOption

  def largest: Option[(Int, Set[(Int, Int)])] = Try {
    val last = findPalindromes.last
    (last._1, last._2.map(e => (e._2, e._3)).toSet)
  }.toOption


  private def isPalindrome(value: Int): Boolean = {
    val s = value.toString
    s == s.reverse
  }

  private def findPalindromesN(i: Int): Seq[(Int, Int, Int)] =
    (i to end).filter(j => isPalindrome(i * j)).map(j => (i * j, i, j))

  private def findPalindromes = {
    (start to end).flatMap(i => findPalindromesN(i)).groupBy(_._1).toIndexedSeq.sortBy(_._1)
  }
}
