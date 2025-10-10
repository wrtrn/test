package expressJava.lesson5.zoo;

public class Elephant extends Animal{
    @Override
    void makeSound() {
        System.out.println("Elephant makes YYYYYYYYY");
    }

    @Override
    void move() {
        System.out.println("Elephant walking");
    }
}
