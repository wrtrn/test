package complexTasks.javaCore.clearCode.urlShortener.storages;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements UrlStorage {

    private static InMemoryStorage instance;
    private Map<String, String> links = new HashMap<>();

    private InMemoryStorage() {
    }

    public static InMemoryStorage getInstance() {
        if (instance == null)
            instance = new InMemoryStorage();

        return instance;
    }

    @Override
    public void save(String longUrl, String shortUrl) {
        links.put(longUrl, shortUrl);
    }

    public Map<String, String> getLinks() {
        return links;
    }
}
