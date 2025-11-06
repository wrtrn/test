package learning.clearCode.designPatterns.furnitureAbstractFactory;

public class ModernChair implements Chair {
    @Override
    public void choose() {
        System.out.println("Choosing modern chair");
    }
}
