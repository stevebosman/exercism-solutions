import scala.collection.mutable

case class WordCount(words: String) {
  def countWords:Map[String, Int] = {
    val wordMap: mutable.Map[String, Int] = mutable.Map()
    words.trim.toLowerCase.split("'*[^a-z0-9']+'*").foreach(s => wordMap.addOne(s, wordMap.getOrElse(s, 0) + 1))
    wordMap.toMap
  }
}