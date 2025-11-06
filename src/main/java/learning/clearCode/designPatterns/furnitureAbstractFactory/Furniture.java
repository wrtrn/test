package learning.clearCode.designPatterns.furnitureAbstractFactory;

public class Furniture {
    private Chair chair;
    private Table table;

    public Furniture(FurnitureFactory factory) {
        chair = factory.createChair();
        table = factory.createTable();
    }

    public void choose() {
        chair.choose();
        table.choose();
    }
}
