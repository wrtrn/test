package clearCode.designPatterns.facadeDoorManagement;

public class Main {
    public static void main(String[] args) {
        DoorFacade doorFacade = new DoorFacade();
        doorFacade.doActionWithDoor("block");
        doorFacade.doActionWithDoor("open");
        doorFacade.doActionWithDoor("close");
    }
}
