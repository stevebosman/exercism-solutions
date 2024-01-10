import java.util.List;
import java.util.stream.Collectors;

public class TestTrack {

    public static void race(final RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(final List<ProductionRemoteControlCar> cars) {
        return cars.stream().sorted().collect(Collectors.toList());
    }
}
