package clearCode.designPatterns.transport;

public class NormalSpeedDriving extends TransportFactory {
    @Override
    public Transport createTransport() {
        return new Moto();
    }
}
