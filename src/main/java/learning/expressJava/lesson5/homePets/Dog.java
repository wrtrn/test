package learning.expressJava.lesson5.homePets;

public class Dog extends Pet {
    @Override
    void doAction() {
        System.out.println("Dog walks");
    }

    @Override
    void eat() {
        System.out.println("Dog eats dry food");
    }
}
