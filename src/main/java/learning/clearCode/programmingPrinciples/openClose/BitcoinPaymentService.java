package learning.clearCode.programmingPrinciples.openClose;

public class BitcoinPaymentService implements PaymentSevice {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата Bitcoin на сумму " + amount);
    }
}
