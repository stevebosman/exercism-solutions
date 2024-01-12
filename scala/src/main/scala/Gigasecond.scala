import java.time.{LocalDate, LocalDateTime}
import java.time.temporal.ChronoUnit

object Gigasecond {
  def add(startDate: LocalDate): LocalDateTime = add(startDate.atStartOfDay)

  def add(startDateTime: LocalDateTime): LocalDateTime = startDateTime.plus(1_000_000_000L, ChronoUnit.SECONDS)
}
