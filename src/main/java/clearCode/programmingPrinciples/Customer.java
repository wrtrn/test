package clearCode.programmingPrinciples;

public class Customer {
    private final String name;
    private boolean hasCoupon;
    private int purchasesNumber;
    private boolean isLoyal;

    public Customer(String name, boolean hasCoupon, boolean isLoyal) {
        this.hasCoupon = hasCoupon;
        this.name = name;
        this.isLoyal = isLoyal;
    }

    public boolean isLoyal() {
        return isLoyal;
    }

    public void makeLoyal() {
        isLoyal = true;
    }

    public boolean isHasCoupon() {
        return hasCoupon;
    }

    public String getName() {
        return name;
    }

    public int getPurchasesNumber() {
        return purchasesNumber;
    }

    public void increasePurchasesNumber() {
        purchasesNumber++;
    }
}
