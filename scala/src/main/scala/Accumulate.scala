import scala.collection.mutable.ListBuffer

class Accumulate {
  def accumulate[A, B](f: (A) => B, list : List[A]): List[B] = {
    val buffer = ListBuffer[B]()
    list.foreach(buffer += f(_))
    buffer.toList
  }
}
