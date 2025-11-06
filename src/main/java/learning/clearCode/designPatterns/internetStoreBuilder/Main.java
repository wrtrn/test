package learning.clearCode.designPatterns.internetStoreBuilder;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Phone", 120);
        Item item2 = new Item("Toy", 10);

        Order order = new Order.Builder().paymentMethod("Stripe").items(item1).items(item2).discount(10).build();
        System.out.println(order.toString());
    }
}
