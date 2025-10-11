package clearCode.programmingPrinciples.openClose;

public class CreditCardPaymentService implements PaymentSevice {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата кредитной картой на сумму " + amount);
    }
}
