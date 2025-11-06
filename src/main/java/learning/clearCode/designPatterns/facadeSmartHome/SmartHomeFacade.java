package learning.clearCode.designPatterns.facadeSmartHome;

public class SmartHomeFacade implements OnOff {
    private AirConditioner airConditioner = new AirConditioner();
    private Light light = new Light();
    private SafetySystem safetySystem = new SafetySystem();

    public SmartHomeFacade() {
        this.airConditioner = new AirConditioner();
        this.light = new Light();
        this.safetySystem = new SafetySystem();
    }


    @Override
    public void turnOn() {
        airConditioner.turnOn();
        light.turnOn();
        safetySystem.turnOn();
    }

    @Override
    public void turnOff() {
        airConditioner.turnOff();
        light.turnOff();
        safetySystem.turnOff();
    }
}
