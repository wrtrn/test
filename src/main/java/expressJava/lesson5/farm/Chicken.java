package expressJava.lesson5.farm;

public class Chicken extends Animal{
    @Override
    void giveYield() {
        System.out.println("Chicken gives eggs");
    }

    @Override
    void recieveFood() {
        System.out.println("Chicken eats grain");
    }
}
