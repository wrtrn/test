package expressJava.lesson5.restaurantControl;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Position beverage = new Beverage(10);
        Position heatFood = new HeatFood(5);

        restaurant.addPosition(beverage);
        restaurant.writePositionDescription();

        restaurant.addPosition(heatFood);
        restaurant.writePositionDescription();
    }
}
