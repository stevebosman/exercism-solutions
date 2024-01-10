import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordCount {
    public Map<String, Integer> phrase(String input) {
        final Map<String, Integer> result = new HashMap();
        Matcher m = Pattern.compile("\\w+([\\w']*\\w)?")
                           .matcher(input.toLowerCase());
        
        while (m.find()) {
            final String word = m.group();
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        return result;
    }
}
