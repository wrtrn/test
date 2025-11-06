package learning.clearCode.designPatterns.facadeSmartHome;

public class Light implements OnOff {
    @Override
    public void turnOn() {
        System.out.println("Turning on light");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off light");
    }
}
