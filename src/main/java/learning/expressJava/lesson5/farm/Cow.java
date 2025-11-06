package learning.expressJava.lesson5.farm;

public class Cow extends Animal{
    @Override
    void giveYield() {
        System.out.println("Cow gives milk");
    }

    @Override
    void recieveFood() {
        System.out.println("Cow eats grass");
    }
}
