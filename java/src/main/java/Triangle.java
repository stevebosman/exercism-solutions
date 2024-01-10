import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

class Triangle {
    private final int uniqueSidesCount;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        // All sides should be positive
        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new TriangleException();
        }
        
        // Longest side should be smaller than sum of two shortest sides
        final List<Double> sides = Arrays.asList(Double.valueOf(side1), Double.valueOf(side2), Double.valueOf(side3));
        sides.sort(Double::compareTo);
        if (sides.get(0) + sides.get(1) <= sides.get(2)) {
            throw new TriangleException();
        }
        
        // Count the sides - one of those rare moments where I don't care about generics
        this.uniqueSidesCount = new HashSet(sides).size();
    }

    boolean isEquilateral() {
        return uniqueSidesCount == 1;
    }

    boolean isIsosceles() {
        return uniqueSidesCount <= 2 ;
    }

    boolean isScalene() throws TriangleException {
        return uniqueSidesCount == 3;
    }

}
