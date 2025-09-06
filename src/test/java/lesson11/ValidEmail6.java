package lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ValidEmail6 {

    @ParameterizedTest
    @MethodSource("testData")
    public void validEmailCheck(String emailForCheck, boolean expectedValidity) {
        boolean actualValidity = MethodsForTests.isValidEmail(emailForCheck);
        Assertions.assertEquals(actualValidity, expectedValidity);
    }

    public static Stream<Arguments> testData() {
        return Stream.of(Arguments.of("test@example.com", true), Arguments.of("bad@.com", false), Arguments.of("no-at-symbol", false), Arguments.of(null, false));
    }

}
