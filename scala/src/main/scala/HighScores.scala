object HighScores {
  private def latestMessage(value: Int) = s"Your latest score was $value. "

  private def personalBestMessage(pb: Int, latest: Int) = (pb - latest) match {
    case diff if diff > 0 =>
      s"That's ${diff} short of your personal best!"
    case _ =>
      s"That's your personal best!"
  }

  def report(values: List[Int]):String = {
    val latestValue = latest(values)
    latestMessage(latestValue) + personalBestMessage(personalBest(values), latestValue)
  }

  def personalTop(values: List[Int]):List[Int] = values.sortWith((a, b) => a > b).take(3)

  def personalBest(values: List[Int]):Int = values.sortWith((a,b) => a>b).head

  def latest(values: List[Int]):Int = values.last

}
