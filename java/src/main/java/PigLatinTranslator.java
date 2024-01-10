import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class PigLatinTranslator {
    //Rule 3: If a word starts with a consonant sound followed by "qu", move it to the end of the word,
    // and then add an "ay" sound to the end of the word (e.g. "square" -> "aresquay").
    //Rule 4: If a word contains a "y" after a consonant cluster or as the second letter in a two letter word
    // it makes a vowel sound (e.g. "rhythm" -> "ythmrhay", "my" -> "ymay").
    private static final Pattern RULE3_4_PATTERN = Pattern.compile("^([^aeiou]?qu|[^aeiou][^aeiouy]*).*");

    public String translate(final String word) {
        return Arrays.stream(word.split(" ")).map(this::translateWord).collect(Collectors.joining(" "));
    }

    public String translateWord(final String string) {
        final String result;
        if (string.matches("^([aeiou]|xr|yt).*")) {
            //Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of the word.
            // Please note that "xr" and "yt" at the beginning of a word make vowel sounds (e.g. "xray" -> "xrayay", "yttria" -> "yttriaay").
            result = string;
        } else {
            final Matcher matcher = RULE3_4_PATTERN.matcher(string);
            if (matcher.matches()) {
                final String match = matcher.group(1);
                result = string.substring(match.length()) + match;
            } else {
                //Rule 2: If a word begins with a consonant sound, move it to the end of the word and then add an "ay" sound to the end of the word.
                // Consonant sounds can be made up of multiple consonants, such as the "ch" in "chair" or "st" in "stand" (e.g. "chair" -> "airchay").
                result = string;
            }
        }
        return result + "ay";
    }

}