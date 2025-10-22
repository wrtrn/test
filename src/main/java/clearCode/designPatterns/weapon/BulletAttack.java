package clearCode.designPatterns.weapon;

public class BulletAttack extends WeaponFactory {
    @Override
    public Weapon createWeapon() {
        return new Gun();
    }
}
