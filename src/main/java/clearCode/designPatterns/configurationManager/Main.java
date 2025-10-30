package clearCode.designPatterns.configurationManager;

public class Main {

    public static void main(String[] args) {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        DatabaseConnectionSettings settings = new DatabaseConnectionSettings("https://vk.com", "Ivan", "123456");
        configurationManager.setSettings(settings, "/cd/tre", "maximum");
    }
}
