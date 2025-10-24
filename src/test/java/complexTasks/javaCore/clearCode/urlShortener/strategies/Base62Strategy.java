package complexTasks.javaCore.clearCode.urlShortener.strategies;

public class Base62Strategy implements ShorteningStrategy {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();
    static long id = 1;

    @Override
    public String shorten(String url) {
        String shortCode = encode(id++);
        return shortCode;
    }

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET.charAt((int) (num % BASE)));
            num /= BASE;
        }
        return sb.reverse().toString();
    }
}
