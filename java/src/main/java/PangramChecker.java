import java.util.HashSet;
import java.util.Set;

public class PangramChecker {

    public boolean isPangram(final String input) {
        return 26 == input.toUpperCase()
                          .chars()
                          .filter(c -> c >= 'A' && c <= 'Z')
                          .distinct()
                          .count();
    }

}
