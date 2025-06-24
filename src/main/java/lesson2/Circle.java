package lesson2;

public class Circle {
    int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    void setRadius(int radius) {
        this.radius = radius;
    }

    int getRadius() {
        return radius;
    }

    double calculateArea() {
        return radius * radius * Math.PI;
    }

    double calculateCircumference() {
        return Math.PI * 2 * radius;
    }
}
