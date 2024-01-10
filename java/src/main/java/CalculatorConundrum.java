import java.util.Objects;

class CalculatorConundrum {
    private void validateOperation(final String operation) {
        if (Objects.isNull(operation)) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }
    }
    
    private int add(final int operand1, final int operand2) {
        return operand1 + operand2;
    }

    private int multiply(final int operand1, final int operand2) {
        return operand1 * operand2;
    }

    private int divide(final int operand1, final int operand2) {
        try {
            return operand1 / operand2;
        } catch (final ArithmeticException e) {
            // The existing tests seem to expect forcing the exception, but IMNSHO it is bad practice.
            // It assumes receiving ArithmeticException will only ever have the one meaning.
            // If in the future there is another possible error you may get hard to debug weird errors.
            // I think it is much better to do a check prior to performing the operation. 
            throw new IllegalOperationException("Division by zero is not allowed", e);
        }
    }

    public String calculate(final int operand1, final int operand2, final String operation) {
        validateOperation(operation);
        
        final int result;
        switch (operation) {
            case "+": result = add(operand1, operand2); break;
            case "*": result = multiply(operand1, operand2); break;
            case "/": result = divide(operand1, operand2); break;
            default: throw new IllegalOperationException(String.format("Operation '%s' does not exist", operation));
        }
        return String.format("%d %s %d = %d", operand1, operation, operand2, result);
    }
}
