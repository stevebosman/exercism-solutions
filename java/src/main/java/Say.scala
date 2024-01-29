import scala.collection.immutable.ListMap

object Say {
  private val Units = List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
  private val Teens = List("eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
  private val Tens = List("ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
  private val Triplets = ListMap(
    1000_000_000 -> Some("billion"),
    1000_000 -> Some("million"),
    1000 -> Some("thousand"),
    1 -> None,
  )

  private def inEnglishSubHundred(number: Int): String = (number / 10, number % 10) match {
    case (0, u) => Units(u - 1)
    case (1, u) if u >= 1 => Teens(u - 1)
    case (t, 0) => Tens(t - 1)
    case (t, u) => Tens(t - 1) + "-" + Units(u - 1)
  }

  private def inEnglishSubThousand(number: Int): String = (number / 100, number % 100) match {
    case (0, subHundred) => inEnglishSubHundred(subHundred)
    case (hundreds, 0) => inEnglishSubHundred(hundreds) + " hundred"
    case (hundreds, subHundred) => inEnglishSubHundred(hundreds) + " hundred " + inEnglishSubHundred(subHundred)
  }

  private def inEnglishTriplet(number: Long, divisor: Int, tripletText: Option[String]): List[String] = {
    val subThousand = ((number / divisor) % 1000).toInt
    if (subThousand == 0) List()
    else if (tripletText.isEmpty) List(inEnglishSubThousand(subThousand))
    else List(inEnglishSubThousand(subThousand), tripletText.get)
  }

  def inEnglish(number: Long): Option[String] =
    if (number < 0 || number >= 1000000000000L) None
    else if (number == 0) Some("zero")
    else Some(Triplets.flatMap(e => inEnglishTriplet(number, e._1, e._2)).mkString(" "))
}