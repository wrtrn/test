package learning.clearCode.designPatterns.weapon;

public abstract class WeaponFactory {

    public abstract Weapon createWeapon();

    public void attackUsingWeapon() {
        Weapon weapon = createWeapon();
        weapon.attack();
    }
}
