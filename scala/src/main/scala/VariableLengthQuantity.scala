import scala.annotation.tailrec

object VariableLengthQuantity {
  def encode(input: List[Int]): List[Int] = {
    @tailrec
    def encodeVal(input: Int, list: List[Int] = List()): List[Int] =
      input >>> 7 match {
        case 0 if list.isEmpty => List(input)
        case 0 => (input | 0x80) +: list
        case i if list.isEmpty => encodeVal(i, (input & 0x7f) +: list)
        case i => encodeVal(i, ((input & 0x7f) | 0x80) +: list)
      }

    input.flatMap(i => encodeVal(i))
  }

  def decode(input: List[Int]): Either[String, List[Int]] = {
    val tuple = input.foldLeft((0, List[Int](), false))((a, v) => v match {
      case i if i < 0x80 => (0, ((0x80 * a._1) + i) +: a._2, false)
      case i => ((0x80 * a._1) + (i & 0x7f), a._2, true)
    })
    if (tuple._3) Left("Incomplete sequence")
    else Right(tuple._2.reverse)
  }
}