package clearCode.designPatterns.transport;

public class Bicycle implements Transport {
    @Override
    public void drive() {
        System.out.println("Bicycle drives slowly");
    }
}
