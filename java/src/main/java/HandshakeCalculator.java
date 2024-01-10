import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class HandshakeCalculator {

    List<Signal> calculateHandshake(final int number) {
        final List<Signal> result = new ArrayList<>();
        for (final Signal signal: Signal.values()) {
            if (((number >> signal.ordinal()) & 1) == 1) result.add(signal);
        }
        if ((number & 16) == 16) Collections.reverse(result);
        return result;
    }

}
