import scala.math.{abs, pow}

object ArmstrongNumbers {
  def isArmstrongNumber(i: Int): Boolean = 
    i == i.toString
           .map(_.asDigit)
           .foldLeft(0)(_ + pow(_, i.toString.length).toInt)
}
