class DNA(val s:String) {
  def nucleotideCounts: Either[String, Map[Char, Int]] = {
    if (!s.matches("[ACGT]*")) Left("invalid")
    else Right("ACGT".map(n => n -> s.count(c => c == n)).toList.toMap)
  }
}