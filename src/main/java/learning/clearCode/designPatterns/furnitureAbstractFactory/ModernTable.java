package learning.clearCode.designPatterns.furnitureAbstractFactory;

public class ModernTable implements Table {
    @Override
    public void choose() {
        System.out.println("Choosing modern table");
    }
}
