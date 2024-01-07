import scala.annotation.tailrec

object ProteinTranslation {
  def nextCodon(s: String): String = {
    if (s.length == 0) 
      "STOP" 
    else {
      s.substring(0,3) match {
        case "UAA" | "UAG" | "UGA" => "STOP"
        case "AUG" => "Methionine"
        case "UUU" | "UUC" => "Phenylalanine"
        case "UUA" | "UUG" => "Leucine"
        case "UCU" | "UCC" | "UCA" | "UCG" => "Serine"
        case "UAU" | "UAC" => "Tyrosine"
        case "UGU" | "UGC" => "Cysteine"
        case "UGG" => "Tryptophan"
      }
    }
  }
  
  @tailrec
  def recurseProteins(seq: Seq[String], rna: String): Seq[String] = {
    nextCodon(rna) match {
      case "STOP" => seq
      case protein => recurseProteins(seq :+ protein, rna.substring(3))
    }  
  }
  
  def proteins(rna: String): Seq[String] = recurseProteins(Seq(), rna)
}