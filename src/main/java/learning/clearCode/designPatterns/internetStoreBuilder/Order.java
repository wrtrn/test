package learning.clearCode.designPatterns.internetStoreBuilder;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String paymentMethod;
    private final List<Item> items;
    private final int discount;

    public Order(Builder b) {
        this.paymentMethod = b.paymentMethod;
        this.items = b.items;
        this.discount = b.discount;
    }

    public static class Builder {
        private String paymentMethod;
        private List<Item> items = new ArrayList<>();
        private int discount;

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder items(Item item) {
            this.items.add(item);
            return this;
        }

        public Builder discount(int discount) {
            this.discount = discount;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public String toString() {
        return "Order created: Payment method: " + paymentMethod + ", discount = " + discount + ", items = " + items.toString();
    }
}
