object Allergen extends Enumeration {
  type Allergen = Value
  val Eggs: Allergen = Value(0)
  val Peanuts: Allergen = Value(1)
  val Shellfish: Allergen = Value(2)
  val Strawberries: Allergen = Value(3)
  val Tomatoes: Allergen = Value(4)
  val Chocolate: Allergen = Value(5)
  val Pollen: Allergen = Value(6)
  val Cats: Allergen = Value(7)
}

object Allergies {
  def allergicTo(allergen: Allergen.Value, value: Int): Boolean =
    (value >> allergen.id) % 2 == 1

  def list(value: Int): List[Allergen.Value] =
    Allergen.values.filter(a => allergicTo(a, value)).toList

}

