object ScrabbleScore {

    fun scoreLetter(c: Char): Int {
        when (c.uppercaseChar()) {
            'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> return 1
            'D', 'G' -> return 2
            'B', 'C', 'M', 'P' -> return 3
            'F', 'H', 'V', 'W', 'Y' -> return 4
            'K' -> return 5
            'J', 'X' -> return 8
            'Q', 'Z' -> return 10
        }
        throw IllegalArgumentException("Unrecognized character '${c}'")
    }

    fun scoreWord(word: String): Int {
        return word.sumOf{ scoreLetter(it) }
    }
}
