package complexTasks.inventoryService;

public class Product {
    private String name;
    private double price;
    private String Category;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        Category = category;
    }

    public String getCategory() {
        return Category;
    }
}
