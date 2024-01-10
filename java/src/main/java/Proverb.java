class Proverb {
    final String recitation;
    Proverb(final String[] words) {
        final StringBuilder sb = new StringBuilder();
        if (words.length > 0) {
            for (int i = 1; i < words.length; i++) {
                sb.append("For want of a ")
                  .append(words[i - 1])
                  .append(" the ")
                  .append(words[i])
                  .append(" was lost.\n");
            }
            sb.append("And all for the want of a ")
              .append(words[0])
              .append(".");
        }
        recitation = sb.toString();
    }

    String recite() {
        return recitation;
    }

}
