object PigLatin {
  private val Vowels = "aeiou"

  private def translateWord(text: String): String = {
    if (Vowels.contains(text.head) || text.startsWith("xr") || text.startsWith("yt")) text
    else if (text.startsWith("qu")) text.substring(2) + "qu"
    else if (text.head == 'q') text.tail + "q"
    else if (text.tail == "y") text.tail + text.head
    else {
      val cluster = text.head + text.tail.takeWhile(c => !Vowels.contains(c) && c != 'y')
      val postCluster = text.substring(cluster.length)
      if (cluster.endsWith("q") && postCluster.head == 'u')
        postCluster.tail + cluster + 'u'
      else
        postCluster + cluster
    }
  } + "ay"

  def translate(text: String): String =
    text.split(" ").map(translateWord).mkString(" ")
}