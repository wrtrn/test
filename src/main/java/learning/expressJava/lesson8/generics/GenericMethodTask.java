package learning.expressJava.lesson8.generics;

public class GenericMethodTask {

    public <T> void printArray(T[] array) {
        for (T element : array)
        {
            System.out.println(element);
        }
    }
}
