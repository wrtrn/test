package clearCode.complexTasks.urlShortener;

import clearCode.complexTasks.urlShortener.strategies.Base62Strategy;
import clearCode.complexTasks.urlShortener.strategies.ShorteningStrategy;
import clearCode.complexTasks.urlShortener.strategies.UuidStrategy;

public class ShortenerFactory {
    ShorteningStrategy createStrategy(String strategy) {
        switch (strategy) {
            case "base62":
                return new Base62Strategy();
            case "uuid":
                return new UuidStrategy();
            default:
                return new UuidStrategy();
        }
    }
}
