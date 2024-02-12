case class Bst(root: Int, left: Option[Bst] = None, right: Option[Bst] = None) {
  def value: Int = root
  def insert(value: Int): Bst = {
    if (value.compare(root) <= 0) {
      if (left.isEmpty) Bst(root, Some(Bst(value)), right)
      else Bst(root, Some(left.get.insert(value)), right)
    } else {
      if (right.isEmpty) Bst(root, left, Some(Bst(value)))
      else Bst(root, left, Some(right.get.insert(value)))
    }
  }
}

object Bst {
  def fromList(list: List[Int]): Bst = {
    list.tail.foldLeft(Bst(list.head))((acc,v) => acc.insert(v))
  }

  def toList(bst: Bst): List[Int] = (bst.left, bst.value, bst.right) match {
    case (Some(left), v, Some(right)) => toList(left) ++ List(v) ++ toList(right)
    case (Some(left), v, None) => toList(left) ++ List(v)
    case (None, v, Some(right)) => List(v) ++ toList(right)
    case (None, v, None) => List(v)
  }
}