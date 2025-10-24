package complexTasks.javaCore.clearCode.urlShortener;

import complexTasks.javaCore.clearCode.urlShortener.storages.InMemoryStorage;
import complexTasks.javaCore.clearCode.urlShortener.storages.UrlStorage;
import complexTasks.javaCore.clearCode.urlShortener.strategies.ShorteningStrategy;

public class Main {
    public static void main(String[] args) {
        ShortenerFactory shortenerFactory = new ShortenerFactory();
        ShorteningStrategy strategyBase62 = shortenerFactory.createStrategy("base62");
        UrlStorage storage = InMemoryStorage.getInstance();

        UrlShortenerService shortenerService = new UrlShortenerService(storage, strategyBase62);

        String shortUrl = shortenerService.shortenUrl("https://example.com/very/long/url321312312");
        System.out.println("Short URL: " + shortUrl);

        String longUrl = shortenerService.expandUrl(shortUrl);
        System.out.println("Original URL: " + longUrl);
    }
}
