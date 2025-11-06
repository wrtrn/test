package learning.expressJava.lesson5.homePets;

public class Cat extends Pet {
    @Override
    void doAction() {
        System.out.println("Cat plays");
    }

    @Override
    void eat() {
        System.out.println("Cat eats wet food");
    }
}
