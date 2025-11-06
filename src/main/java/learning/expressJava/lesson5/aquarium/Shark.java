package learning.expressJava.lesson5.aquarium;

public class Shark extends Animal implements Swimable{

    @Override
    public void swim() {
        System.out.println("Shark swims quickly and aggressively");
    }
}
