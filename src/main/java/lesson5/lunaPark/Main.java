package lesson5.lunaPark;

public class Main {
    public static void main(String[] args) {
        Attraction rollerCoaster = new RollerCoaster();
        Attraction carousel = new Carousel();

        LunaParkController lunaParkController = new LunaParkController();

        lunaParkController.maintenance(rollerCoaster);
        lunaParkController.showInfo(rollerCoaster);
        lunaParkController.maintenance(carousel);
        lunaParkController.showInfo(carousel);
    }
}
