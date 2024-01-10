public class Hamming {
    final String leftStrand, rightStrand;
    public Hamming(final String leftStrand, final String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            if (leftStrand.length() == 0) throw new IllegalArgumentException("left strand must not be empty.");
            if (rightStrand.length() == 0) throw new IllegalArgumentException("right strand must not be empty.");
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }
        this.leftStrand = leftStrand;
        this.rightStrand = rightStrand;
    }

    public int getHammingDistance() {
        int diff = 0;
        for (int i = 0; i < leftStrand.length(); i++) {
            if (leftStrand.charAt(i) != rightStrand.charAt(i)) diff++;
        }
        return diff;
    }
}
