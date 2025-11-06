package learning.expressJava.lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayMax8 {

    @ParameterizedTest
    @MethodSource("testData")
    public void findSecondMaxCheck(int[] numbers, int expectedMaxNumber) {
        int actualMaxNumber = MethodsForTests.findSecondMax(numbers);
        Assertions.assertEquals(actualMaxNumber, expectedMaxNumber);
    }


    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new int[]{3, 5, 7, 2}, 5),
                Arguments.of(new int[]{-3, -5, -7, -2}, -3)
        );
    }

    @Test
    public void findSecondMaxCheckIfOneElement() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> MethodsForTests.findSecondMax(new int[]{3}));
    }

    @Test
    public void findSecondMaxCheckIfTheSameNumbersInArray() {
        Assertions.assertThrows(NoSuchElementException.class, () -> MethodsForTests.findSecondMax(new int[]{7,7,7}));
    }
}
