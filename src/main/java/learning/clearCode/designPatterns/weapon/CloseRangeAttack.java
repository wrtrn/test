package learning.clearCode.designPatterns.weapon;

public class CloseRangeAttack extends WeaponFactory {
    @Override
    public Weapon createWeapon() {
        return new Sword();
    }
}
