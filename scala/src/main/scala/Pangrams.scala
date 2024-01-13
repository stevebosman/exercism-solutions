object Pangrams {
  def isPangram(input: String): Boolean = input.toLowerCase.filter(('a' to 'z') contains _).toSet.size == 26
}
