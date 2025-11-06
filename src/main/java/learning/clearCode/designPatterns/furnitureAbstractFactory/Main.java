package learning.clearCode.designPatterns.furnitureAbstractFactory;

public class Main {
    public static void main(String[] args) {
        FurnitureFactory factory;

        String style = "Modern";

        if (style.equals("Modern")) {
            factory = new ModernFactory();
        } else {
            factory = new ClassicFactory();
        }
        Furniture furniture = new Furniture(factory);
        furniture.choose();
    }
}
