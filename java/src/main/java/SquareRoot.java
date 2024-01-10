public class SquareRoot {
    public int squareRoot(final int radicand) {
        if (radicand < 0) {
            throw new IllegalArgumentException("Radicand must be non-negative.");
        }
        if (radicand <= 1) {
            return radicand;
        }
        long below = 0;
        long above = radicand;
        long attempt;
        long diff;

        do {
            attempt = (above + below) / 2;
            diff = attempt * attempt - radicand;
            if (diff > 0) {
                above = attempt;
            } else if (diff < 0) {
                if (attempt == below) {
                    throw  new IllegalArgumentException("Radicand does not have an integer square root.");
                }
                below = attempt;
            }
        } while (diff != 0);

        return (int)attempt;
    }
}
