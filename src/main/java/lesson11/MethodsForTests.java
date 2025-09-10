package lesson11;

import java.util.Arrays;

public class MethodsForTests {

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static int countVowels(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return (int) input.toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) != -1)
                .count();
    }

    public static String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }

    public static int findMax(int[] numbers) {
        return Arrays.stream(numbers).max().orElseThrow();
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }

    public static int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative numbers not allowed");
        return (n == 0) ? 1 : n * factorial(n - 1);
    }

    public static int findSecondMax(int[] numbers) {
        return Arrays.stream(numbers).distinct().sorted().skip(numbers.length - 2).findFirst().orElseThrow();
    }

    public static int countWords(String sentence) {
        return sentence.trim().isEmpty() ? 0 : sentence.split("\\s+").length;
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\+\\d{1,3} \\d{10}");
    }
}
