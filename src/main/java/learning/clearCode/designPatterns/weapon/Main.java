package learning.clearCode.designPatterns.weapon;

public class Main {
    public static void main(String[] args) {
        WeaponFactory weaponFactory;

        weaponFactory = new ArrowsAttack();
        weaponFactory.attackUsingWeapon();

        weaponFactory = new BulletAttack();
        weaponFactory.attackUsingWeapon();

        weaponFactory = new CloseRangeAttack();
        weaponFactory.attackUsingWeapon();
    }
}
