import scala.util.Try

object RnaTranscription {
  def complement(nucleotide: Char): String = nucleotide match {
    case 'G' => "C"
    case 'C' => "G"
    case 'T' => "A"
    case 'A' => "U"
  }

  def toRna(sequence: String): Option[String] = Try(sequence.flatMap(c => complement(c))).toOption
}