object Etl {
  def transform(scoreMap: Map[Int, Seq[String]]): Map[String, Int] = 
    scoreMap.flatMap {
      case (key,values) => values.map(value => (value.toLowerCase, key))
    }
}
