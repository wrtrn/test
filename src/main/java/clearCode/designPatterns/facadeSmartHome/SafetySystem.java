package clearCode.designPatterns.facadeSmartHome;

public class SafetySystem implements OnOff {
    @Override
    public void turnOn() {
        System.out.println("Turning on safety");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off safety");
    }
}
