import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Connect {
    private static final Map<Winner, String> PLAYER_CHARS = Map.ofEntries(
            Map.entry(Winner.PLAYER_O, "O"),
            Map.entry(Winner.PLAYER_X, "X")
    );
    private static final Map<Winner, Character> PLAYER_FLOOD_CHARS = Map.ofEntries(
            Map.entry(Winner.PLAYER_O, '@'),
            Map.entry(Winner.PLAYER_X, '*')
    );
    private final int lastRowIndex;
    private final List<StringBuilder> board = new ArrayList<>();
    private final List<Integer> lineLengths = new ArrayList<>();
    private final List<Integer> firstForX = new ArrayList<>();

    public Connect(final String[] board) {
        this.lastRowIndex = board.length - 1;
        for (final String line: board) {
            this.board.add(new StringBuilder(line));
            this.lineLengths.add(line.length());
            final boolean firstX = line.trim().substring(0,1).equals(PLAYER_CHARS.get(Winner.PLAYER_X));
            this.firstForX.add(firstX ? line.indexOf(PLAYER_CHARS.get(Winner.PLAYER_X)) : -1);
        }
    }

    public Winner computeWinner() {
        final Winner winner;
        if (isWinnerX()) {
            winner = Winner.PLAYER_X;
        } else if (isWinnerO()) {
            winner = Winner.PLAYER_O;
        } else {
            winner = Winner.NONE;
        }
        return winner;
    }

    private boolean isWinnerX() {
        // flood from each owned character on first column.
        boolean result = false;
        final Winner potentialWinner = Winner.PLAYER_X;
        for (int i = 0; i < board.size(); i++) {
            if (firstForX.get(i) != -1) {
                if (flood(i, firstForX.get(i), potentialWinner)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isWinnerO() {
        // flood from each owned character on first row.
        boolean result = false;
        final Winner potentialWinner = Winner.PLAYER_O;
        final StringBuilder line1 = board.get(0);
        int colIndex;
        while ((colIndex = line1.indexOf("O")) != -1) {
            if (flood(0, colIndex, potentialWinner)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean flood(final int rowIndex, final int columnIndex, final Winner potentialWinner) {
        boolean result = false;
        if (isValidIndex(rowIndex, columnIndex)) {
            final StringBuilder row = board.get(rowIndex);
            if (row.substring(columnIndex, columnIndex + 1)
                   .equals(PLAYER_CHARS.get(potentialWinner))) {
                row.setCharAt(columnIndex, PLAYER_FLOOD_CHARS.get(potentialWinner));

                result = (potentialWinner == Winner.PLAYER_X && columnIndex == row.length() - 1)
                        || (potentialWinner == Winner.PLAYER_O && rowIndex == board.size() - 1);
                // check next cell in row
                if (!result) result = flood(rowIndex, columnIndex + 2, potentialWinner);
                // check cells on next row
                if (!result) result = flood(rowIndex + 1, columnIndex + 1, potentialWinner);
                if (!result) result = flood(rowIndex + 1, columnIndex - 1, potentialWinner);
                // put backward steps last as they are likely to be used less.
                // check cells on previous row
                if (!result) result = flood(rowIndex - 1, columnIndex + 1, potentialWinner);
                if (!result) result = flood(rowIndex - 1, columnIndex - 1, potentialWinner);
                // check previous cell in row
                if (!result) result = flood(rowIndex, columnIndex - 2, potentialWinner);
            }
        }
        return result;
    }

    private boolean isValidIndex(final int rowIndex, final int columnIndex) {
        return rowIndex >= 0
                && rowIndex <= lastRowIndex
                && columnIndex >= 0
                && columnIndex <= lineLengths.get(rowIndex);
    }
}
