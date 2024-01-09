const RNA_COMPLEMENTS = new Map<string, string>([
  ["G", "C"],
  ["C", "G"],
  ["T", "A"],
  ["A", "U"],
]);

export function toRna(sequence: string): string {
  return Array.from(sequence).map((v) => {
    const next = RNA_COMPLEMENTS.get(v)
    if (!next) {
      throw new Error("Invalid input DNA.")
    }
    return next
  }).join('')
}
