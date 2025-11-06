package learning.clearCode.designPatterns.loggingManager;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    static Logger logger;

    List events = new ArrayList<>();
    List errors = new ArrayList<>();
    List warnings = new ArrayList<>();

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null)
            logger = new Logger();
        return logger;
    }

    public void recordEventsInformation(String event) {
        events.add(event);
    }

    public void recordError(String error) {
        errors.add(error);
    }

    public void recordWarning(String warning) {
        warnings.add(warning);
    }
}
