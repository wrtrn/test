package expressJava.lesson9.part1.mathOperations;

public class Main {

    public static void main(String[] args) {
        MathOperation addition = Double::sum;
        MathOperation subtraction = (x, y) -> x - y;
        MathOperation multiplication = (x, y ) -> x*y;
        MathOperation division = (x, y ) -> x/y;

        System.out.println(addition.calculate(5.0,6.0));
        System.out.println(subtraction.calculate(10.0,6.0));
        System.out.println(multiplication.calculate(5.0,5.0));
        System.out.println(division.calculate(5.0,5.0));
    }
}
