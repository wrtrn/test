package clearCode.designPatterns.furnitureAbstractFactory;

public class ClassicTable implements Table {
    @Override
    public void choose() {
        System.out.println("Choosing classic table");
    }
}
