package clearCode.programmingPrinciples;

public class MathOperations {
    public int add(int[] numbersArray) {
        int sum = 0;
        for (int number : numbersArray)
            sum += number;
        return sum;
    }
}
