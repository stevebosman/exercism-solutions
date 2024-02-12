case class Bst[A:Ordering](value: A, left: Option[Bst[A]] = None, right: Option[Bst[A]] = None) {
  def insert(newValue: A): Bst[A] = {
    if (implicitly[Ordering[A]].lteq(newValue, value)) {
      if (left.isEmpty) Bst(value, Some(Bst(newValue)), right)
      else Bst(value, Some(left.get.insert(newValue)), right)
    } else {
      if (right.isEmpty) Bst(value, left, Some(Bst(newValue)))
      else Bst(value, left, Some(right.get.insert(newValue)))
    }
  }
}

object Bst {
  def fromList[A:Ordering](list: List[A]): Bst[A] = {
    list.tail.foldLeft(Bst(list.head))((acc,v) => acc.insert(v))
  }

  def toList[A:Ordering](bst: Bst[A]): List[A] = (bst.left, bst.value, bst.right) match {
    case (Some(left), v, Some(right)) => toList(left) ++ List(v) ++ toList(right)
    case (Some(left), v, None) => toList(left) ++ List(v)
    case (None, v, Some(right)) => List(v) ++ toList(right)
    case (None, v, None) => List(v)
  }
}