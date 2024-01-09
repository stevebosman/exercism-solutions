class Robot {
    companion object {
        private val NAMES = mutableSetOf<String>()
    }
    private var mutableName = generateName()

    private fun generateName(): String {
        var newName: String
        do {
            newName = "${randomLetter()}${randomLetter()}${randomDigit()}${randomDigit()}${randomDigit()}"
        } while (NAMES.contains(newName))
        NAMES.add(newName)
        return newName
    }

    private fun randomLetter() = ('A'..'Z').random()
    private fun randomDigit() = (0..9).random()

    val name: String
        get() = mutableName

    fun reset() {
        val originalName = mutableName
        mutableName = generateName()
        NAMES.remove(originalName)
    }
}
