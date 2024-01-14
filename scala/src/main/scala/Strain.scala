object Strain {
  def keep[T](list: Seq[T], predicate: T => Boolean): List[T] = list match {
    case Nil => Nil
    case h :: t if predicate(h) => h :: keep(t, predicate)
    case _ :: t => keep(t, predicate)
  }

  def discard[T](list: Seq[T], predicate: T => Boolean): List[T] = list match {
    case Nil => Nil
    case h :: t if !predicate(h) => h :: discard(t, predicate)
    case _ :: t => discard(t, predicate)
  }
}