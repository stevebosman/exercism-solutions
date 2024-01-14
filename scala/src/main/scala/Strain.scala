import scala.collection.mutable.ListBuffer

object Strain {
  def keep[T](list: Seq[T], predicate: T => Boolean): Seq[T] = {
    val buffer = ListBuffer[T]()
    list.foreach(e => if (predicate(e)) buffer += e)
    buffer.toList
  }

  def discard[T](list: Seq[T], predicate: T => Boolean): Seq[T] = {
    val buffer = ListBuffer[T]()
    list.foreach(e => if (!predicate(e)) buffer += e)
    buffer.toList
  }
}