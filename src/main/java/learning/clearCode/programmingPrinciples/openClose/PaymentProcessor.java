package learning.clearCode.programmingPrinciples.openClose;

public class PaymentProcessor {
    public void processPayment(PaymentSevice paymentSevice, double amount) {
        paymentSevice.pay(amount);
    }
}
