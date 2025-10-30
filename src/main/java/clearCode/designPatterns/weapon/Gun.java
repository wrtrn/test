package clearCode.designPatterns.weapon;

public class Gun implements Weapon {
    @Override
    public void attack() {
        System.out.println("The gun shoots bullets from a distance");
    }
}
