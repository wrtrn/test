package complexTasks.javaCore.clearCode.urlShortener;

import complexTasks.javaCore.clearCode.urlShortener.strategies.Base62Strategy;
import complexTasks.javaCore.clearCode.urlShortener.strategies.ShorteningStrategy;
import complexTasks.javaCore.clearCode.urlShortener.strategies.UuidStrategy;

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
