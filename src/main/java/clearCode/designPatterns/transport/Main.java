package clearCode.designPatterns.transport;

public class Main {
    public static void main(String[] args) {
        TransportFactory transportFactory;

        transportFactory = new SlowDriving();
        transportFactory.driveOnTheTransport();

        transportFactory = new QuickDriving();
        transportFactory.driveOnTheTransport();

        transportFactory = new NormalSpeedDriving();
        transportFactory.driveOnTheTransport();
    }
}
