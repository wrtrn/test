package expressJava.lesson5.lunaPark;

public class LunaParkController {

    void maintenance(Attraction attraction) {
    attraction.maintenance();
    }

    void showInfo(Attraction attraction) {
        attraction.getFeelings();
    }
}
