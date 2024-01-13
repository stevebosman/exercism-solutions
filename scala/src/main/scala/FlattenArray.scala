object FlattenArray {
  def flatten(list: List[Any]): List[Any] = {
    list.filter(_ != null)
      .flatMap(e => e match {
        case list1: List[Any] => flatten(list1)
        case e: Any => List(e)
      })
  }
}