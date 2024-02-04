object FrameState extends Enumeration {
  val Open, Spare, Strike = Value
}

case class Frame(round : Int, pins: List[Int]) {
  private val FinalRound = 10

  def state(): FrameState.Value = {
    if (round == FinalRound || pins.sum < 10)
      FrameState.Open
    else if (pins.length == 2)
      FrameState.Spare
    else
      FrameState.Strike
  }

  def score(bonusRolls: List[Int]): Int = {
    pins.sum + bonusRolls.sum
  }

  def legalScores:Boolean = {
    if (pins.exists(e => e < 0 || e > 10))
      false
    else if (round == 10 && pins.length == 1)
      false
    else if (round == 10 && pins.length == 2)
      pins.head < 10 && pins.sum < 10
    else if (round == 10 && pins.length == 3)
      (pins.head, pins(1), pins(2)) match {
        case (10, 10, _) => true
        case (10, s2, s3) => s2 + s3 <= 10
        case (s1, s2, _) => s1 + s2  == 10
      }
    else
      pins.sum <= 10
  }

  def frameOver:Boolean =
    if (round == FinalRound)
      pins.length == 3 || pins.length == 2 && pins.sum < 10
    else
      pins.length == 2 || pins.sum == 10
}

case class Bowling(frames: List[Frame] = List()) {
  def roll(pins: Int): Bowling = {
    if (frames.isEmpty) {
      Bowling(frames :+ Frame(1, List(pins)))
    } else {
      val last = frames.last
      if (last.frameOver) {
        Bowling(frames :+ Frame(last.round+1, List(pins)))
      } else {
        Bowling(frames.slice(0, frames.length - 1) :+ Frame(last.round, last.pins :+ pins))
      }
    }
  }

  private def bonusRolls(frame: Frame): List[Int] =
    frame.state() match {
      case FrameState.Spare => frames.filter(f => f.round > frame.round).flatMap(f => f.pins).take(1)
      case FrameState.Strike => frames.filter(f => f.round > frame.round).flatMap(f => f.pins).take(2)
      case _ => List()
    }

  def score(): Either[String, Int] =
    if (frames.isEmpty)
      Left("an unstarted game cannot be scored")
    else if (frames.length < 10)
      Left("an incomplete game cannot be scored")
    else if (frames.length > 10)
      Left("cannot roll if game already has ten frames")
    else if (frames.exists(!_.legalScores))
      Left("illegal frame detected")
    else {
      Right(frames.map(f => f.score(bonusRolls(f))).sum)
    }
}