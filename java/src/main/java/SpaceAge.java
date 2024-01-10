class SpaceAge {
    private static final double MERCURY_PERIOD = 0.2408467;
    private static final double VENUS_PERIOD = 0.61519726;
    private static final double MARS_PERIOD = 1.8808158;
    private static final double JUPITER_PERIOD = 11.862615;
    private static final double SATURN_PERIOD = 29.447498;
    private static final double URANUS_PERIOD = 84.016846;
    private static final double NEPTUNE_PERIOD = 164.79132;
    
    final double seconds;

    SpaceAge(final double seconds) {
        this.seconds = seconds;
    }

    double getSeconds() {
        return this.seconds;
    }

    double onEarth() {
        return this.seconds / 31_557_600;
    }

    double onMercury() {
        return onEarth() / MERCURY_PERIOD ;
    }

    double onVenus() {
        return onEarth() / VENUS_PERIOD ;
    }

    double onMars() {
        return onEarth() / MARS_PERIOD ;
    }

    double onJupiter() {
        return onEarth() / JUPITER_PERIOD ;
    }

    double onSaturn() {
        return onEarth() / SATURN_PERIOD ;
    }

    double onUranus() {
        return onEarth() / URANUS_PERIOD ;
    }

    double onNeptune() {
        return onEarth() / NEPTUNE_PERIOD ;
    }

}
