object Series {
  def largestProduct(length: Int, digits: String): Option[Int] =
    if (length < 1) None
    else if (digits.length < length) None
    else if (! digits.matches("^[0-9]+$")) None
    else Some(digits.map(_.asDigit).sliding(length).map(_.product).max)
}