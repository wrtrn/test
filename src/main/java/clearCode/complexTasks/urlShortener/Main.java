package clearCode.complexTasks.urlShortener;

import clearCode.complexTasks.urlShortener.storages.InMemoryStorage;
import clearCode.complexTasks.urlShortener.storages.UrlStorage;
import clearCode.complexTasks.urlShortener.strategies.ShorteningStrategy;

public class Main {
    public static void main(String[] args) {
        ShortenerFactory shortenerFactory = new ShortenerFactory();
        ShorteningStrategy strategyBase62 = shortenerFactory.createStrategy("base62");
        ShorteningStrategy strategyUuid = shortenerFactory.createStrategy("uuid");
        UrlStorage storage = InMemoryStorage.getInstance();

        UrlShortenerService shortenerService = new UrlShortenerService(storage, strategyBase62);

        String shortUrl = shortenerService.shortenUrl("https://example.com/very/long/url321312312");
        System.out.println("Short URL: " + shortUrl);

        String longUrl = shortenerService.expandUrl(shortUrl);
        System.out.println("Original URL: " + longUrl);

        shortenerService.setStrategy(strategyUuid);

        String shortUrl2 = shortenerService.shortenUrl("https://example.com/very/long/url");
        System.out.println("Short URL: " + shortUrl2);

        String longUrl2 = shortenerService.expandUrl(shortUrl);
        System.out.println("Original URL: " + longUrl2);
    }
}
