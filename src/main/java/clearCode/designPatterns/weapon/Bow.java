package clearCode.designPatterns.weapon;

public class Bow implements Weapon {
    @Override
    public void attack() {
        System.out.println("The bow shoots arrows from a distance");
    }
}
