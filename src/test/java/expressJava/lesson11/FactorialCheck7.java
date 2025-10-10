package expressJava.lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FactorialCheck7 {

    @ParameterizedTest
    @MethodSource("testData")
    public void factorialCheck(int numberForCheck, int expectedFactorial) {
        int actualFactorial = MethodsForTests.factorial(numberForCheck);
        Assertions.assertEquals(actualFactorial, expectedFactorial);
    }

    public static Stream<Arguments> testData() {
        return Stream.of(Arguments.of(0, 1), Arguments.of(1, 1), Arguments.of(5, 120), Arguments.of(7, 5040));
    }


    @Test
    public void negativeFactorialCheck() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> MethodsForTests.factorial(-5));
    }
}
