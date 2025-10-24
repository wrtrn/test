package clearCode.urlShortener;

import clearCode.urlShortener.storages.UrlStorage;
import clearCode.urlShortener.strategies.ShorteningStrategy;

import java.util.Map;

public class UrlShortenerService {
    private UrlStorage storage;
    private ShorteningStrategy strategy;

    public UrlShortenerService(UrlStorage storage, ShorteningStrategy strategy) {
        this.strategy = strategy;
        this.storage = storage;
    }

    public void setStrategy(ShorteningStrategy strategy) {
        this.strategy = strategy;
    }

    public String shortenUrl(String longUrl) {
        Map<String, String> links = storage.getLinks();

        if (storage.getLinks().get(longUrl) == null) {
            String shortUrl = strategy.shorten(longUrl);
            storage.save(longUrl, "https://bit.ly/" + shortUrl);
            return "https://bit.ly/" + shortUrl;
        } else return links.get(longUrl);
    }

    public String expandUrl(String shortUrl) {
        Map<String, String> links = storage.getLinks();
        String longUrl = "";

        for (Map.Entry<String, String> el : links.entrySet()) {
            if (shortUrl.equals(el.getValue()))
                longUrl = el.getKey();
        }
        return longUrl;
    }
}
