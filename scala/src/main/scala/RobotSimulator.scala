object Bearing extends Enumeration {
  val North, South, East, West = Value
}

case class Robot2(bearing: Bearing.Value, coordinates: (Integer, Integer)) {
  def advance: Robot2 = {
    bearing match {
      case Bearing.North => Robot2(bearing, (coordinates._1, coordinates._2 + 1))
      case Bearing.East => Robot2(bearing, (coordinates._1 + 1, coordinates._2))
      case Bearing.South => Robot2(bearing, (coordinates._1, coordinates._2 - 1))
      case Bearing.West => Robot2(bearing, (coordinates._1 - 1, coordinates._2))
    }
  }

  def turnRight: Robot2 = {
    bearing match {
      case Bearing.North => Robot2(Bearing.East, coordinates)
      case Bearing.East => Robot2(Bearing.South, coordinates)
      case Bearing.South => Robot2(Bearing.West, coordinates)
      case Bearing.West => Robot2(Bearing.North, coordinates)
    }
  }

  def turnLeft: Robot2 = {
    bearing match {
      case Bearing.North => Robot2(Bearing.West, coordinates)
      case Bearing.East => Robot2(Bearing.North, coordinates)
      case Bearing.South => Robot2(Bearing.East, coordinates)
      case Bearing.West => Robot2(Bearing.South, coordinates)
    }
  }

  def simulate(commands: String): Robot2 = commands.foldLeft(this) {
    case (robot, 'R') => robot.turnRight
    case (robot, 'L') => robot.turnLeft
    case (robot, 'A') => robot.advance
  }
}
