import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {
    public static final int ONE_BILLION = 1_000_000_000;
    private final LocalDateTime gigasecond;

    public Gigasecond(final LocalDate moment) {
        this(moment.atTime(0,0));
    }

    public Gigasecond(final LocalDateTime moment) {
        gigasecond = moment.plusSeconds(ONE_BILLION);
    }

    public LocalDateTime getDateTime() {
        return gigasecond;
    }
}
