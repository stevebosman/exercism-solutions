class Squares(val n: Int) {
    fun sumOfSquares(): Int {
        return (n * (n + 1) * (2 * n + 1)) / 6
    }

    fun squareOfSum(): Int {
        return (n * (n + 1) / 2).let { it * it }
    }

    fun difference(): Int {
        return squareOfSum() - sumOfSquares()
    }
}
