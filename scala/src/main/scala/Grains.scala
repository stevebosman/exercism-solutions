object Grains {
  def square(square:Int):Option[BigInt] = {
    if (square > 0 && square <= 64) {
      Some(BigInt(1) << (square - 1))
    } else {
      None
    }
  }

  def total:BigInt = {
    (BigInt(1) << 64) - 1
  }
}