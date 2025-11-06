package expressJava.lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LeapYear5 {

    @ParameterizedTest
    @MethodSource("testData")
    public void reverseStringCheck(int yearForTest, boolean expectedLeapYear) {
        boolean actualLeapYear = MethodsForTests.isLeapYear(yearForTest);
        Assertions.assertEquals(actualLeapYear, expectedLeapYear);
    }

    public static Stream<Arguments> testData() {
        return Stream.of(Arguments.of(2021, false), Arguments.of(2022, false), Arguments.of(2020, true), Arguments.of(2000, true), Arguments.of(1600, true), Arguments.of(1900, false), Arguments.of(2100, false));
    }
}
