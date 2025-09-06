package lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ArrayMax4 {

    @ParameterizedTest
    @MethodSource("testData")
    public void findMaxCheck(int[] numbers, int expectedMaxNumber) {
        int actualMaxNumber = MethodsForTests.findMax(numbers);
        Assertions.assertEquals(actualMaxNumber, expectedMaxNumber);
    }


    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new int[]{3, 5, 7, 2}, 7),
                Arguments.of(new int[]{7}, 7),
                Arguments.of(new int[]{-3, -5, -7, -2}, -2)
        );
    }

    @Test
    public void findMaxCheckIfNull() {
        Assertions.assertThrows(NullPointerException.class, () -> MethodsForTests.findMax(null));
    }
}
