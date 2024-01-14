object Strain {
  def keep[T](list: Seq[T], predicate: T => Boolean): Seq[T] = list.filter(predicate)
  def discard[T](list: Seq[T], predicate: T => Boolean): Seq[T] = list.filterNot(predicate)
}