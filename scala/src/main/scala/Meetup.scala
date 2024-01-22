import Schedule.Schedule

import java.time.{DayOfWeek, LocalDate, YearMonth}

case class Meetup(month: Int, year: Int) {
  def day(dayOfWeek: Int, schedule: Schedule): LocalDate = {
    if (schedule == Schedule.Last) {
      val lastDayOfMonth = YearMonth.of(year, month).atEndOfMonth()
      lastDayOfMonth.minusDays((lastDayOfMonth.getDayOfWeek.getValue - dayOfWeek + 7) % 7)
    } else {
      val firstDayOfMonth = LocalDate.of(year, month, 1)
      val firstDayOfWeek = firstDayOfMonth.plusDays((dayOfWeek - firstDayOfMonth.getDayOfWeek.getValue + 7) % 7)
      if (schedule == Schedule.Teenth) {
        if (firstDayOfWeek.getDayOfMonth < 6) {
          firstDayOfWeek.plusDays(14)
        } else {
          firstDayOfWeek.plusDays(7)
        }
      } else {
        firstDayOfWeek.plusDays((schedule.id - 1) * 7)
      }
    }
  }
}

object Schedule extends Enumeration {
  type Schedule = Value
  val Teenth, First, Second, Third, Fourth, Last = Value
}

object Meetup {
  val Mon = DayOfWeek.MONDAY.getValue
  val Tue = DayOfWeek.TUESDAY.getValue
  val Wed = DayOfWeek.WEDNESDAY.getValue
  val Thu = DayOfWeek.THURSDAY.getValue
  val Fri = DayOfWeek.FRIDAY.getValue
  val Sat = DayOfWeek.SATURDAY.getValue
  val Sun = DayOfWeek.SUNDAY.getValue
}
