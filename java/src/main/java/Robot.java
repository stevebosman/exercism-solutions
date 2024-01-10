import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


class Robot {
    private static final Set<String> names = new ConcurrentSkipListSet<>();
    private static final Random RANDOM = new Random();

    private String name;

    public Robot() {
        this.name = generateName();
    }

    private char randomCharacter(final char start, final int limit) {
        return (char) (start + RANDOM.nextInt(0, limit));
    }

    private char generateLetter() {
        return randomCharacter('A', 26);
    }

    private char generateDigit() {
        return randomCharacter('0', 10);
    }

    private String generateName() {
        String name;
        do {
            name = String.valueOf(generateLetter())
                    + generateLetter()
                    + generateDigit()
                    + generateDigit()
                    + generateDigit();
        } while (names.contains(name));
        names.add(name);
        return name;
    }

    String getName() {
        return name;
    }

    void reset() {
        final String newName = generateName();
        names.remove(name);
        this.name = newName;
    }

}