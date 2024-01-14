object Strain {
  def keep[T](expectedList: Seq[T], function: T => Boolean): Seq[T] = {
    expectedList.filter(function.apply).toList
  }
  def discard[T](expectedList: Seq[T], function: T => Boolean): Seq[T] = {
    expectedList.filterNot(function.apply).toList
  }
}