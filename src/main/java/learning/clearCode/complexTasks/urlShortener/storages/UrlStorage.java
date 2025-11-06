package learning.clearCode.complexTasks.urlShortener.storages;

import java.util.Map;

public interface UrlStorage {
    public void save(String longUrl, String shortUrl);

    public Map<String, String> getLinks();
}
