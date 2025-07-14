package lesson5.lunaPark;

public class Carousel extends Attraction{
    @Override
    void maintenance() {
        System.out.println("Maintenance carousel");
    }

    @Override
    void getFeelings() {
        System.out.println("Slow speed Carousel");
    }
}
