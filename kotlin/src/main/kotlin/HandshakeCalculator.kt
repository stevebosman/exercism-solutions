object HandshakeCalculator {
    private infix fun Int.isBitSet(bit: Int) = this and bit == bit

    fun calculateHandshake(number: Int): List<Signal> {
        return mutableListOf<Signal>().apply {
            if (number.isBitSet(1)) {
                add(Signal.WINK);
            }
            if (number.isBitSet(2)) {
                add(Signal.DOUBLE_BLINK);
            }
            if (number.isBitSet(4)) {
                add(Signal.CLOSE_YOUR_EYES);
            }
            if (number.isBitSet(8)) {
                add(Signal.JUMP);
            }
            if (number.isBitSet(16)) {
                reverse();
            }
        }
    }

}
