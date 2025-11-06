package expressJava.lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CountWords9 {

    @ParameterizedTest
    @MethodSource("testData")
    public void howManyWordsCheck(String stringForTest, int expectedWordsCount) {
        int actualWordsCount = MethodsForTests.countWords(stringForTest);
        Assertions.assertEquals(actualWordsCount, expectedWordsCount);
    }


    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("Hello One Two", 3),
                Arguments.of("",0)
        );
    }

    @Test
    public void howManyWordsCheckNull() {
        Assertions.assertThrows(NullPointerException.class, () -> MethodsForTests.countWords(null));
    }
}
