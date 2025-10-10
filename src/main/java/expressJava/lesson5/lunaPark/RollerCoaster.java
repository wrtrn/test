package expressJava.lesson5.lunaPark;

public class RollerCoaster extends Attraction{
    @Override
    void maintenance() {
        System.out.println("Safety check roller coaster");
    }

    @Override
    void getFeelings() {
        System.out.println("Incredibly fast Roller Coaster");
    }
}
