class MicroBlog {
    private static final int MAX_LENGTH = 5;

    public String truncate(final String input) {
        final int[] codePoints = input.codePoints()
                                .limit(MAX_LENGTH)
                                .toArray();
        return new String(codePoints, 0, Math.min(codePoints.length, MAX_LENGTH));
    }
}
