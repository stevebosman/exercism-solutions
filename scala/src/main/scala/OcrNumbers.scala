object OcrNumbers {
  // Parts
  private val Blank = (' ', ' ', ' ', ' ')

  private val Alternates = ('_', '_', '_', ' ')
  private val TopBottom = ('_', ' ', '_', ' ')
  private val Top = ('_', ' ', ' ', ' ')
  private val Middle = (' ', '_', ' ', ' ')

  private val Side = (' ', '|', '|', ' ')
  private val TopSide = (' ', '|', ' ', ' ')
  private val BottomSide = (' ', ' ', '|', ' ')

  private val Digits = Map(
    List(Side, TopBottom, Side) -> "0",
    List(Blank, Blank, Side) -> "1",
    List(BottomSide, Alternates, TopSide) -> "2",
    List(Blank, Alternates, Side) -> "3",
    List(TopSide, Middle, Side) -> "4",
    List(TopSide, Alternates, BottomSide) -> "5",
    List(Side, Alternates, BottomSide) -> "6",
    List(Blank, Top, Side) -> "7",
    List(Side, Alternates, Side) -> "8",
    List(TopSide, Alternates, Side) -> "9"
  )

  private def convertLine(input: List[String]): String =
    input.head.lazyZip(input(1)).lazyZip(input(2)).lazyZip(input(3)).toList
      .grouped(3).map(s => Digits.getOrElse(s, "?")).mkString("")

  def convert(input: List[String]): String =
    if (input.size % 4 != 0 || input.head.length % 3 != 0) "?"
    else input.grouped(4).map(convertLine).mkString(",")
}