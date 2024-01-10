class NeedForSpeed {
  private final int speed;
  private final int batteryDrain;
  private int distanceDriven;
  private int battery = 100;

  NeedForSpeed(final int speed, final int batteryDrain) {
    this.speed = speed;
    this.batteryDrain = batteryDrain;
  }

  public static NeedForSpeed nitro() {
    return new NeedForSpeed(50, 4);
  }

  public boolean batteryDrained() {
    return battery <= 0;
  }

  public int distanceDriven() {
    return distanceDriven;
  }

  public void drive() {
    if (!batteryDrained()) {
      distanceDriven += speed;
      battery -= batteryDrain;
    }
  }
}

class RaceTrack {
  private final int distance;

  RaceTrack(final int distance) {
    this.distance = distance;
  }

  public boolean tryFinishTrack(final NeedForSpeed car) {
    while (!car.batteryDrained() && car.distanceDriven() < distance) {
        car.drive();
    }
    return car.distanceDriven() >= distance;
  }
}
