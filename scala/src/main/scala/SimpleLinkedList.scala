trait SimpleLinkedList[T] {
  def isEmpty: Boolean
  def value: T
  def add(item: T): SimpleLinkedList[T]
  def next: SimpleLinkedList[T]
  def reverse: SimpleLinkedList[T]
  def toSeq: Seq[T]
}

class MyLinkedList[T](data: Seq[T]) extends SimpleLinkedList[T] {
  override def isEmpty: Boolean = data.isEmpty

  override def value: T = data.head

  override def add(item: T): SimpleLinkedList[T] = SimpleLinkedList(data :+ item: _*)

  override def next: SimpleLinkedList[T] = SimpleLinkedList(data.tail: _*)

  override def reverse: SimpleLinkedList[T] = SimpleLinkedList(data.reverse: _*)

  override def toSeq: Seq[T] = data
}

object SimpleLinkedList {
  def apply[T](ts: T*): SimpleLinkedList[T] = fromSeq[T](ts)
  def fromSeq[T](seq: Seq[T]): SimpleLinkedList[T] = new MyLinkedList[T](seq)
}
