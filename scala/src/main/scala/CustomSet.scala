object CustomSet {
  def fromList(list: List[Int]): Set[Int] = {
    list.toSet
  }

  def empty(set: Set[Int]) : Boolean = {
    set.isEmpty
  }

  def member(set: Set[Int], value: Int) : Boolean = {
    set.contains(value)
  }

  def isSubsetOf(set1: Set[Int], set2: Set[Int]) : Boolean = {
    set2.intersect(set1) == set1
  }

  def isDisjointFrom(set1: Set[Int], set2: Set[Int]) : Boolean = {
    empty(set2.intersect(set1))
  }

  def isEqual(set1: Set[Int], set2: Set[Int]) : Boolean = {
    set2 == set1
  }

  def insert(set: Set[Int], value: Int) : Set[Int] = {
    set + value
  }

  def intersection(set1: Set[Int], set2: Set[Int]) : Set[Int] = {
    set2.intersect(set1)
  }

  def difference(set1: Set[Int], set2: Set[Int]) : Set[Int] = {
    set1 -- set2
  }

  def union(set1: Set[Int], set2: Set[Int]) : Set[Int] = {
    set1 ++ set2
  }

}