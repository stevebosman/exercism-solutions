public class CarsAssemble {

    private int successRate(final int speed) {
        final int rate;
        if (speed <= 4) {
            rate = 100;
        } else if (speed < 9) {
            rate = 90;
        } else if (speed == 9) {
            rate = 80;
        } else {
            rate = 77;
        }
        return rate;
    }

    public double productionRatePerHour(int speed) {
        return speed * 221 * successRate(speed) / 100.0;
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (productionRatePerHour(speed) / 60);
    }
}
