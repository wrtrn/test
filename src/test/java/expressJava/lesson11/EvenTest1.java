package expressJava.lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EvenTest1 {

    @ParameterizedTest
    @MethodSource("numbersForTest")
    public void evenOrNotIfNumberIsEven(int number, boolean expectedResult) {
        boolean result = MethodsForTests.isEven(number);
        Assertions.assertEquals(result, expectedResult);

    }

    public static Stream<Arguments> numbersForTest() {
        return Stream.of(
                Arguments.of(6, true),
                Arguments.of(5, false),
                Arguments.of(0, true),
                Arguments.of(-2, true),
                Arguments.of(-1, false));
    }
}
