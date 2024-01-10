import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class HighScores {
    private final List<Integer> highScores;

    public HighScores(final List<Integer> highScores) {
        this.highScores = highScores;
    }

    List<Integer> scores() {
        return highScores;
    }

    Integer latest() {
        return highScores.get(highScores.size() - 1);
    }

    Integer personalBest() {
        return highScores.stream().max(Comparator.naturalOrder()).orElse(0);
    }

    List<Integer> personalTopThree() {
        return highScores.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
    }

}
