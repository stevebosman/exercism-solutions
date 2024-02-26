class EmptyBufferException() extends Exception {}

class FullBufferException() extends Exception {}

class CircularBuffer(var capacity: Int) {
  private var buffer: List[Int] = List()

  def write(value: Int): Unit =
    if (buffer.size == capacity) throw new FullBufferException()
    else buffer = buffer :+ value

  def read(): Int =
    if (buffer.isEmpty) throw new EmptyBufferException()
    else {
      val h = buffer.head
      buffer = buffer.tail
      h
    }

  def overwrite(value: Int): Unit =
    if (buffer.size == capacity) buffer = buffer.tail :+ value
    else buffer = buffer :+ value

  def clear(): Unit =
    buffer = List()
}