class Clock(val hour: Int, val minute: Int) {
  def +(that: Clock): Clock = Clock(
    this.hour + that.hour,
    this.minute + that.minute)

  def -(that: Clock): Clock = Clock(
    this.hour - that.hour,
    this.minute - that.minute)

  private def canEqual(other: Any): Boolean = other.isInstanceOf[Clock]

  override def equals(other: Any): Boolean = other match {
    case that: Clock =>
      that.canEqual(this) &&
        hour == that.hour &&
        minute == that.minute
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(hour, minute)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"$hour:$minute"
}

object Clock {
  def apply(hour: Int, minute: Int): Clock = new Clock(
    (hour + Math.floorDiv(minute, 60)) - Math.floorDiv(hour + Math.floorDiv(minute, 60), 24) * 24,
    Math.floorMod(minute, 60)
  )

  def apply(minute: Int): Clock = Clock(0, minute)
}
