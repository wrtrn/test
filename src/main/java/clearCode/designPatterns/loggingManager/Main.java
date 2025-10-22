package clearCode.designPatterns.loggingManager;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.recordEventsInformation("New event");
        logger.recordWarning("New warning");
        logger.recordError("New error");
    }
}
