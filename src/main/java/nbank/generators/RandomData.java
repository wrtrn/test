package nbank.generators;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomData {
    private RandomData() {
    }

    public static String getUsername() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String getPassword() {
        return RandomStringUtils.randomAlphabetic(3).toUpperCase() +
                RandomStringUtils.randomAlphabetic(5).toLowerCase() +
                RandomStringUtils.randomNumeric(3) + "$";
    }

    public static int getDepositValue() {
        return new Random().nextInt(4999) + 1;
    }

    public static int getTransferValue() {
        return new Random().nextInt(4999) + 1;
    }
}
