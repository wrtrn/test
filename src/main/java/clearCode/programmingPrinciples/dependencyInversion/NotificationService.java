package clearCode.programmingPrinciples.dependencyInversion;

public class NotificationService {
    private Sender sender;

    public NotificationService(Sender sender) {
        this.sender = sender;
    }

    public void sendNotification(String message) {
        sender.send(message);
    }
}
