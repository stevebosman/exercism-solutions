import scala.annotation.tailrec

object SecretHandshake {
  @tailrec
  private def recurse(command: Int, depth: Int, oldList: List[String]): List[String] = {
    if (command == 0) return oldList
    val newList = if (command % 2 == 0) {
      oldList
    } else {
      (depth) match {
        case (1) => oldList :+ "wink"
        case (2) => oldList :+ "double blink"
        case (3) => oldList :+ "close your eyes"
        case (4) => oldList :+ "jump"
        case (5) => oldList.reverse
      }
    }
    recurse(command / 2, depth + 1, newList)
  }

  def commands(command: Integer): List[String] = {
    recurse(command, 1, List())
  }
}
