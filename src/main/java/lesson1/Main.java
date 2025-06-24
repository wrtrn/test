package lesson1;

public class Main {
    public static void main(String[] args) {

        MathOperations mathOperations = new MathOperations();

        System.out.println(mathOperations.celsiusToFahrenheit(5));
        System.out.println(mathOperations.fahrenheitToCelsius(5));
    }
}