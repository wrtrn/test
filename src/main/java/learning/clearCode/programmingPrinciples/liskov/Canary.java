package learning.clearCode.programmingPrinciples.liskov;

public class Canary extends Bird implements Flyable{
    @Override
    public void fly() {
        System.out.println("Канарейка летит");
    }
}
