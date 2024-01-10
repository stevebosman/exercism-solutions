import java.util.Random;

public class Cipher {
    private final String key;
    private final int keyLength;
    
    public Cipher() {
        this(Cipher.randomKey());
    }

    public Cipher(String key) {
        this.key = key;
        this.keyLength = key.length();
    }

    public String getKey() {
        return key;
    }

    public String encode(String plainText) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < plainText.length(); i++) {
            char next = (char)(plainText.charAt(i) + (key.charAt(i % keyLength) - 'a'));
            if (next > 'z') next = (char)(next - 26);
            sb.append(next);
        }
        return sb.toString();
    }

    public String decode(String cipherText) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < cipherText.length(); i++) {
            char next = (char)(cipherText.charAt(i) - (key.charAt(i % keyLength) - 'a'));
            if (next < 'a') next = (char)(next + 26);
            sb.append(next);
        }
        return sb.toString();
    }

    private static String randomKey() {
        int leftLimit = 'a';
        int rightLimit = 'z';
        int targetStringLength = 100;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    } 

}
