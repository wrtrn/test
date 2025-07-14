package lesson5.restaurantControl;

public class Beverage extends Position {

    public int volume;

    Beverage(int volume) {
        this.volume = volume;
    }

    @Override
    void writeDescription() {
        System.out.println("Beverage with volume " + volume);
    }
}
