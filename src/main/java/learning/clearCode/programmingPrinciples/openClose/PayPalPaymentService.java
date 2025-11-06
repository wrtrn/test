package learning.clearCode.programmingPrinciples.openClose;

public class PayPalPaymentService implements PaymentSevice {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата через PayPal на сумму " + amount);
    }
}
