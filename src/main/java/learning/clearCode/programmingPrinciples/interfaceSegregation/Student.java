package learning.clearCode.programmingPrinciples.interfaceSegregation;

public class Student implements Eating{
    @Override
    public void eat() {
        System.out.println("Студент обедает");
    }
}
