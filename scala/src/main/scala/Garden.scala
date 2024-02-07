import Plant.Plant

object Plant extends Enumeration {
  type Plant = Value
  val Radishes, Clover, Grass, Violets: Plant = Value

  val LookUp: Map[Char, Plant] = Map('C' -> Clover,'G' -> Grass,'R' -> Radishes,'V' -> Violets)
}
case class Garden(rows: Seq[Seq[Plant]]) {
  def plants(name: String): Seq[Plant] = {
    val firstPlantIndex = 2 * (name.head - 'A')

    def childsPlantsInRow(row: Seq[Plant]): Seq[Plant] =
      row.zipWithIndex.filter(e => e._2 == firstPlantIndex || e._2 == firstPlantIndex + 1).map(_._1)

    rows.flatMap(childsPlantsInRow)
  }
}

object Garden {
  def defaultGarden(g: String): Garden = {
    def createRow(r: String): Seq[Plant] = r.flatMap(Plant.LookUp.get)

    Garden(g.split("\n").map(createRow))
  }
}