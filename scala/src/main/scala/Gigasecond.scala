import java.time.{Duration, LocalDate, LocalDateTime}
import java.time.temporal.{ChronoUnit, TemporalAmount, TemporalUnit}

object Gigasecond {
  def add(startDate: LocalDate): LocalDateTime = add(startDate.atStartOfDay)

  def add(startDateTime: LocalDateTime): LocalDateTime = startDateTime.plus(1_000_000_000L, ChronoUnit.SECONDS)
}
