import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ProteinTranslator {
  List<String> translate(final String rnaSequence) {
    final String[] codons = rnaSequence.split("(?<=\\G.{3})");
    return Arrays.stream(codons)
                 .map(Codon::valueOf)
                 .takeWhile(c -> !c.isStop())
                 .map(p -> p.getProtein()
                            .getProteinName())
                 .collect(Collectors.toList());
  }
}

enum Protein {
  PHENYLALANINE("Phenylalanine"),
  LEUCINE("Leucine"),
  ISOLEUCINE("Isoleucine"),
  METHIONINE("Methionine"),
  VALINE("Valine"),
  SERINE("Serine"),
  PROLINE("Proline"),
  THREONINE("Threonine"),
  ALANINE("Alanine"),
  TYROSINE("Tyrosine"),
  CYSTEINE("Cysteine"),
  HISTIDINE("Histidine"),
  GLUTAMINE("Glutamine"),
  ASPARAGINE("Asparagine"),
  LYSINE("Lysine"),
  ASPARTIC_ACID("Aspartic Acid"),
  GLUTAMIC_ACID("Glutamic Acid"),
  ARGININE("Arginine"),
  GLYCINE("Glycine"),
  TRYPTOPHAN("Tryptophan");

  final String proteinName;

  Protein(final String proteinName) {
    this.proteinName = proteinName;
  }

  public String getProteinName() {
    return proteinName;
  }
}

enum Codon {
  // From https://en.wikipedia.org/wiki/DNA_and_RNA_codon_tables
  UUU(Protein.PHENYLALANINE),
  UUC(Protein.PHENYLALANINE),
  UUA(Protein.LEUCINE),
  UUG(Protein.LEUCINE),
  CUU(Protein.LEUCINE),
  CUC(Protein.LEUCINE),
  CUA(Protein.LEUCINE),
  CUG(Protein.LEUCINE),
  AUU(Protein.ISOLEUCINE),
  AUC(Protein.ISOLEUCINE),
  AUA(Protein.ISOLEUCINE),
  AUG(Protein.METHIONINE),
  GUU(Protein.VALINE),
  GUC(Protein.VALINE),
  GUA(Protein.VALINE),
  GUG(Protein.VALINE),

  UCU(Protein.SERINE),
  UCC(Protein.SERINE),
  UCA(Protein.SERINE),
  UCG(Protein.SERINE),
  CCU(Protein.PROLINE),
  CCC(Protein.PROLINE),
  CCA(Protein.PROLINE),
  CCG(Protein.PROLINE),
  ACU(Protein.THREONINE),
  ACC(Protein.THREONINE),
  ACA(Protein.THREONINE),
  ACG(Protein.THREONINE),
  GCU(Protein.ALANINE),
  GCC(Protein.ALANINE),
  GCA(Protein.ALANINE),
  GCG(Protein.ALANINE),

  UAU(Protein.TYROSINE),
  UAC(Protein.TYROSINE),
  UAA(null),
  UAG(null),
  CAU(Protein.HISTIDINE),
  CAC(Protein.HISTIDINE),
  CAA(Protein.GLUTAMINE),
  CAG(Protein.GLUTAMINE),
  AAU(Protein.ASPARAGINE),
  AAC(Protein.ASPARAGINE),
  AAA(Protein.LYSINE),
  AAG(Protein.LYSINE),
  GAU(Protein.ASPARTIC_ACID),
  GAC(Protein.ASPARTIC_ACID),
  GAA(Protein.GLUTAMIC_ACID),
  GAG(Protein.GLUTAMIC_ACID),

  UGU(Protein.CYSTEINE),
  UGC(Protein.CYSTEINE),
  UGA(null),
  UGG(Protein.TRYPTOPHAN),
  CGU(Protein.ARGININE),
  CGC(Protein.ARGININE),
  CGA(Protein.ARGININE),
  CGG(Protein.ARGININE),
  AGU(Protein.SERINE),
  AGC(Protein.SERINE),
  AGA(Protein.ARGININE),
  AGG(Protein.ARGININE),
  GGU(Protein.GLYCINE),
  GGC(Protein.GLYCINE),
  GGA(Protein.GLYCINE),
  GGG(Protein.GLYCINE);

  private final Protein protein;

  Codon(final Protein protein) {
    this.protein = protein;
  }

  public Protein getProtein() {
    return protein;
  }

  public boolean isStop() {
    return protein == null;
  }
}
