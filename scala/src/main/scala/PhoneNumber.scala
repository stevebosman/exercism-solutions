import scala.annotation.tailrec

object PhoneNumber {
  private val LEGAL_PUNCTUATION = " ()-+."

  private def validateNormalized(normalized: String): Option[String] = {
    def validateCodes(normalized: String): Option[String] = {
      if (normalized.head == '0' || normalized.head == '1' || normalized(3) == '0' || normalized(3) == '1') {
        None
      } else {
        Some(normalized)
      }
    }
    if (normalized.length == 11 && normalized(0) == '1') {
      validateCodes(normalized.tail)
    } else if (normalized.length == 10) {
      validateCodes(normalized)
    } else {
      None
    }
  }

  @tailrec
  private def normalize(normalized: String, number: String): Option[String] = {
    if (number.isEmpty) {
      return Some(normalized)
    }
    number.head match {
      case c if c.isDigit => normalize(normalized + c, number.tail)
      case c if LEGAL_PUNCTUATION.contains(c) => normalize(normalized, number.tail)
      case _ => None
    }
  }

  def clean(str: String): Option[String] = {
    normalize("", str) match {
      case None => None
      case Some(normalized) => validateNormalized(normalized)
    }
  }
}