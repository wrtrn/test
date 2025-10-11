package clearCode.programmingPrinciples.dependencyInversion;

public class Main {
    public static void main(String[] args) {
        Sender emailSender = new EmailSender();
        NotificationService notificationService = new NotificationService(emailSender);
        notificationService.sendNotification("Hello");
    }
}
