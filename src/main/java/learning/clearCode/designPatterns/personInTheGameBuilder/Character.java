package learning.clearCode.designPatterns.personInTheGameBuilder;

public class Character {
    private final int health;
    private final int damage;
    private final int armor;
    private final String magic;

    public Character(Builder b) {
        this.health = b.health;
        this.damage = b.damage;
        this.armor = b.armor;
        this.magic = b.magic;
    }

    public static class Builder {
        private int health;
        private int damage;
        private int armor;
        private String magic;

        public Builder health(int health) {
            this.health = health;
            return this;
        }

        public Builder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder armor(int armor) {
            this.armor = armor;
            return this;
        }

        public Builder magic(String magic) {
            this.magic = magic;
            return this;
        }

        public Character build(){
            return new Character(this);
        }
    }

    @Override
    public String toString() {
        return "Character{" +
                "health=" + health +
                ", damage=" + damage +
                ", armor=" + armor +
                ", magic='" + magic + '\'' +
                '}';
    }
}
