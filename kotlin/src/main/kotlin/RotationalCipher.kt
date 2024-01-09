class RotationalCipher(val offset: Int) {
    fun encode(text: String): String {
        return String(text.map {
            if (it.isLetter()) {
                var raw = it + offset
                if (it.isUpperCase() && raw > 'Z') raw -= 26
                if (it.isLowerCase() && raw > 'z') raw -= 26
                raw
            } else {
                it
            }
        }.toCharArray())
    }
}
