package learning.clearCode.designPatterns.transport;

abstract public class TransportFactory {

    public abstract Transport createTransport();

    public void driveOnTheTransport() {
        Transport transport = createTransport();
        transport.drive();
    }
}
