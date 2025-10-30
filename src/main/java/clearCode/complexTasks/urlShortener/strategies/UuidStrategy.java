package clearCode.complexTasks.urlShortener.strategies;

import java.util.UUID;

public class UuidStrategy implements ShorteningStrategy {
    @Override
    public String shorten(String url) {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
