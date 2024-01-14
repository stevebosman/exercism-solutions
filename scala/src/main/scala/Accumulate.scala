class Accumulate {
  def accumulate[A, B](f: (A) => B, list : List[A]): List[B] = list match {
    case Nil => Nil
    case h :: t => f(h) :: accumulate(f, t)
  }
}