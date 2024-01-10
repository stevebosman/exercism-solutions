import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ReverseString {
    String reverse(final String inputString) {
        return new StringBuilder(inputString).reverse().toString();
    }
  
}
