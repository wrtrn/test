package expressJava.lesson8.exceptions;

public class AgeChecker {

    public void ageCheck(int age) throws AgeException {
        if (age<0 || age>150)
            throw new AgeException("Age is out of bounds");
    }
}
