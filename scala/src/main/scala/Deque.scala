case class Deque[A]() {
  private var items = List[A]()
  def push(item: A): Unit = {
    items = item::items
  }

  def pop(): Option[A] = {
    if (items.isEmpty) None
    else {
      val result = items.headOption
      items = items.tail
      result
    }
  }

  def shift(): Option[A] = {
    if (items.isEmpty) None
    else {
      val result = items.lastOption
      items = items.take(items.length - 1)
      result
    }
  }

  def unshift(item: A): Unit = {
    items = items :+ item
  }
}