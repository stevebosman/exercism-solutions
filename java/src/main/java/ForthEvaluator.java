import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

class ForthEvaluator {
  private static final Map<String, Consumer<Deque<Integer>>> BASE_FUNCTIONS = Map.ofEntries(
          Map.entry("+", stack -> {
            if (stack.size() < 2)
              throw new IllegalArgumentException("Addition requires that the stack contain at least 2 values");
            stack.offerLast(stack.removeLast() + stack.removeLast());
          }),
          Map.entry("-", stack -> {
            if (stack.size() < 2)
              throw new IllegalArgumentException("Subtraction requires that the stack contain at least 2 values");
            stack.offerLast(-stack.removeLast() + stack.removeLast());
          }),
          Map.entry("*", stack -> {
            if (stack.size() < 2)
              throw new IllegalArgumentException("Multiplication requires that the stack contain at least 2 values");
            stack.offerLast(stack.removeLast() * stack.removeLast());
          }),
          Map.entry("/", stack -> {
            if (stack.size() < 2)
              throw new IllegalArgumentException("Division requires that the stack contain at least 2 values");
            final Integer v2 = stack.removeLast();
            if (v2 == 0) throw new IllegalArgumentException("Division by 0 is not allowed");
            final Integer v1 = stack.removeLast();
            stack.offerLast(v1 / v2);
          }),
          Map.entry("dup", stack -> {
            if (stack.size() < 1)
              throw new IllegalArgumentException("Duplicating requires that the stack contain at least 1 value");
            stack.offerLast(stack.peekLast());
          }),
          Map.entry("drop", stack -> {
            if (stack.size() < 1)
              throw new IllegalArgumentException("Dropping requires that the stack contain at least 1 value");
            stack.removeLast();
          }),
          Map.entry("swap", stack -> {
            if (stack.size() < 2)
              throw new IllegalArgumentException("Swapping requires that the stack contain at least 2 values");
            final Integer v2 = stack.removeLast();
            final Integer v1 = stack.removeLast();
            stack.offerLast(v2);
            stack.offerLast(v1);
          }),
          Map.entry("over", stack -> {
            if (stack.size() < 2)
              throw new IllegalArgumentException("Overing requires that the stack contain at least 2 values");
            final Integer v2 = stack.removeLast();
            final Integer v1 = stack.removeLast();
            stack.offerLast(v1);
            stack.offerLast(v2);
            stack.offerLast(v1);
          })
  );
  final Deque<Integer> stack = new ArrayDeque<>();
  final Map<String, List<String>> customFunctions = new HashMap<>();

  public static boolean isInteger(final String str) {
    return str.matches("-?\\d+");
  }

  List<Integer> evaluateProgram(final List<String> input) {
    for (final String line : input) {
      final List<String> args = List.of(line.toLowerCase()
                                            .split(" "));
      final String primaryArg = args.get(0);
      if (":".equals(primaryArg)) {
        final String wordName = args.get(1);
        if (isInteger(wordName)) {
          throw new IllegalArgumentException("Cannot redefine numbers");
        }
        // definition - for simplicity currently assume ends with a semicolon
        final List<String> remediatedArgs = new ArrayList<>();
        final List<String> customArgs = args.subList(2, args.size() - 1);
        for (final String customArg : customArgs) {
          if (customFunctions.containsKey(customArg)) {
            remediatedArgs.addAll(customFunctions.get(customArg));
          } else {
            remediatedArgs.add(customArg);
          }
        }
        customFunctions.put(wordName, remediatedArgs);
      } else {
        runArgs(args);
      }
    }

    return stack.stream()
                .toList();
  }

  private void runArgs(final List<String> args) {
    for (final String arg : args) {
      if (customFunctions.containsKey(arg)) {
        runArgs(customFunctions.get(arg));
      } else if (BASE_FUNCTIONS.containsKey(arg)) {
        BASE_FUNCTIONS.get(arg)
                      .accept(stack);
      } else if (isInteger(arg)) {
        stack.offerLast(Integer.valueOf(arg));
      } else {
        throw new IllegalArgumentException("No definition available for operator \"" + arg + "\"");
      }
    }
  }
}
