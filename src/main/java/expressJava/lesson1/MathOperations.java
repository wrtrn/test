package expressJava.lesson1;

public class MathOperations {

    int add(int x, int y) {

        return x + y;
    }

    int subtract(int x, int y) {

        return x - y;
    }

    int multiply(int x, int y) {

        return x * y;
    }

    double divide(int x, int y) {

        return (double) x / y;
    }

    int findMax(int a, int b) {
        return Math.max(a, b);
    }

    int difference(int x, int y) {
        return Math.abs(x - y);
    }

    int squareArea(int side) {
        return side * side;
    }

    int squarePerimeter(int side) {
        return 4 * side;
    }

    double convertSecondsToMinutes(int seconds) {
        return (double) seconds / 60.0;
    }

    double averageSpeed(double distance, double time) {
        return distance / time;
    }

    double findHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    double circleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    double calculatePercentage(double total, double part) {
        return part / total * 100;
    }

    double celsiusToFahrenheit(double c) {
        return c * 9 / 5 + 32;
    }

    double fahrenheitToCelsius(double f) {
        return (f - 32) * 5 / 9;
    }
}
