public class Lasagna {
    private final int expectedMiinutesInOven = 40;
    public int expectedMinutesInOven() {
        return expectedMiinutesInOven;
    }

    public int remainingMinutesInOven(final int elapsedMinutes) {
        return expectedMiinutesInOven - elapsedMinutes;
    }

    public int preparationTimeInMinutes(final int layers) {
        return layers * 2;
    }

    public int totalTimeInMinutes(final int layers, final int elapsedMinutes) {
        return preparationTimeInMinutes(layers) + elapsedMinutes;   
    }
}
