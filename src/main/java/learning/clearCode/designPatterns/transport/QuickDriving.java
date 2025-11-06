package learning.clearCode.designPatterns.transport;

public class QuickDriving extends TransportFactory {
    @Override
    public Transport createTransport() {
        return new Car();
    }
}
