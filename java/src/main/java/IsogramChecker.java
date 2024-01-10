import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class IsogramChecker {

    boolean isIsogram(String phrase) {
        final Set<Character> charSet = new HashSet<>();
        int letterCount = 0;
        for (char c: phrase.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;            
                charSet.add(Character.toLowerCase(c));
            }
        }
        return letterCount == charSet.size();
    }

}
