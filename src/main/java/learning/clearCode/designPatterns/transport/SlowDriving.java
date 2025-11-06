package learning.clearCode.designPatterns.transport;

public class SlowDriving extends TransportFactory {
    @Override
    public Transport createTransport() {
        return new Bicycle();
    }
}
