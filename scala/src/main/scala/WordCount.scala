case class WordCount(words: String) {
  def countWords: Map[String, Int] = {
    words.trim
      .toLowerCase
      .split("'*[^a-z0-9']+'*")
      .filter(s => !s.isBlank)
      .foldLeft(Map[String, Int]()) {
        (map, word) => map + (word -> (map.getOrElse(word, 0) + 1))
      }
  }
}