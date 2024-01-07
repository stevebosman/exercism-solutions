class GradeSchool {
  type DB = Map[Int, Seq[String]]

  private var _db: DB = Map.empty

  def add(name: String, g: Int) = {
    _db = db + (g -> {grade(g) :+ name})
  }

  def db: DB = _db

  def grade(g: Int): Seq[String] = db.getOrElse(g, Seq())

  def sorted: DB = Map(db.mapValues(_.sorted).toSeq.sortBy(_._1):_*)
}
