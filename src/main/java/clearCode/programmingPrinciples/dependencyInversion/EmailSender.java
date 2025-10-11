package clearCode.programmingPrinciples.dependencyInversion;

class EmailSender implements Sender{
    public void send(String message) {
        System.out.println("Отправка email: " + message);
    }
}
