package learning.clearCode.designPatterns.configurationManager;

public class ConfigurationManager {

    static ConfigurationManager configurationManager;

    DatabaseConnectionSettings settings;
    String fileStoragePath;
    String loggingLevel;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) configurationManager = new ConfigurationManager();
        return configurationManager;
    }

    public void setSettings(DatabaseConnectionSettings settings, String fileStoragePath, String loggingLevel) {
        this.settings = settings;
        this.fileStoragePath = fileStoragePath;
        this.loggingLevel = loggingLevel;
    }
}
