class Triangle<out T : Number>(val a: T, val b: T, val c: T) {

    init {
        val sides = listOf(a, b, c).map(Number::toDouble).sorted()
        require(sides[0] > 0 && sides[0] + sides[1] > sides[2]) {
            "Not a triangle"
        }
    }

    private val uniqueSideLengthsCount = setOf(a, b, c).size
    val isEquilateral: Boolean = uniqueSideLengthsCount == 1
    val isIsosceles: Boolean = uniqueSideLengthsCount <= 2
    val isScalene: Boolean = uniqueSideLengthsCount == 3
}
