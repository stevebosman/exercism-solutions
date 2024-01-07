object Luhn {
  private def isOdd(n:Int): Boolean = {
    n%2 == 0
  }

  private def double(n:Int): Int = {
    val doubled = n * 2
    if (doubled > 9) {
      doubled - 9
    } else {
      doubled
    }
  }

  private def convert(v:Int, i:Int): Int = {
    if (isOdd(i)) {
      v
    } else {
      double(v)
    }
  }

  def valid(number:String): Boolean = {
    val clean = number.replaceAll(" ", "").reverse

    (
      clean.length > 1
      && clean.matches("[0-9]+")
      && clean.map(_.asDigit)
              .zipWithIndex
              .map({ case (d, i) => convert(d, i) })
              .sum % 10 == 0
    )
  }
}