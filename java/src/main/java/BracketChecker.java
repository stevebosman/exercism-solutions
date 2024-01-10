import java.util.Stack;

class BracketChecker {
    final String expression;
    BracketChecker(final String expression) {
        this.expression = expression;
    }

    boolean areBracketsMatchedAndNestedCorrectly() {
        final Stack<Character> brackets = new Stack<>();
        for (final char c: expression.toCharArray()) {
            if (c == '(') {
                brackets.push(')');
            } else if (c == '{') {
                brackets.push('}');
            } else if (c == '[') {
                brackets.push(']');
            } else if (c == ')' || c == '}' || c == ']') {
                if (brackets.isEmpty() || c != brackets.pop()) {
                  return false;
                }
            }
        }
        return brackets.isEmpty();
    }

}