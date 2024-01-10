import java.util.stream.IntStream;

class IsbnVerifier {

    boolean isValid(final String stringToVerify) {
        final String isbn = stringToVerify.replaceAll("-", "");
        return isbn.matches("^[0-9]{9}[0-9X]$")
                && (IntStream.range(0, 10)
                                 .map(i -> (10 - i) * value(isbn.charAt(i)))
                                 .sum()) % 11 == 0;
    }

    private static int value(final char c) {
        return c == 'X' ? 10 : c - '0';
    }

}
