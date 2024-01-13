object NumberType extends Enumeration {
  val Perfect, Abundant, Deficient: NumberType.Value = Value
}

object PerfectNumbers {
  private def factors(num: Int) = (1 until 1 + num/2).filter(f => num % f == 0)
  private def aliquotSum(num: Int) = factors(num).sum

  def classify(num: Int): Either[String, NumberType.Value] = {
    if (num <= 0) return Left("Classification is only possible for natural numbers.")

    val aliquot = aliquotSum(num)
    if (aliquot == num) Right(NumberType.Perfect)
    else if (aliquot < num) Right(NumberType.Deficient)
    else Right(NumberType.Abundant)
  }
}
