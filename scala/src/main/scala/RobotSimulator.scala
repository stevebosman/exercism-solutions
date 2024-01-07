package robot-simulator
object Bearing extends Enumeration {
  val North, South, East, West = Value
}

case class Robot(bearing: Bearing.Value, coordinates: (Integer, Integer)) {
  def advance: Robot = {
    bearing match {
      case Bearing.North => Robot(bearing, (coordinates._1, coordinates._2 + 1))
      case Bearing.East => Robot(bearing, (coordinates._1 + 1, coordinates._2))
      case Bearing.South => Robot(bearing, (coordinates._1, coordinates._2 - 1))
      case Bearing.West => Robot(bearing, (coordinates._1 - 1, coordinates._2))
    }
  }

  def turnRight: Robot = {
    bearing match {
      case Bearing.North => Robot(Bearing.East, coordinates)
      case Bearing.East => Robot(Bearing.South, coordinates)
      case Bearing.South => Robot(Bearing.West, coordinates)
      case Bearing.West => Robot(Bearing.North, coordinates)
    }
  }

  def turnLeft: Robot = {
    bearing match {
      case Bearing.North => Robot(Bearing.West, coordinates)
      case Bearing.East => Robot(Bearing.North, coordinates)
      case Bearing.South => Robot(Bearing.East, coordinates)
      case Bearing.West => Robot(Bearing.South, coordinates)
    }
  }

  def simulate(commands: String): Robot = commands.foldLeft(this) {
    case (robot, 'R') => robot.turnRight
    case (robot, 'L') => robot.turnLeft
    case (robot, 'A') => robot.advance
  }
}
