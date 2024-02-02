object Allergen extends Enumeration {
  type Allergen = Value
  val Eggs, Peanuts, Shellfish, Strawberries, Tomatoes, Chocolate, Pollen, Cats: Allergen = Value
}

object Allergies {
  def allergicTo(allergen: Allergen.Value, value: Int): Boolean =
    (value >> allergen.id) % 2 == 1

  def list(value: Int): List[Allergen.Value] =
    Allergen.values.filter(a => allergicTo(a, value)).toList

}
