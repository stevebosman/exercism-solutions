import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Anagram {
    private final String originalWord;
    private final String letters;
    public Anagram(final String word) {
        this.originalWord = word;
        this.letters = getSortedCharacters(word);
    }

    private String getSortedCharacters(final String word) {
        final List<String> letters = Arrays.asList(word.toUpperCase().split(""));
        letters.sort(String::compareTo);
        return String.join("", letters);
    }

    public List<String> match(final List<String> candidates) {
        return candidates.stream().filter(s -> !originalWord.equalsIgnoreCase(s) && letters.equals(getSortedCharacters(s))).collect(Collectors.toList());
    }
}