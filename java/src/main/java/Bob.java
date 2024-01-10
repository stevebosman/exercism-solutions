class Bob {
    private static final String QUESTION ="Sure.";// This is his response if you ask him a question, such as "How are you?" The convention used for questions is that it ends with a question mark.
    private static final String YELL ="Whoa, chill out!";// This is his answer if you YELL AT HIM. The convention used for yelling is ALL CAPITAL LETTERS.
    private static final String YELL_QUESTION ="Calm down, I know what I'm doing!";// This is what he says if you yell a question at him.
    private static final String SILENCE ="Fine. Be that way!";// This is how he responds to silence. The convention used for silence is nothing, or various combinations of whitespace characters.
    private static final String OTHER ="Whatever.";// This is what he answers to anything else.

    String hey(String input) {
        input = input.trim();
        final boolean silence = input.matches("^\\s*$");
        final boolean question = input.endsWith("?");
        final boolean lower = input.matches("^.*[a-z]+.*$");
        final boolean upper = input.matches("^.*[A-Z]+.*$");

        final String result;
        if (upper && !lower) {
            if (question) {
                result = YELL_QUESTION;
            } else {
                result = YELL;
            }
        } else if (question) {
            result = QUESTION;
        } else if (silence) {
            result = SILENCE;
        } else {
            result = OTHER;
        }
        return result;
    }
}
