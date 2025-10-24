package complexTasks.javaCore.clearCode.urlShortener.storages;

import java.util.Map;

public interface UrlStorage {
    public void save(String longUrl, String shortUrl);

    public Map<String, String> getLinks();
}
