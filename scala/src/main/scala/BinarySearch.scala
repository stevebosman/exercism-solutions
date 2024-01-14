import scala.annotation.tailrec

object BinarySearch {
  @tailrec
  private def binary(offset:Int, list: List[Int], item :Int): Option[Int] = {
    val length = list.length
    if (length==0) return None
    length/2 match {
      case index if list(index) > item => binary(offset, list.slice(0, index), item)
      case index if list(index) < item => binary(offset + 1 + index, list.slice(index+1, length), item)
      case index => Some(offset + index)
    }
  }

  def find(list: List[Int], item :Int): Option[Int] = {
    binary(0, list, item)
  }
}