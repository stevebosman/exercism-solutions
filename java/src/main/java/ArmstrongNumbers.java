class ArmstrongNumbers {

    boolean isArmstrongNumber(final int numberToCheck) {
        if (numberToCheck < 0) {
            throw new IllegalArgumentException("You must supply a non-negative integer");
        }
        int total = 0;
        if (numberToCheck > 0) {
            final int power = 1 + (int)Math.log10(numberToCheck);
            int working = numberToCheck;
            while (working > 0) {
                total += Math.pow(working % 10, power);
                working = working / 10; 
            }
        }
        return total == numberToCheck;

    }

}
