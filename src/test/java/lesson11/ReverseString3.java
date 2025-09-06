package lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ReverseString3 {

    @ParameterizedTest
    @MethodSource("testDataForReverseString")
    public void reverseStringCheck(String stringForTest, String expectedString) {
        String actualString = MethodsForTests.reverse(stringForTest);
        Assertions.assertEquals(actualString, expectedString);
    }

    public static Stream<Arguments> testDataForReverseString() {
        return Stream.of(Arguments.of("hello", "olleh"), Arguments.of("java", "avaj"), Arguments.of("AEIOU", "UOIEA"), Arguments.of("", ""), Arguments.of(null, null));
    }
}
