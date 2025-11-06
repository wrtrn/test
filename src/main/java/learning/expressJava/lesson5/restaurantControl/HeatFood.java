package learning.expressJava.lesson5.restaurantControl;

public class HeatFood extends Position {

    public int temperature;

    HeatFood(int temperature) {
        this.temperature = temperature;
    }

    @Override
    void writeDescription() {
        System.out.println("Heat food with temperature " + temperature);
    }
}
