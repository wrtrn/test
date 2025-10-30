package clearCode.designPatterns.facadeSmartHome;

public class Main {
    public static void main(String[] args) {
        SmartHomeFacade smartHomeFacade = new SmartHomeFacade();
        smartHomeFacade.turnOn();
        smartHomeFacade.turnOff();
    }
}
