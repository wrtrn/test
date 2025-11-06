package learning.expressJava.lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class VowelsTest2 {

    @ParameterizedTest
    @MethodSource("testDataForVowels")
    public void calculateVowels(String stringForTest, int expectedVowels) {
        int actualVowels = MethodsForTests.countVowels(stringForTest);
        Assertions.assertEquals(expectedVowels, actualVowels);
    }

    @Test
    public void calculateVowelsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> MethodsForTests.countVowels(null));
    }

    public static Stream<Arguments> testDataForVowels() {
        return Stream.of(Arguments.of("hello", 2), Arguments.of("java", 2), Arguments.of("AEIOU", 5), Arguments.of("", 0), Arguments.of("qqqq", 0));
    }
}
