package learning.clearCode.designPatterns.facadeSmartHome;

public class AirConditioner implements OnOff {
    @Override
    public void turnOn() {
        System.out.println("Turning on air cond");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off cond");
    }
}
