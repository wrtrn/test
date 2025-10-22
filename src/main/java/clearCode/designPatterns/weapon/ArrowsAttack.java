package clearCode.designPatterns.weapon;

public class ArrowsAttack extends WeaponFactory {
    @Override
    public Weapon createWeapon() {
        return new Bow();
    }
}
