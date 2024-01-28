object Anagram {
  private def isAnagram(str: String, potential: String):Boolean =
    str != potential && str.sorted == potential.sorted

  def findAnagrams(str: String, potentials: List[String]): List[String] = {
    val lower = str.toLowerCase()
    potentials.filter(p => isAnagram(lower, p.toLowerCase()))
  }
}