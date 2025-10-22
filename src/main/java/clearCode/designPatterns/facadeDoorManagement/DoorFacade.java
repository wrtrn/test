package clearCode.designPatterns.facadeDoorManagement;

public class DoorFacade {
    private DoorBlocking doorBlocking;
    private DoorClosing doorClosing;
    private DoorOpening doorOpening;

    public DoorFacade() {
        this.doorBlocking = new DoorBlocking();
        this.doorClosing = new DoorClosing();
        this.doorOpening = new DoorOpening();
    }

    public void doActionWithDoor(String action) {
        if (action.equals("open")) doorOpening.openDoor();
        if (action.equals("close")) doorClosing.closeDoor();
        if (action.equals("block")) doorBlocking.blockDoor();
    }
}
