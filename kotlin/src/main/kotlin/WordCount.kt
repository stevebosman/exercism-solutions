import java.util.*

object WordCount {
    fun phrase(phrase: String): Map<String, Int> {
        return "\\w+(?:'\\w+)?".toRegex()
            .findAll(phrase.lowercase(Locale.getDefault()))
            .groupingBy { it.value }
            .eachCount()
    }
}
