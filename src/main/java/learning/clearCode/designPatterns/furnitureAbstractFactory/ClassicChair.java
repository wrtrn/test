package learning.clearCode.designPatterns.furnitureAbstractFactory;

public class ClassicChair implements Chair {
    @Override
    public void choose() {
        System.out.println("Choosing classic chair");
    }
}
