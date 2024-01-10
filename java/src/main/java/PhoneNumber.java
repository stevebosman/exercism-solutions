class PhoneNumber {
    final String numberString;

    PhoneNumber(String numberString) {
        // Eliminate valid punctuation.
        // There are still some weird edge cases it will allow through.
        // For instance (223-234-2345 will pass even though a bracket is missing.
        numberString = numberString.replaceAll("[-\\()\\s.]", "").replaceAll("^\\+", "");
        if (numberString.matches(".*[a-zA-Z]+.*")) {
          throw new IllegalArgumentException("letters not permitted");   
        }
        if (numberString.matches(".*[^\\d]+.*")) {
          throw new IllegalArgumentException("punctuations not permitted");   
        }
        if (numberString.length() < 10) {
          throw new IllegalArgumentException("incorrect number of digits");   
        }
        if (numberString.length() > 11) {
          throw new IllegalArgumentException("more than 11 digits");   
        }
        if (numberString.length() == 11 && numberString.charAt(0) != '1') {
          throw new IllegalArgumentException("11 digits must start with 1");   
        }
        
        this.numberString = numberString.substring(numberString.length()-10);

        if (this.numberString.charAt(0) == '0') {
          throw new IllegalArgumentException("area code cannot start with zero");   
        }
        if (this.numberString.charAt(0) == '1') {
          throw new IllegalArgumentException("area code cannot start with one");   
        }
        if (this.numberString.charAt(3) == '0') {
          throw new IllegalArgumentException("exchange code cannot start with zero");   
        }
        if (this.numberString.charAt(3) == '1') {
          throw new IllegalArgumentException("exchange code cannot start with one");   
        }
    }

    String getNumber() {
        return numberString;
    }

}