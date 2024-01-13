object Pangrams {
  def isPangram(input: String): Boolean = input.toLowerCase.filter(c => c >= 'a' && c <= 'z').toSet.size == 26
}

