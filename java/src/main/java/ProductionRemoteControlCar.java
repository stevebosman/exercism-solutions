class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
    private static final int SPEED = 10;

    private int distanceTravelled;

    private int numberOfVictories;

    public void drive() {
        distanceTravelled += SPEED;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public void setNumberOfVictories(final int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    @Override
    public int compareTo(final ProductionRemoteControlCar o) {
        return Integer.compare(o.numberOfVictories, numberOfVictories);
    }
}
