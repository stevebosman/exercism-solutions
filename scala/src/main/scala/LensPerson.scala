import java.time.LocalDate
import monocle.syntax.all._
import monocle.macros.syntax.lens._

object LensPerson {
  case class Person(_name: Name, _born: Born, _address: Address)

  case class Name(_foreNames: String /*Space separated*/ , _surName: String)

  // Value of java.time.LocalDate.toEpochDay
  type EpochDay = Long

  case class Born(_bornAt: Address, _bornOn: EpochDay)

  case class Address(_street: String, _houseNumber: Int,
    _place: String /*Village / city*/ , _country: String)

  // Valid values of Gregorian are those for which 'java.time.LocalDate.of'
  // returns a valid LocalDate.
  case class Gregorian(_year: Int, _month: Int, _dayOfMonth: Int)

  // Implement these.
  val bornStreet: Born => String =
    (brn: Born) => brn._bornAt._street

  val setCurrentStreet: String => Person => Person =
    street => person => person.lens(_._address._street).modify(_ => street)

  val setBirthMonth: Int => Person => Person =
    month => person => person.lens(_._born._bornOn).modify(b => LocalDate.ofEpochDay(b).withMonth(month).toEpochDay)

  // Transform both birth and current street names.
  val renameStreets: (String => String) => Person => Person =
    function => person => person.lens(_._address._street).modify(function).lens(_._born._bornAt._street).modify(function)
}
