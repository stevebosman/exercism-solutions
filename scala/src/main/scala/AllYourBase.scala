import scala.annotation.tailrec

object AllYourBase {

  @tailrec
  private def toNewBase(value: Int, endBase: Int, list: List[Int] = List()): List[Int] = {
    if (value == 0) list
    else toNewBase(value / endBase, endBase, list :+ value % endBase)
  }

  def rebase(startBase: Int, digits: List[Int], endBase: Int): Option[List[Int]] =
    if (startBase < 2 || endBase < 2)
      None
    else {
      val base10 = toInt(startBase, digits)
      if (base10 == 0)
        Some(List(0))
      else if (digits.min < 0 || digits.max >= startBase)
        None
      else
        Some(toNewBase(base10, endBase).reverse)
    }

  private def toInt(startBase: Int, digits: List[Int]) = {
    digits.foldLeft(0)((a, b) => a * startBase + b)
  }
}