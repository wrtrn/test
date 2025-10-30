package clearCode.designPatterns.configurationManager;

public class DatabaseConnectionSettings {
    String url;
    String username;
    String password;

    public DatabaseConnectionSettings(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
