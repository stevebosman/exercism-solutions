object Sublist {
  def sublist(list1: List[Int], list2: List[Int]): SublistType = {
    if (list1 == list2) Equal
    else if (list1.isEmpty) Sublist
    else if (list2.isEmpty) Superlist
    else if (list1.length < list2.length && list2.sliding(list1.length).contains(list1)) Sublist
    else if (list1.length > list2.length && list1.sliding(list2.length).contains(list2)) Superlist
    else Unequal
  }

  def Equal: SublistType = SublistType(1)
  def Sublist: SublistType = SublistType(2)
  def Superlist: SublistType = SublistType(3)
  def Unequal: SublistType = SublistType(4)
}

case class SublistType(id: Int)
