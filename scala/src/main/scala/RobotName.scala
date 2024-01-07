import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Random

class Robot {
  var name = RobotName.next()

  def reset(): Unit = {
    name = RobotName.next()
  }
}

object RobotName {
  private val usedNames = mutable.HashSet.empty[String]

  private val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  private def randomLetter = letters(Random.nextInt(letters.length))
  
  private def randomName = f"${randomLetter}${randomLetter}${Random.nextInt(1000)}%03d"
  
  def next(): String = {
    val temp = randomName
    temp match {
      case name if usedNames contains name => 
        next()
      case name =>
        usedNames += name
        name
    }
  }
}