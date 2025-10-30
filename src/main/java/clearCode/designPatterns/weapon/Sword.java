package clearCode.designPatterns.weapon;

public class Sword implements Weapon {
    @Override
    public void attack() {
        System.out.println("The sword attacks from close range");
    }
}
