object Sublist extends Enumeration {
  val Equal, Unequal, Sublist, Superlist = Value

  def sublist(list1: List[Int], list2: List[Int]): Value = {
    if (list1 == list2) Equal
    else if (list1.isEmpty) Sublist
    else if (list2.isEmpty) Superlist
    else if (isSubList(list1, list2)) Sublist
    else if (isSubList(list2, list1)) Superlist
    else Unequal
  }

  private def isSubList(list1: List[Int], list2: List[Int]) = {
    list2.sliding(list1.length).contains(list1)
  }
}

