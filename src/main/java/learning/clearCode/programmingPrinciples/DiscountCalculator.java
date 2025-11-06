package learning.clearCode.programmingPrinciples;

public class DiscountCalculator {
    public double calculateDiscount(double price, Customer customer) {
        customer.increasePurchasesNumber();

        if (customer.isLoyal() && customer.getPurchasesNumber() - 1 == 0) {
            return calculatePriceWithDiscount(price, 10);
        }

        if (customer.isLoyal() && customer.getPurchasesNumber() - 1 > 0) {
            return calculatePriceWithDiscount(price, 5);
        }

        if (customer.isHasCoupon()) {
            return calculatePriceWithDiscount(price, 7);
        } else {
            return calculatePriceWithDiscount(price, 2);
        }
    }

    private double calculatePriceWithDiscount(double price, int discountPercentage) {
        return price - price * discountPercentage / 100;
    }
}