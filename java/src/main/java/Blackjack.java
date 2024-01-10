import java.util.Map;

public class Blackjack {
    private static final Map<String, Integer> CARD_VALUES = Map.ofEntries(
        Map.entry("ace", 11),
        Map.entry("two", 2),
        Map.entry("three", 3),
        Map.entry("four", 4),
        Map.entry("five", 5),
        Map.entry("six", 6),
        Map.entry("seven", 7),
        Map.entry("eight", 8),
        Map.entry("nine", 9),
        Map.entry("ten", 10),
        Map.entry("jack", 10),
        Map.entry("queen", 10),
        Map.entry("king", 10)
    );
    private static final String HIT = "H";
    private static final String PASS = "P";
    private static final String STAND = "S";
    private static final String WIN = "W";

    public int parseCard(String card) {
        return CARD_VALUES.getOrDefault(card, 0);
    }

    public boolean isBlackjack(String card1, String card2) {
        return parseCard(card1) + parseCard(card2) == 21;
    }

    public String largeHand(boolean isBlackjack, int dealerScore) {
        final String result;
        if (isBlackjack) {
            if (dealerScore < 10) {
                result = WIN;
            } else {
                result = STAND;
            }
        } else {
            result = "P";
        }
        return result;
    }

    public String smallHand(int handScore, int dealerScore) {
        final String result;
        if (handScore >= 17) {
            result = STAND;
        } else if (handScore <= 11 || dealerScore >= 7) {
            result = HIT;
        } else {
            result = STAND;
        }
        return result;
    }

    // FirstTurn returns the semi-optimal decision for the first turn, given the cards of the player and the dealer.
    // This function is already implemented and does not need to be edited. It pulls the other functions together in a
    // complete decision tree for the first turn.
    public String firstTurn(String card1, String card2, String dealerCard) {
        int handScore = parseCard(card1) + parseCard(card2);
        int dealerScore = parseCard(dealerCard);

        if (20 < handScore) {
            return largeHand(isBlackjack(card1, card2), dealerScore);
        } else {
            return smallHand(handScore, dealerScore);
        }
    }
}
