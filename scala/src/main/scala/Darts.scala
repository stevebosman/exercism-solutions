object Darts {
  def score(x: Double, y: Double): Int = x * x + y * y match {
    case d2 if d2 <= 1 => 10
    case d2 if d2 <= 25 => 5
    case d2 if d2 <= 100 => 1
    case _ => 0
  }
}