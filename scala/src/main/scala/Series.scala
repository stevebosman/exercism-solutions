object Series {
  def slices(length: Int, str: String): Seq[Seq[Int]] =
    str.map(_.toInt - '0').sliding(length).toList
}