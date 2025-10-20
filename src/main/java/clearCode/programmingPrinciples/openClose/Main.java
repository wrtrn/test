package clearCode.programmingPrinciples.openClose;

public class Main {
    public static void main(String[] args) {
        PaymentSevice bitcoin = new BitcoinPaymentService();
        PaymentSevice creditCard = new CreditCardPaymentService();
        PaymentSevice payPal = new PayPalPaymentService();

        bitcoin.pay(100);
        creditCard.pay(1000);
        payPal.pay(10000);
    }
}
