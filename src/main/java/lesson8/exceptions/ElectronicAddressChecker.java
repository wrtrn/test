package lesson8.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElectronicAddressChecker {

    public void checkAddress(String address) throws ElectronicAddressException {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        Matcher matcher = pattern.matcher(address);
        if (!matcher.find()) {
            throw new ElectronicAddressException("It is an incorrect email address");
        }
    }
}
