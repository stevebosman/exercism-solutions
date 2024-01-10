class Acronym {
    private final String acronym;
    Acronym(final String phrase) {
        // 1. remove inner quotes
        // 2. keep first letter of each word
        // 3. remove non-letter characters.
        this.acronym = phrase.replaceAll("([A-Za-z])[']([A-Za-z])", "$1$2")
                             .replaceAll("([A-Za-z])[A-Za-z]*", "$1")
                             .replaceAll("[^A-Za-z]+", "")
                             .toUpperCase();
    }

    String get() {
        return acronym;
    }

}
