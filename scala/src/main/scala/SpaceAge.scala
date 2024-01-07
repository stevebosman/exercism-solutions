object SpaceAge {
  private val EARTH_YEAR_SECONDS = 31_557_600
  private def calculateAge(orbitalPeriod: Double) = 
    (age: Double) => age / EARTH_YEAR_SECONDS / orbitalPeriod
  
  val onEarth = calculateAge(1)

  val onVenus = calculateAge(0.61519726)

  val onMercury = calculateAge(0.2408467)

  val onMars = calculateAge(1.8808158)

  val onJupiter = calculateAge(11.862615)

  val onSaturn = calculateAge(29.447498)

  val onUranus = calculateAge(84.016846)

  val onNeptune = calculateAge(164.79132)
}
