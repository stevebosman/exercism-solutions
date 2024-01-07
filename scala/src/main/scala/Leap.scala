object Leap {
  def leapYear(year: Int): Boolean = 
    (year % 400, year % 100, year % 4) match {
      case (0, _, _) => true
      case (_, 0, _) => false
      case (_, _, 0) => true
      case (_, _, _) => false
    }
}
