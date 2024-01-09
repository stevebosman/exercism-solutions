import java.time.LocalDate
import java.time.LocalDateTime

class Gigasecond(start: LocalDateTime) {
    constructor(start: LocalDate) : this(start.atStartOfDay())

    val date: LocalDateTime = start.plusSeconds(1_000_000_000)
}
