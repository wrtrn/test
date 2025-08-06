package lesson5.restaurantControl;

public class Restaurant {
    private Position position;

    void addPosition(Position position) {
        this.position = position;
    }

    void writePositionDescription() {
        position.writeDescription();
    }
}
