object Raindrops {
  def convert(n: Int): String = {
    val pling = n%3 == 0
    val plang = n%5== 0
    val plong = n%7== 0
    if (pling || plang || plong) {
      val sb = new StringBuilder
      if (pling) sb.append("Pling")
      if (plang) sb.append("Plang")
      if (plong) sb.append("Plong")
      sb.toString
    } else {
      n.toString
    }
  }
}

