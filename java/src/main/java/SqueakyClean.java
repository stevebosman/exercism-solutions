class SqueakyClean {
    private static boolean isLowerCaseGreek(final char c) {
        return Character.isLowerCase(c) && c >= '\u0370' && c <= '\u03ff';   
    } 
    
    static String clean(String identifier) {
        final StringBuilder sb = new StringBuilder();
        final char[] chars = identifier.toCharArray();
        boolean upperCaseNext = false;
        for (char c : chars) {
            if (Character.isISOControl(c)) {
                sb.append("CTRL");
                upperCaseNext = false;
            } else if (c == ' ') {
                sb.append("_");
                upperCaseNext = false;
            } else if (c == '-') {
                upperCaseNext = true;
            } else if (Character.isLetter(c) && ! isLowerCaseGreek(c)) {
                if (upperCaseNext) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(c);
                }
                upperCaseNext = false;
            }
        }
        return sb.toString();
    }
}
