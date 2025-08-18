package lesson8.exceptions;

public class UncheckedExceptionProcessing {


    public void division(int numberOne, int numberTwo) {
        int result;
        {
            try {
                result = numberOne / numberTwo;
            } catch (ArithmeticException e) {
                System.out.println("Number two equals 0");
            }
        }
    }
}
