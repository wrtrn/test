package expressJava.lesson11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PhoneNumberValidation10 {

    @ParameterizedTest
    @MethodSource("testData")
    public void phoneNumberCheck(String phoneForTest, boolean expectedResult) {
        boolean actualResult = MethodsForTests.isValidPhoneNumber(phoneForTest);
        Assertions.assertEquals(actualResult, expectedResult);
    }


    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("+1 1234567890", true),
                Arguments.of("12345",false),
                Arguments.of("invalid",false)
        );
    }
}
