package learning.clearCode.designPatterns.personInTheGameBuilder;

public class Main {
    public static void main(String[] args) {
        Character character = new Character.Builder().health(100).armor(10).damage(15).magic("Froze").build();

        System.out.println(character.toString());
    }
}
